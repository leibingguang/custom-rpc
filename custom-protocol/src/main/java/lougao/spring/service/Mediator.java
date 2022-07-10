package lougao.spring.service;

import lougao.core.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 3:51 PM
 */
public class Mediator {
    public static final Map<String, BeanMethod> beanMethodMap = new ConcurrentHashMap<>();
    public volatile static Mediator mediator = null;

    public static Mediator getMediator() {
        if (mediator == null) {
            synchronized (Mediator.class) {
                if (mediator == null) {
                    mediator = new Mediator();
                }
            }
        }
        return mediator;
    }

    public static String getBeanMethodKey(String className, String methodName) {
        return className + "#" + methodName;
    }

    public Object process(RpcRequest request) {
        String className = request.getClassName();
        String methodName = request.getMethodName();
        String key = getBeanMethodKey(className, methodName);
        BeanMethod beanMethod = beanMethodMap.get(key);
        if(beanMethod == null) {
            return null;
        }
        Method method = beanMethod.getMethod();
        Object object = beanMethod.getObject();
        try {
            return method.invoke(object, request.getParams());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
