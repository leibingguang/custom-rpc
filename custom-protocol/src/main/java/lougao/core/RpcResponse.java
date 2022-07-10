package lougao.core;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应对象
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:46 PM
 */
@Data
public class RpcResponse implements Serializable {
    /**
     * 响应消息体
     */
    private Object body;
    /**
     * 响应状态消息
     */
    private String msg;
}
