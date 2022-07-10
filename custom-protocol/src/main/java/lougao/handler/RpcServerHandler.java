package lougao.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lougao.constants.ReqType;
import lougao.core.Header;
import lougao.core.RpcProtocol;
import lougao.core.RpcRequest;
import lougao.core.RpcResponse;
import lougao.spring.service.Mediator;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:03 PM
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcProtocol<RpcRequest> requestRpcProtocol = (RpcProtocol<RpcRequest>) msg;
        RpcRequest rpcRequest = requestRpcProtocol.getContent();

//        Class clazz = Class.forName(rpcRequest.getClassName());
//        Method method = clazz.getDeclaredMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());

        // 获取调用对象
//        Object beanObj = SpringContextManager.getBean(clazz);
//        Object result = method.invoke(beanObj, rpcRequest.getParams());
        Object result = Mediator.getMediator().process(rpcRequest);
        // 写回响应
        RpcProtocol<RpcResponse> responseRpcProtocol = new RpcProtocol<>();
        Header header = new Header(requestRpcProtocol.getHeader().getMagic(), ReqType.RESPONSE.getCode(), requestRpcProtocol.getHeader().getSerialType(), requestRpcProtocol.getHeader().getRequestId());
        responseRpcProtocol.setHeader(header);
        RpcResponse response = new RpcResponse();
        response.setBody(result);
        response.setMsg("success");
        responseRpcProtocol.setContent(response);
        ctx.channel().writeAndFlush(responseRpcProtocol);
    }
}
