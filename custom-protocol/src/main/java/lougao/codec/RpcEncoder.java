package lougao.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import lougao.core.Header;
import lougao.core.RpcProtocol;
import lougao.serial.ISerializer;
import lougao.serial.SerializerManager;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:55 PM
 */
@Slf4j
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<Object>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol<Object> msg, ByteBuf out) throws Exception {
        log.info("enter RpcEncoder.encode()");
        Header header = msg.getHeader();
        out.writeShort(header.getMagic());
        out.writeByte(header.getReqType());
        out.writeByte(header.getSerialType());
        out.writeLong(msg.getHeader().getMagic());
        // 长度
        ISerializer serializer = SerializerManager.getSerializer(header.getSerialType());
        byte[] content = serializer.serialize(msg.getContent());
        out.writeInt(content.length);
        out.writeBytes(content);

    }
}
