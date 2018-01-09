package competition.leetcode.w66;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zzt on 1/7/18.
 * <p>
 * <h3></h3>
 */
public class BoldWords {

    public String boldWords(String[] words, String S) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        char[] cs = S.toCharArray();
        HashMap<Integer, Integer> i2len = new HashMap<>();
        for (int i = 0; i < cs.length; i++) {
            for (int x = 1; x <= 10 && i + x <= cs.length; x++) {
                String key = new String(cs, i, x);
                if (set.contains(key)) {
                    i2len.put(i, x);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int endL = -1;
        for (int i = 0; i < cs.length; i++) {
            boolean b = i2len.containsKey(i);
            if (i == endL) {
                if (!b) {
                    sb.append("</b>");
                }
            }
            if (!b) {
                sb.append(cs[i]);
            } else {
                if (i > endL) {
                    sb.append("<b>");
                }
                sb.append(cs[i]);
                endL = Math.max(i2len.get(i) + i, endL);
            }
        }
        if (endL == cs.length) {
            sb.append("</b>");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BoldWords b = new BoldWords();
        System.out.println(b.boldWords(new String[]{"d", "gtmjvoqbx", "ipvptk", "efntdjw",
                        "wowctg", "lqrofqg", "fqlu", "oz", "brzzz", "nqrppkbw", "voqbxarmlg",
                        "djwhzbwrvk", "tmjvo", "wa", "fbefntd", "sbnxm", "k", "oiudhjpnw",
                        "znymy", "vkidn", "fnt", "tplqrof", "j", "c", "eq", "zozjxbzt",
                        "sisfbefnt", "bxarml", "ae", "eicjou", "ajgufksqe", "gufksqek", "ygpne",
                        "wtcmml", "lv", "x", "jldkrmujqw", "j", "hz", "atp", "zcnjtkgh", "jkrig",
                        "juujcnjdjo", "uujcnjdj", "rofqg", "yupnluzkky", "qfo", "oqou", "mxb",
                        "pvptkf"},
                "eicjoudmxbevzrvravkidnricwsbnxmxvdckzahmqzbrlqugtmjvoqbxarmlgjeqcorhnodvnoqfomdpkrcoqoudcpeochkhnhdghbbyjiiyrvpkvsdydiwowctgdzehqafdszhjktayayqcfvnajququqgftptryamllxnsysisfbefntdjwhzbwrvkrihqxuofbhrjkrigjldkrmujqwajgufksqekmfaijzsjiotmcivroaoeqpoiudhjpnwlsnxddehvxkroilvyddkfrfskhkzcnjtkghsszofuxnnjoyghbnlicunqrppkbwwyzozjxbztjsxqxmzwarkjqwpvbuwcygpneqthatplqrofqgwwfmhzxjddhyupnluzkkysofgqawjyrwhfgdpkhiqgkpupgdeonipvptkfqluytogoljiaexrnxckeofqojltdjuujcnjdjohqbrzzzznymyrbbcjjmacdqyhpwtcmmlpjbqic"));
        System.out.println(b.boldWords(new String[]{"abd", "bc"}, "aabcabd"));
        System.out.println(b.boldWords(new String[]{"abcd", "bc"}, "aabcd"));
        System.out.println(b.boldWords(new String[]{}, ""));
        System.out.println(b.boldWords(new String[]{"ab", "bc"}, "aabcd"));
        System.out.println(b.boldWords(new String[]{"abc", "bc"}, "aabcd"));
    }
}
