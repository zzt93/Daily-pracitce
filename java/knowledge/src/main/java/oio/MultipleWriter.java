package oio;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zzt
 */
public class MultipleWriter {

  public static void main(String[] args) {
    int nThreads = 20;
    ExecutorService service = Executors.newFixedThreadPool(nThreads);
    for (int i = 0; i < nThreads; i++) {
      service.submit(() -> {
        try (BufferedWriter multiWriter = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream("multi_writer")))) {
          for (int x = 0; x < 1000; x++) {
            multiWriter.write(Thread.currentThread().getName()
                + ": lao peng is bad guy. A service is a content repository that allows collaboration between multiple users. It uses sets of two replicated file servers in different racks for reliability. The service needs to avoid writing data simultaneously to both file servers in a set, because doing so could result in data corruption (and possibly unrecoverable data)."
                + "Each pair of file servers has one leader and one follower. The servers monitor each other via heartbeats. If one file server cannot contact its partner, it issues a STONITH (Shoot The Other Node in the Head) command to its partner node to shut the node down, and then takes mastership of its files. This practice is an industry standard method of reducing split-brain instances, although as we shall see, it is conceptually unsound.\n");
          }
          multiWriter.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

}
