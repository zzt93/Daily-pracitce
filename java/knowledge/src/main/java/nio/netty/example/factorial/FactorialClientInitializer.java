package nio.netty.example.factorial;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.ssl.SslContext;

/**
 * @author zzt
 */
public class FactorialClientInitializer extends ChannelInitializer<SocketChannel> {

  private final SslContext sslContext;

  public FactorialClientInitializer(SslContext sslContext) {
    this.sslContext = sslContext;
  }

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    if (sslContext != null) {
      pipeline
          .addLast(sslContext.newHandler(ch.alloc(), FactorialClient.HOST, FactorialClient.PORT));
    }


    pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
    pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
    // the encoder & decoder are static as these are sharable, i.e. thread safe
    pipeline.addLast(new BigIntegerDecoder());
    pipeline.addLast(new NumberEncoder());

    // then business
    pipeline.addLast(new FactorialClientHandler());
  }
}
