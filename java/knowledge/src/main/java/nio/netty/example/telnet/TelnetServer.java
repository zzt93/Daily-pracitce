package nio.netty.example.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @author zzt
 */
public class TelnetServer {

  private static final boolean SSL = System.getProperty("ssl") != null;
  private static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

  public static void main(String[] args) throws Exception {
    final SslContext sslContext;
    if (SSL) {
      SelfSignedCertificate ssc = new SelfSignedCertificate();
      sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
    } else {
      sslContext = null;
    }

    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new TelnetServerInitializer(sslContext));
      b.bind(PORT).sync().channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}
