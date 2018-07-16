package nio.netty.example.securechat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author zzt
 */
public class SecureChatServerInitializer extends ChannelInitializer<SocketChannel> {

  private static final StringEncoder ENCODER = new StringEncoder();
  private static final StringDecoder DECODER = new StringDecoder();

  private final SslContext sslContext;

  public SecureChatServerInitializer(SslContext sslContext) {
    this.sslContext = sslContext;
  }

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    pipeline.addLast(sslContext.newHandler(ch.alloc()));

    // Add the text line codec combination first
    pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
    // the encoder & decoder are static as these are sharable, i.e. thread safe
    pipeline.addLast(DECODER);
    pipeline.addLast(ENCODER);

    // then business
    pipeline.addLast(new SecureChatServerHandler());
  }
}
