package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zzt
 */
public class ChannelTest {

  public static void main(String[] args) throws IOException {
    Executors.newScheduledThreadPool(1).scheduleAtFixedRate(ChannelTest::blockClient, 2, 2,
        TimeUnit.SECONDS);

    ServerSocketChannel server = ServerSocketChannel.open();
    server.bind(new InetSocketAddress(9300));
    server.configureBlocking(false);
    Selector selector = Selector.open();
    server.register(selector, SelectionKey.OP_ACCEPT);

    byte[] rotation = new byte[95 * 2];
    for (byte i = ' '; i <= '~'; i++) {
      rotation[i - ' '] = i;
      rotation[i + 95 - ' '] = i;
    }

    while (true) {
      selector.select();
      for (Iterator<SelectionKey> it = selector.selectedKeys().iterator(); it.hasNext(); ) {
        SelectionKey next = it.next();
        it.remove();
        try {
          if (next.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) next.channel();
            SocketChannel accept = channel.accept();
            // have to config blocking mode or IllegalBlockingModeException
            accept.configureBlocking(false);
            SelectionKey key2 = accept.register(selector, SelectionKey.OP_WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(74);
            buffer.put(rotation, 0, 72);
            buffer.put((byte) '\r');
            buffer.put((byte) '\n');
            buffer.flip();
            key2.attach(buffer);
          } else if (next.isWritable()) {
            SocketChannel client = ((SocketChannel) next.channel());
            ByteBuffer buffer = (ByteBuffer) next.attachment();
            if (!buffer.hasRemaining()) {
//              System.out.println(client.toString() + "re-fill");
              buffer.rewind();
              int first = buffer.get();
              buffer.rewind();
              int position = first - ' ' + 1;
              buffer.put(rotation, position, 72);
              buffer.put((byte) '\r');
              buffer.put((byte) '\n');
              buffer.flip();
            }
            client.write(buffer);
//            System.out.println(client.toString() + "write");
          }
        } catch (IOException e) {
          next.cancel();
          next.channel().close();
        }
      }
    }
  }

  private static void blockClient() {
    InetSocketAddress addr = new InetSocketAddress(9300);
    try {
      SocketChannel in = SocketChannel.open(addr);
      ByteBuffer buffer = ByteBuffer.allocate(100);
      for (int i = 0; i < 5; i++) {
        buffer.rewind();
        // may read two times output, i.e. write faster than read
        int read = in.read(buffer);
        System.out.println("Read " + read);
        WritableByteChannel out = Channels.newChannel(System.out);
        buffer.flip();
        out.write(buffer);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
