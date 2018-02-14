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

/**
 * @author zzt
 */
public class ChannelTest {

  public static void main(String[] args) throws IOException {
    blockClient();

    Selector selector = Selector.open();
    ServerSocketChannel server = ServerSocketChannel.open();
    server.bind(new InetSocketAddress(9300));
    server.configureBlocking(false);
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
              buffer.rewind();
              int first = buffer.get();
              buffer.rewind();
              int position = first - ' ' + 1;
              buffer.put(rotation, position, 72);
              buffer.put((byte) '\n');
              buffer.flip();
            }
            client.write(buffer);
          }
        } catch (IOException e) {
          next.cancel();
          next.channel().close();
        }
      }
    }
  }

  private static void blockClient() throws IOException {
    InetSocketAddress addr = new InetSocketAddress(9300);
    SocketChannel in = SocketChannel.open(addr);
    ByteBuffer buffer = ByteBuffer.allocate(100);
    int read = in.read(buffer);
    WritableByteChannel out = Channels.newChannel(System.out);
    buffer.flip();
    out.write(buffer);
  }

}
