package lougao.spring.reference;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 5:06 PM
 */
public class RpcReferenceBean implements FactoryBean<Object> {
    private Object object;
    private Class<?> interfaceClass;

    @Override
    public Object getObject() throws Exception {
        return object;
    }

    public void init() {
        this.object = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new RpcInvokerProxy());
    }

    @Override
    public Class<?> getObjectType() {
        return this.interfaceClass;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
}
