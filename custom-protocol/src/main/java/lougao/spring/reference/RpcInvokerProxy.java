package lougao.spring.reference;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lougao.constants.ReqType;
import lougao.constants.SerialType;
import lougao.core.Header;
import lougao.core.RequestHolder;
import lougao.core.RpcFuture;
import lougao.core.RpcProtocol;
import lougao.core.RpcRequest;
import lougao.core.RpcResponse;
import lougao.protocol.NettyClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 12:13 AM
 */
public class RpcInvokerProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 报文
        RpcProtocol<RpcRequest> requestRpcProtocol = new RpcProtocol<>();
//        long requestId = RequestHolder.REQUEST_ID.incrementAndGet();
        long requestId = 1;
        Header header = new Header((short) 1, ReqType.REQUEST.getCode(), SerialType.JAVA_SERIAL.getCode(), requestId);
        requestRpcProtocol.setHeader(header);
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        requestRpcProtocol.setContent(request);

        // 发送
        NettyClient nettyClient = new NettyClient("192.168.0.102", 28080);
        nettyClient.sendRequest(requestRpcProtocol);
        // 这是啥，会导致阻塞 todo lougao
        RpcFuture<RpcResponse> future = new RpcFuture<>(new DefaultPromise<RpcResponse>(new DefaultEventLoop()));

        RequestHolder.REQUEST_MAP.put(requestId, future);
        return future.getPromise().get().getBody();
    }
}
