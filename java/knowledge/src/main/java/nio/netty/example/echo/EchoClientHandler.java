package nio.netty.example.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zzt
 *
 * io.netty.handler.logging.LoggingHandler channelRegistered
 * REGISTERED
 * io.netty.handler.logging.LoggingHandler connect
 * CONNECT: /127.0.0.1:8007
 * io.netty.handler.logging.LoggingHandler channelActive
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

  private final ByteBuf first;

  public EchoClientHandler() {
    first = Unpooled.buffer(EchoClient.SIZE);
    first.writeBytes("w a n, I L Y".getBytes());
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    super.channelRegistered(ctx);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ctx.writeAndFlush(first);
  }

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
