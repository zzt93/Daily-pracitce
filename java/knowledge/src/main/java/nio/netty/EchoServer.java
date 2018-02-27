package nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author zzt
 */
public class EchoServer {

  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  private static class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf buffer = (ByteBuf) msg;
      System.out.println("Server received: " + buffer.toString(CharsetUtil.UTF_8));
      ctx.write(buffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
          .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    if (args.length != 1) {
      System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
      return;
    }

    int port = Integer.parseInt(args[0]);
    new EchoServer(port).start();
  }

  private void start() throws InterruptedException {
    EchoServerHandler handler = new EchoServerHandler();
    NioEventLoopGroup group = new NioEventLoopGroup();
    ServerBootstrap boot = new ServerBootstrap();
    boot.group(group)
        .channel(NioServerSocketChannel.class)
        .localAddress(new InetSocketAddress(port))
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(handler);
          }
        });
    ChannelFuture f = null;
    try {
      f = boot.bind().sync();
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      group.shutdownGracefully().sync();
    }
  }
}
