package memory;

import java.io.IOException;

/**
 * @author zzt
 */
public class OOM {

  public static void fastAllocate() throws IOException {
    int l = (int) 1e6;
    int[][] ints = new int[l][];
    for (int i = 0; i < ((int) 1e9); i++) {
      ints[i%l] = new int[(int) 1e3];
    }
    System.in.read();
  }

  public static void main(String[] args) throws IOException {
    fastAllocate();
  }

}
