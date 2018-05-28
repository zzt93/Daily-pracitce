package nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;

/**
 * @author zzt
 */
public class VectoredIO {

  public static void main(String[] args) throws IOException {
    GatheringByteChannel channel = new FileOutputStream("./test").getChannel();
    ByteBuffer bufferOne = ByteBuffer.allocate(4);
    ByteBuffer buffer2 = ByteBuffer.allocate(200);
    bufferOne.asIntBuffer().put(13);
    buffer2.asCharBuffer().put("gatherBytes() reads bytes from different buffers and writes to file channel. "
        + "Note that it uses a single write for both the buffers.");
    channel.write(new ByteBuffer[]{bufferOne,buffer2});
  }

}
