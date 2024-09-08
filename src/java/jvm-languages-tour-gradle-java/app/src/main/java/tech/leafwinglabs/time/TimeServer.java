package tech.leafwinglabs.time;

public class TimeServer {

  public class TimeDecoder extends ByteToMessageDecoder { // (1)
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
      if (in.readableBytes() < 4) {
        return; // (3)
      }

      out.add(in.readBytes(4)); // (4)

      b.handler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
        }
      });
    }
  }

  public class TimeDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(
        ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
      out.add(in.readBytes(4));
    }
  }

}
