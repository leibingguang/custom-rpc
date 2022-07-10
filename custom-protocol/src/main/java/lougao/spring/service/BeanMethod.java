package lougao.spring.service;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 3:22 PM
 */
@Data
public class BeanMethod {
    /**
     * 对象
     */
    private Object object;
    /**
     * 方法
     */
    private Method method;
}
