package lougao.core;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求对象
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:45 PM
 */
@Data
public class RpcRequest implements Serializable {
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */

    private Class[] paramTypes;
    /**
     * 参数
     */
    private Object[] params;

}
