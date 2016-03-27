#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <pwd.h>
#include <grp.h>

#define FAIL -1
#define SUCC 0
#define SKIP -2

int not_all_handler(const char *name, struct stat* stat, char *buf, int now, int len) {
    if (name[0] == '.') {
        return SKIP;
    }
    return now;
}

int all_handler(const char *name, struct stat* stat, char *buf, int now, int len) {
    return now;
}

int dir_handler(const char *name, struct stat* stat, char *buf, int now, int len) {
    if (strcmp(name, ".") != 0) {
        return SKIP;
    }
    return now;
}

#define NUM_MAX_WIDTH 14

static
int copy_int_to_str(char *buf, int i) {
    int len = NUM_MAX_WIDTH + 1;
    char num[len];
    len = snprintf(num, len, "%8d ", i);
    strncpy(buf, num, len);
    return len;
}

static
int copy_str_to_str(char *dest, char *aim) {
    int len = strlen(aim);
    strncpy(dest, aim, len);
    strncpy(dest + len, " ", 1);
    return len + 1;
}

int inode_handler(const char *name, struct stat* stat, char *buf, int now, int max) {
    if (now + NUM_MAX_WIDTH > max) {
        return FAIL;
    }

    int len = copy_int_to_str(buf + now, stat->st_ino);
    return now + len;
}

const char *file_type(mode_t mode) {
    if (S_ISDIR(mode)) {
        return "d";
    } else if (S_ISREG(mode)) {
        return "-";
    } else if (S_ISCHR(mode)) {
        return "c";
    } else if (S_ISBLK(mode)) {
        return "b";
    } else if (S_ISFIFO(mode)) {
        return "p";
    } else if (S_ISLNK(mode)) {
        return "l";
    }
    return "s";
}

int detail_handler(const char *name, struct stat* stat, char *buf, int now, int max) {
    if (now + NUM_MAX_WIDTH > max) {
        return FAIL;
    }

    //("File Permissions");
    strncpy(buf + now++, file_type(stat->st_mode), 1);
    strncpy(buf + now++, (stat->st_mode & S_IRUSR) ? "r" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IWUSR) ? "w" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IXUSR) ? "x" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IRGRP) ? "r" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IWGRP) ? "w" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IXGRP) ? "x" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IROTH) ? "r" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IWOTH) ? "w" : "-", 1);
    strncpy(buf + now++, (stat->st_mode & S_IXOTH) ? "x" : "-", 1);
    strncpy(buf + now++, " ", 1);
    now += copy_int_to_str(buf + now, stat->st_nlink);
    now += copy_int_to_str(buf + now, stat->st_size);

    // user name
    struct passwd *pwd;
    pwd = getpwuid(stat->st_uid);
    now += copy_str_to_str(buf + now, pwd->pw_name);

    // group name
    struct group *grp;
    grp = getgrgid(stat->st_gid);
    now += copy_str_to_str(buf + now, grp->gr_name);

    // time
    now += copy_str_to_str(buf + now, asctime(localtime(&stat->st_atim.tv_sec)));
    // remove a newline of time string
    buf[now - 2] = ' ';
    return now;
}

static int recursive = 0;
static int all = 0;

#define MAX_OPTIONS_NUM 16
typedef int (* Handler)(const char *name, struct stat*, char * buf, int now, int len);
static Handler handler[MAX_OPTIONS_NUM];

int assign_handler(char option, int *option_num, Handler *f) {
    switch(option) {
        case 'a':
            all = 1;
            return SUCC;
        case 'd':
            *f = dir_handler;
            all = 1;
            break;
        case 'i':
            *f = inode_handler;
            break;
        case 'l':
            *f = detail_handler;
            break;
        case 'R':
            recursive = 1;
            return SUCC;
        default:
            return FAIL;
    }
    *option_num = *option_num + 1;
    return SUCC;
}

/**
   filter: -a( and without -a), -d
   reduce: -i, -l
   map: -i, -l
 */
int cmd_intercept(int argc, char *argv[]) {
    int i;
    int option_num = 0;
    for (i = 0; i < argc; i++) {
        int len = strlen(argv[i]);
        if (len < 2 || argv[i][0] != '-') {
            return FAIL;
        } else if (len == 2) {
            if (assign_handler(argv[i][1], &option_num, handler + option_num) == FAIL) {
                printf("not support this option: %s", argv[i]);
                return FAIL;
            }
        } else {
            int j;
            for (j = 1; j < len; j++) {
                if (assign_handler(argv[i][j], &option_num, handler + option_num) == FAIL) {
                    printf("not support this option: %s", argv[i]);
                    return FAIL;
                }
            }
        }
    }
    if (all) {
        handler[option_num++] = all_handler;
    } else {
        handler[option_num++] = not_all_handler;
    }
    return option_num;
}

#define MAX_LINE 256

void ls(const char *dir, int option_num) {
    DIR *dp;
    struct dirent *entry;

    if ((dp = opendir(dir)) == NULL) {
        puts("No such directory or file");
        return;
    }
    chdir(dir);
    struct stat buf;
    while ((entry = readdir(dp)) != NULL) {
        if (stat(entry->d_name, &buf) == -1) {
            continue;
        }
        int now = 0;
        char line[MAX_LINE + 1] = {0};
        int i;
        for (i = 0; i < option_num; i++) {
            now = handler[i](entry->d_name, &buf, line, now, MAX_LINE);
            if (now == FAIL) {
                puts("too long output line");
                return;
            }

            if (now == SKIP) {
                break;
            }
        }
        if (now == SKIP) {
            continue;
        }
        if (now + strlen(entry->d_name) < MAX_LINE) {
            strcpy(line + now, entry->d_name);
        } else {
            puts("too long output line");
        }
        printf("%s\n", line);
        if (recursive && S_ISDIR(buf.st_mode) &&
            (strcmp(entry->d_name, ".") != 0 &&
                strcmp(entry->d_name, "..") != 0)) {
            printf("%s:\n", entry->d_name);
            ls(entry->d_name, option_num);
            puts("");
        }
    }
    chdir("..");
}

int main(int argc, char *argv[]){
    if (argc < 2) {
        puts("too few arguments");
        return 1;
    }
    int option_num = cmd_intercept(argc - 2, argv + 1);
    if (option_num < 0) {
        puts("Invalid option");
        return 1;
    }
    ls(argv[argc - 1], option_num);
    return 0;
}
