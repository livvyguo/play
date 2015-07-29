package org.lvy.play;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by livvy on 15/7/28.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx.writeAndFlush(msg);
//        ByteBuf in = (ByteBuf) msg;

        try {
//            while (in.isReadable()) { // (1)
//
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
            ctx.writeAndFlush(msg);
        } finally {
            //ReferenceCountUtil.release(msg); // (2)
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
