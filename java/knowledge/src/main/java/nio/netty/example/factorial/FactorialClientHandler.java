package nio.netty.example.factorial;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zzt
 */
public class FactorialClientHandler extends SimpleChannelInboundHandler<BigInteger> {

  private ChannelHandlerContext ctx;
  private int receivedMessages;
  private int next = 1;
  final BlockingQueue<BigInteger> answer = new LinkedBlockingQueue<>();

  public BigInteger getFactorial() {
    boolean interrupted = false;
    try {
      for (;;) {
        try {
          return answer.take();
        } catch (InterruptedException e) {
          interrupted = true;
        }
      }
    } finally {
      if (interrupted) {
        Thread.currentThread().interrupt();
      }
    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    this.ctx = ctx;
    sendNumbers();
  }

  private void sendNumbers() {
    ChannelFuture future = null;
    for (int i = 0; i < 4096 && next <= FactorialClient.COUNT; i++) {
      future = ctx.write(next);
      next++;
    }
    if (next<= FactorialClient.COUNT) {
      assert  future != null;
      future.addListener(numberSender);
    }
  }

  private final ChannelFutureListener numberSender = future -> {
    if (future.isSuccess()) {
      sendNumbers();
    } else {
      future.cause().printStackTrace();
      future.channel().close();
    }
  };

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BigInteger msg) throws Exception {
    receivedMessages++;
    if (receivedMessages == FactorialClient.COUNT) {
      ctx.channel().close().addListener((ChannelFutureListener) future -> {
        boolean offer = answer.offer(msg);
        assert offer;
      });
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
