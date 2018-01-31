package interview.leetcode._2xx._27x;

/**
 * @author zzt
 */
public class Integer2English {

  private String[] less20 = {"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ",
      "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
      "Eighteen ", "Nineteen "};
  private String[] tens = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ",
      "Eighty ", "Ninety ", };
  private String[] hundreds = {"", "Thousand ", "Million ", "Billion "};

  public String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (num > 0) {
      int f = num % 100;
      num /= 100;
      int s = num % 10;
      num /= 10;
      if (f != 0 || s != 0) {
        sb.insert(0, hundreds[i / 3]);
      }
      if (f < 20) {
        sb.insert(0, less20[f]);
      } else {
        sb.insert(0, less20[f % 10]).insert(0, tens[f / 10]);
      }
      if (s != 0) {
        sb.insert(0,"Hundred ").insert(0, less20[s]);
      }
      i += 3;
    }
    return sb.deleteCharAt(sb.length()-1).toString();
  }

  public static void main(String[] args) {
    Integer2English i = new Integer2English();
    System.out.println(i.numberToWords(10000000));
    System.out.println(i.numberToWords(1));
    System.out.println(i.numberToWords(12));
    System.out.println(i.numberToWords(123));
    System.out.println(i.numberToWords(1234));
    System.out.println(i.numberToWords(12345));
    System.out.println(i.numberToWords(123456));
    System.out.println(i.numberToWords(1234567));
    System.out.println(i.numberToWords(12345678));
    System.out.println(i.numberToWords(123456789));
  }
}
