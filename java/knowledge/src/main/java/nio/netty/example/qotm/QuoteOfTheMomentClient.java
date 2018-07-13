package nio.netty.example.qotm;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;

/**
 * @author zzt
 */
public class QuoteOfTheMomentClient {

  private static final int PORT = Integer.parseInt(System.getProperty("port", "7686"));

  public static void main(String[] args) throws Exception {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(group)
          .channel(NioDatagramChannel.class)
          .option(ChannelOption.SO_BROADCAST, true)
          .handler(new QuoteOfTheMomentClientHandler());

      // because using broadcast, using bind rather than connect
      Channel channel = b.bind(0).sync().channel();

      channel.writeAndFlush(new DatagramPacket(
          Unpooled.copiedBuffer("QOTM?", CharsetUtil.UTF_8),
          // broadcast to PORT
          SocketUtils.socketAddress("255.255.255.255", PORT)
      )).sync();

      if (!channel.closeFuture().await(5000)) {
        System.err.println("QOTM request time out.");
      }
    } finally {
      group.shutdownGracefully();
    }
  }

}
