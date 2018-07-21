package nio.netty.example.factorial;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.math.BigInteger;

/**
 * @author zzt
 */
@Sharable
public class FactorialServerHandler extends SimpleChannelInboundHandler<BigInteger> {

  private BigInteger lastMultiplier = new BigInteger("1");
  private BigInteger factorial = new BigInteger("1");

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.err.format("Factorial of %,d is: %,d%n", lastMultiplier, factorial);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BigInteger msg) throws Exception {
    lastMultiplier = msg;
    factorial = factorial.multiply(msg);
    ctx.writeAndFlush(factorial);
  }
}
