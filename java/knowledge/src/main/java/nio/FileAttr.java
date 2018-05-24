package nio;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

/**
 * @author zzt
 */
public class FileAttr {

  public static void main(String[] args) throws IOException {
//    attrView();
    fileStore();
  }

  private static void attrView() {
    FileSystem fs = FileSystems.getDefault();
    Set<String> views = fs.supportedFileAttributeViews();
    for (String view : views) {
      System.out.println(view);
    }
  }

  public static void fileStore() throws IOException {
    System.out.println(Files.getFileStore(Paths.get("pom.xml")));
    FileSystem fs = FileSystems.getDefault();
    for (FileStore store : fs.getFileStores()) {
      try {
        long total_space = store.getTotalSpace() / 1024;
        long used_space = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
        long available_space = store.getUsableSpace() / 1024;
        boolean is_read_only = store.isReadOnly();
        System.out.println("--- " + store.name() + " --- " + store.type());
        System.out.println("Total space: " + total_space);
        System.out.println("Used space: " + used_space);
        System.out.println("Available space: " + available_space);
        System.out.println("Is read only? " + is_read_only);
      } catch (IOException e) {
        System.err.println(e);
      } }

  }
}
