package lougao.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息头部分
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:40 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header implements Serializable {
    public Header(short magic, byte reqType, byte serialType, long requestId) {
        this.magic = magic;
        this.reqType = reqType;
        this.serialType = serialType;
        this.requestId = requestId;
    }

    /**
     * 魔数 2字节
     */
    private short magic;
    /**
     * 请求类型 1字节
     *
     * @see lougao.constants.ReqType
     */
    private byte reqType;

    /**
     * 序列化类型 1字节
     *
     * @see lougao.constants.SerialType
     */
    private byte serialType;

    /**
     * 请求id，唯一 8字节
     */
    private long requestId;

    /**
     * 报文长度 4字节
     */
    private int length;

}
