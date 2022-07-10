package lougao.spring.reference;

import lougao.annotation.CustomReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 5:13 PM
 */
@Configuration
public class SpringRpcReferencePostProcessor implements ApplicationContextAware, BeanClassLoaderAware, BeanFactoryPostProcessor {
    private ApplicationContext context;
    private ClassLoader classLoader;

    //保存发布的引用bean的信息
    private final Map<String, BeanDefinition> rpcRefBeanDefinition=new ConcurrentHashMap<>();


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null) {
                Class<?> clazz = ClassUtils.resolveClassName(beanClassName, this.classLoader);
                ReflectionUtils.doWithFields(clazz, this::parseRpcReference);
            }
        }
        BeanDefinitionRegistry registry=(BeanDefinitionRegistry)beanFactory;
        this.rpcRefBeanDefinition.forEach((beanName,beanDefinition)->{
            if(context.containsBean(beanName)){
                return;
            }
            registry.registerBeanDefinition(beanName,beanDefinition);
        });
    }

    private void parseRpcReference(Field field) {
        CustomReference customReferenceAnnotation = AnnotationUtils.getAnnotation(field, CustomReference.class);
        if (customReferenceAnnotation != null) {
            BeanDefinitionBuilder builder=BeanDefinitionBuilder.
                    genericBeanDefinition(RpcReferenceBean.class);
            builder.setInitMethodName("init");
            builder.addPropertyValue("interfaceClass",field.getType());
            /*builder.addPropertyValue("serviceAddress",rpcClientProperties.getServiceAddress());
            builder.addPropertyValue("servicePort",rpcClientProperties.getServicePort());*/
//            builder.addPropertyValue("registryAddress",rpcClientProperties.getRegistryAddress());
//            builder.addPropertyValue("registryType",rpcClientProperties.getRegistryType());

            BeanDefinition beanDefinition=builder.getBeanDefinition();
            rpcRefBeanDefinition.put(field.getName(),beanDefinition);
        }

    }

}
