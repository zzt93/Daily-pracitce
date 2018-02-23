package nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * @author zzt
 *
 * telnet addr port
 */
public class EchoServer {

  private final int port;
  private final ByteBuffer content;

  public EchoServer(ByteBuffer data, String contentType, int port, String encoding)
      throws UnsupportedEncodingException {
    this.port= port;
    String header = "HTTP/1.0 200 OK\r\n"
        + "Server: NonblockingSingleFileHTTPServer\r\n"
        + "Content-length: " + data.limit() + "\r\n"
        + "Content-type: " + contentType + "\r\n\r\n";
    content = ByteBuffer.allocate(header.length() + data.limit());
    content.put(header.getBytes("us-ascii"));
    content.put(data);
    content.flip();
  }

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println("Usage: java EchoServer file port");
      return;
    }

    String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
    Path file = Paths.get(args[0]);
    byte[] bytes = Files.readAllBytes(file);
    ByteBuffer buffer = ByteBuffer.wrap(bytes);

    int port;
    try {
      port = Integer.parseInt(args[1]);
      if (port < 0 || port > 65536) {
        port = 80;
      }
    } catch (NumberFormatException e) {
      port = 80;
    }

    String encoding = "utf8";
    new EchoServer(buffer, contentType, port, encoding).run();
  }

  private void run() throws IOException {
    ServerSocketChannel server = ServerSocketChannel.open();
    server.bind(new InetSocketAddress(port));
    server.configureBlocking(false);
    Selector selector = Selector.open();
    server.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      selector.select();
      for (Iterator<SelectionKey> it = selector.selectedKeys().iterator(); it.hasNext(); ) {
        SelectionKey next = it.next();
        it.remove();
        if (next.isAcceptable()) {
          ServerSocketChannel channel = (ServerSocketChannel) next.channel();
          SocketChannel accept = channel.accept();
          accept.configureBlocking(false);
          accept.register(selector, SelectionKey.OP_READ);
//          accept.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
        } else if (next.isReadable()) {
          SocketChannel client = (SocketChannel) next.channel();
          ByteBuffer buffer = ByteBuffer.allocate(4096);
          client.read(buffer);
          next.interestOps(SelectionKey.OP_WRITE);
          next.attach(content.duplicate());
        } else if (next.isWritable()) {
          SocketChannel client = (SocketChannel) next.channel();
          ByteBuffer buffer = (ByteBuffer) next.attachment();
          if (buffer.hasRemaining()) {
            client.write(buffer);
          } else {
            client.close();
          }
        }
      }
    }
  }

}
