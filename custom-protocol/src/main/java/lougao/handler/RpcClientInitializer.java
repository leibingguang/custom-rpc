package lougao.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import lougao.codec.RpcDecoder;
import lougao.codec.RpcEncoder;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 10:58 PM
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LoggingHandler())
                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 12, 4, 0, 0))
                .addLast(new RpcEncoder())
                .addLast(new RpcDecoder())
                .addLast(new RpcClientHandler());
    }
}
