package org.lvy.play.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * Created by livvy on 15/7/28.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf m = (ByteBuf) msg;
        try {
            long currentTime = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTime));
            ctx.close();
        } finally {
            m.release();
        }



    }
}
