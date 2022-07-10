package lougao.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Promise;
import lougao.core.RequestHolder;
import lougao.core.RpcFuture;
import lougao.core.RpcProtocol;
import lougao.core.RpcResponse;

/**
 * 客户端处理接收到的消息`
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:04 PM
 */
public class RpcClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcProtocol<RpcResponse> rpcResponseRpcProtocol = (RpcProtocol<RpcResponse>) msg;
        long requestId = rpcResponseRpcProtocol.getHeader().getRequestId();
        RpcFuture<RpcResponse> rpcFuture = RequestHolder.REQUEST_MAP.remove(requestId);
        System.out.println(requestId);
        rpcFuture.getPromise().setSuccess(rpcResponseRpcProtocol.getContent());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
