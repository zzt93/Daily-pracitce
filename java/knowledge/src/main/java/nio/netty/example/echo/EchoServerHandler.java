package nio.netty.example.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zzt
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ctx.write(msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
