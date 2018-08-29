package thread;

public class CtrlC {

  public static void main(String[] args) {
    while (!Thread.interrupted()) {

    }
    System.out.println("exit");
  }
}
