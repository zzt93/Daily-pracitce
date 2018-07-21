package nio.netty.example.factorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.math.BigInteger;

/**
 * @author zzt
 */
public class NumberEncoder extends MessageToByteEncoder<Number> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Number msg, ByteBuf out) throws Exception {
    // convert to a BigInteger first for easier implementation
    BigInteger v;
    if (msg instanceof BigInteger) {
      v = (BigInteger) msg;
    } else {
      v = new BigInteger(String.valueOf(msg));
    }

    byte[] data = v.toByteArray();
    int length = data.length;

    out.writeByte(((byte) 'F'));
    out.writeByte(length);
    out.writeBytes(data);
  }
}
