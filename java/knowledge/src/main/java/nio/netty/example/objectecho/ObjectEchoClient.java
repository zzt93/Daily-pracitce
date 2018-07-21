package nio.netty.example.objectecho;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * @author zzt
 */
public class ObjectEchoClient {

  private static final boolean SSL = System.getProperty("ssl") != null;
  private static final String HOST = System.getProperty("host", "127.0.0.1");
  private static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
  static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

  public static void main(String[] args) throws Exception {
    final SslContext sslContext;
    if (SSL) {
      sslContext = SslContextBuilder.forClient()
          .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
    } else {
      sslContext = null;
    }

    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(group)
          .channel(NioSocketChannel.class)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ChannelPipeline p = ch.pipeline();
              if (sslContext != null) {
                p.addLast(sslContext.newHandler(ch.alloc(), HOST, PORT));
              }
//              p.addLast(new LoggingHandler(LogLevel.INFO));
              p.addLast(new ObjectEncoder(),
                  new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                  new ObjectEchoClientHandler());
            }
          });
      b.connect(HOST, PORT).sync().channel().closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }
  }
}
