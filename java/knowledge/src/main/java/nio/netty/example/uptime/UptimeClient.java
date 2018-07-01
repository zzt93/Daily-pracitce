package nio.netty.example.uptime;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author zzt
 */
public class UptimeClient {

  static final int RECONNECT_DELAY = Integer.parseInt(System.getProperty("reconnectDelay", "2"));
  static final String HOST = System.getProperty("host", "127.0.0.1");
  static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
  private static final int READ_TIMEOUT = Integer.parseInt(System.getProperty("readTimeout", "5"));
  private static Bootstrap b = new Bootstrap();
  private static UptimeClientHandler handler = new UptimeClientHandler();

  public static void main(String[] args) throws Exception {
    EventLoopGroup group = new NioEventLoopGroup();
    b.group(group)
        .channel(NioSocketChannel.class)
        .remoteAddress(HOST, PORT)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline p = ch.pipeline();
            p.addLast(new LoggingHandler(LogLevel.INFO));
            // Triggers an IdleStateEvent when a Channel has not performed
            // read, write, or both operation for a while.
            // Use context's executor to schedule task to repeat check
            p.addLast(new IdleStateHandler(READ_TIMEOUT, 0, 0), handler);
          }
        });
    b.connect();
  }

  static void connect() {
    b.connect().addListener((ChannelFutureListener) future -> {
      if (future.cause() != null) {
        handler.startTime = -1;
        handler.println("Failed to connect: " + future.cause());
      }
    });
  }
}
