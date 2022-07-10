package lougao.spring.service;

import lougao.annotation.CustomRpcService;
import lougao.protocol.NettyServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 3:12 PM
 */
@Component
public class RpcProviderBean implements InitializingBean, BeanPostProcessor {

    private String serverAddress;

//    public RpcProviderBean(int port) throws UnknownHostException {
//        InetAddress inetAddress = InetAddress.getLocalHost();
//        this.serverAddress = inetAddress.getHostAddress();
//        this.port = port;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        this.serverAddress = inetAddress.getHostAddress();
        new Thread(() -> {
            new NettyServer().startServer(serverAddress, 28080);
        }).start();
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(CustomRpcService.class)) {
            Method[] methods = bean.getClass().getMethods();
            String interfaceName = bean.getClass().getInterfaces()[0].getName();
            for (Method method : methods) {
                String methodName = method.getName();
                String key = Mediator.getBeanMethodKey(interfaceName, methodName);
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setMethod(method);
                beanMethod.setObject(bean);
                Mediator.beanMethodMap.put(key, beanMethod);
            }
        }
        return bean;

    }
}
