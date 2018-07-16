package nio.netty.example.telnet;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.InetAddress;
import java.util.Date;

/**
 * @author zzt
 */
@Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
    ctx.write("It is " + new Date() + " now.\r\n");
    ctx.flush();
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

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    boolean close = false;
    String res;
    if (msg.isEmpty()) {
      res = "Please type something.\r\n";
    } else if ("bye".equals(msg.toLowerCase())) {
      close = true;
      res = "Have a good day";
    } else {
      res = "Did you say '" + msg + "'?\r\n";
    }
    // We do not need to write a ChannelBuffer here.
    // We know the encoder inserted at TelnetPipelineFactory will do the conversion.
    ChannelFuture future = ctx.write(res);
    if (close) {
      // after write is finished, close the channel
      future.addListener(ChannelFutureListener.CLOSE);
    }
  }
}
