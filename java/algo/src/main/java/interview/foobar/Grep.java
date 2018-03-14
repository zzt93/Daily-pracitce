package interview.foobar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zzt
 */
public class Grep {

  public static List<Path> grep(String dir) throws IOException {
    List<Path> paths = travelFiles(dir);
    Pattern num = Pattern.compile("((\\(\\d{3}\\) )|(\\d{3}-))\\d{3}-\\d{4}");
    Pattern.compile("([^ ])\\(");
    for (Iterator<Path> it = paths.iterator(); it.hasNext(); ) {
      Path p = it.next();
      boolean has = false;
      for (String line : Files.readAllLines(p)) {
        if (num.matcher(line).find()) {
          has = true;
          break;
        }
      }
      if (!has) {
        it.remove();
      }
    }
    return paths;
  }

  private static List<Path> travelFiles(String dir) throws IOException {
    Path path = Paths.get(dir);
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      throw new IllegalArgumentException();
    }
    List<Path> res = new LinkedList<>();
    recur(path, res);
    return res;
  }

  private static void recur(Path path, List<Path> res) throws IOException {
    List<Path> children = Files.list(path).collect(Collectors.toList());
    for (Path p : children) {
      if (Files.isDirectory(p)) {
        recur(p, res);
      } else {
        res.add(p);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    // 000-000-0000
    System.out.println(grep("/Users/xuyangdong/Daily-pracitce/java/algo/src"));
  }

}
