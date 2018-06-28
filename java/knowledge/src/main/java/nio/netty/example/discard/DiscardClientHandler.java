package nio.netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zzt
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {


  private ByteBuf content;
  private ChannelHandlerContext ctx;
  private ChannelFutureListener trafficGenerator = future -> {
    if (future.isSuccess()) {
      generateTraffic();
    } else {
      future.cause().printStackTrace();
      future.channel().close();
    }
  };

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    this.ctx = ctx;
    // fill message
    content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);
    // send initial messages
    generateTraffic();
  }

  private void generateTraffic() {
    ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    // discard
  }
}
