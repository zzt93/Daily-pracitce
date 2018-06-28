package nio.netty.example.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zzt
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object>{


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    // discard
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
