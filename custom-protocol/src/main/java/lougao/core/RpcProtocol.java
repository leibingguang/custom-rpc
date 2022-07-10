package lougao.core;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息完整报文
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:47 PM
 */
@Data
public class RpcProtocol<T> implements Serializable {
    private Header header;
    private T content;
}
