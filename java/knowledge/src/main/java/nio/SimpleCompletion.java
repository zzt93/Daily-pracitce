package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.CompletionHandler;
import java.nio.channels.WritableByteChannel;

/**
 * @author zzt
 */
public class SimpleCompletion implements CompletionHandler<Integer, ByteBuffer> {

  @Override
  public void completed(Integer result, ByteBuffer attachment) {
    attachment.flip();
    WritableByteChannel out = Channels.newChannel(System.out);
    try {
      out.write(attachment);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void failed(Throwable exc, ByteBuffer attachment) {

  }
}
