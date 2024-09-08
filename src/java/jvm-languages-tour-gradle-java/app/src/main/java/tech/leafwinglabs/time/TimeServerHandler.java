package tech.leafwinglabs.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  /**
   * Allocates an asynchronous buffer for the current time and writes it to the channel.
   */
  @Override
  public void channelActive(final ChannelHandlerContext ctx) {
    final ByteBuf time = ctx.alloc().buffer(4);
    time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

    final ChannelFuture f = ctx.writeAndFlush(time);
    f.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) {
        assert f == future;
        ctx.close();
      }
    });
  }

  /**
   * Logs the exception and closes the channel.
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}