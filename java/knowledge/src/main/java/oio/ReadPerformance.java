package oio;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Consumer;


/**
 * @author zzt
 */
public class ReadPerformance {

  private static void readSingle(String file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      int cnt = 0;
      int b;
      while ((b = fis.read()) != -1) {
        if (b == '\n') {
          cnt++;
        }
      }
      fis.close();
      System.out.println(cnt);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  private static void buffered(String file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      int cnt = 0;
      int b;
      while ((b = bis.read()) != -1) {
        if (b == '\n') {
          cnt++;
        }
      }
      bis.close();
      System.out.println(cnt);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  private static void bufferLarger(String file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      byte buf[] = new byte[2048];
      int cnt = 0;
      int n;
      while ((n = fis.read(buf)) != -1) {
        for (int i = 0; i < n; i++) {
          if (buf[i] == '\n') {
            cnt++;
          }
        }
      }
      fis.close();
      System.out.println(cnt);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  public static void countLine(String file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      DataInputStream dis = new DataInputStream(bis);
      int cnt = 0;
      while (dis.readLine() != null) {
        cnt++;
      }
      dis.close();
      System.out.println(cnt);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  public static void readerCountLine(String file) {
    try {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      int cnt = 0;
      while (br.readLine() != null) {
        cnt++;
      }
      br.close();
      System.out.println(cnt);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  public static void streamChar() {
    FileOutputStream fos = new FileOutputStream(FileDescriptor.out);
    PrintStream ps = new PrintStream(fos);
    ps.println("\uffff\u4321\u1234");
    ps.close();
  }



  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("missing filename");
      System.exit(1);
    }
    streamChar();
    timing(ReadPerformance::bufferLarger, args[0]);
    timing(ReadPerformance::buffered, args[0]);
    timing(ReadPerformance::readSingle, args[0]);
  }

  private static void timing(Consumer<String> consumer, String file) {
    long s = System.nanoTime();
    consumer.accept(file);
    System.out.println((System.nanoTime() - s) / 1000.0 / 1000);
  }

}
