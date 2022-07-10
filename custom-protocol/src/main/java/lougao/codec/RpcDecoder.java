package lougao.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import lougao.constants.ReqType;
import lougao.constants.SerialType;
import lougao.core.Header;
import lougao.core.RpcProtocol;
import lougao.core.RpcRequest;
import lougao.core.RpcResponse;
import lougao.serial.ISerializer;
import lougao.serial.SerializerManager;

import java.util.List;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:55 PM
 */
@Slf4j
public class RpcDecoder extends ByteToMessageDecoder {

    /**
     * 目的是往ByteBuf中读取字节信息，写入到out中
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("enter RpcDecoder.decode()");
        short magic = in.readShort();
        byte reqTypeCode = in.readByte();
        byte serialTypeCode = in.readByte();
        long requestId = in.readLong();
        int length = in.readInt();
        Header header = new Header(magic, reqTypeCode, serialTypeCode, requestId, length);
        // 获取序列化
        ISerializer serializer = SerializerManager.getSerializer(serialTypeCode);

        ReqType reqType = ReqType.getReqType(reqTypeCode);
        byte[] content = null;
        if (length > 0) {
            content = new byte[length];
            in.readBytes(content);
        }
        switch (reqType) {
            case REQUEST:
                RpcRequest request = serializer.deserialize(content, RpcRequest.class);
                RpcProtocol<RpcRequest> requestRpcProtocol = new RpcProtocol<>();
                requestRpcProtocol.setHeader(header);
                requestRpcProtocol.setContent(request);
                out.add(requestRpcProtocol);
                break;
            case RESPONSE:
                RpcResponse response = serializer.deserialize(content, RpcResponse.class);
                RpcProtocol<RpcResponse> rpcResponseRpcProtocol = new RpcProtocol<>();
                rpcResponseRpcProtocol.setHeader(header);
                rpcResponseRpcProtocol.setContent(response);
                out.add(rpcResponseRpcProtocol);
                break;
            case HEART_BEAT:
                // TODO
                break;
            default:
                break;

        }
    }
}
