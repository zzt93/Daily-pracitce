package interview.google.foobar;

/**
 * @author zzt
 */
public class Translation {

  public static String answer(String plaintext) {
    String[] map = new String[26];
    String cap = "000001", space = "000000";
    String plain = "the quick brown fox jumps over the lazy dog",
        encode =
            "011110110010100010000000111110101001010100100100101000000000110000111010101010010111101110000000110100101010101101000000010110101001101100111100011100000000101010111001100010111010000000011110110010100010000000111000100000101011101111000000100110101010110110";
    for (int i = 0; i < plain.length(); i++) {
      char c = plain.charAt(i);
      if (Character.isUpperCase(c)) c = Character.toLowerCase(c);
      if (c == ' ') continue;
      map[c-'a']=encode.substring(i*6, i*6+6);
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < plaintext.length(); i++) {
      char c = plaintext.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.append(cap);
        c = Character.toLowerCase(c);
      }
      if (c == ' ') {
        sb.append(space);
        continue;
      }
      sb.append(map[c-'a']);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(answer("code"));
    System.out.println(answer("Braille"));
//    000001110000111010100000010100111000111000100010
  }

}
