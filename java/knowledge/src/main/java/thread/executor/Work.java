package thread.executor;

/**
 * Created by zzt on 5/13/15.
 * <p>
 * Description:
 */
public class Work implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
