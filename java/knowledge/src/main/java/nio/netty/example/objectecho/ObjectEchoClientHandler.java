package nio.netty.example.objectecho;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzt
 *
 * io.netty.handler.logging.LoggingHandler channelRegistered
 * REGISTERED
 * io.netty.handler.logging.LoggingHandler connect
 * CONNECT: /127.0.0.1:8007
 * io.netty.handler.logging.LoggingHandler channelActive
 */
public class ObjectEchoClientHandler extends ChannelInboundHandlerAdapter {

  private final List<Character> first;

  public ObjectEchoClientHandler() {
    first = new ArrayList<>(ObjectEchoClient.SIZE * 4);
    for (int i = 0; i < ObjectEchoClient.SIZE; i++) {
      first.add('w');
      first.add('a');
      first.add('n');
      first.add(';');
    }
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
    System.out.println("client received: " + msg);
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
