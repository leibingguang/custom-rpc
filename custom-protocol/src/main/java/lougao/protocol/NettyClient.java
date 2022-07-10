package lougao.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lougao.core.RpcProtocol;
import lougao.core.RpcRequest;
import lougao.handler.RpcClientHandler;
import lougao.handler.RpcClientInitializer;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:41 PM
 */
public class NettyClient {
    Bootstrap bootstrap = null;
    private String serverAddress;
    private int port;

    public NettyClient(String serverAddress, int port) {
        EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new RpcClientInitializer());

        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void sendRequest(RpcProtocol<RpcRequest> request) {
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(serverAddress, port).sync();

            channelFuture.channel().writeAndFlush(request).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
