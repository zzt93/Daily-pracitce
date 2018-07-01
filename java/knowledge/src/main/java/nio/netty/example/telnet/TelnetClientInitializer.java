package nio.netty.example.telnet;

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
public class TelnetClientInitializer extends ChannelInitializer<SocketChannel> {

  private static final StringEncoder ENCODER = new StringEncoder();
  private static final StringDecoder DECODER = new StringDecoder();

  private static final TelnetClientHandler CLIENT_HANDLER = new TelnetClientHandler();

  private final SslContext sslContext;

  public TelnetClientInitializer(SslContext sslContext) {
    this.sslContext = sslContext;
  }

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    if (sslContext != null) {
      pipeline.addLast(sslContext.newHandler(ch.alloc(), TelnetClient.HOST, TelnetClient.PORT));
    }

    // Add the text line codec combination first
    pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
    // the encoder & decoder are static as these are sharable, i.e. thread safe
    pipeline.addLast(DECODER);
    pipeline.addLast(ENCODER);

    // then business
    pipeline.addLast(CLIENT_HANDLER);
  }
}
