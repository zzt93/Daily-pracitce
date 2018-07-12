package nio.netty.example.qotm;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * @author zzt
 */
public class QuoteOfTheMomentClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
    System.err.println(msg);
    String res = msg.content().toString(CharsetUtil.UTF_8);
    if (res.startsWith("QOTM: ")) {
      System.out.println("Quote of the moment: " + res.substring(6));
      ctx.close();
    }
  }

}
