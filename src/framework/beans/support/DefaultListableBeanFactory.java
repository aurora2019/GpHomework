package framework.beans.support;

import framework.beans.config.BeanDefinition;
import framework.context.supprot.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author aurora
 * @Description
 * @Date Created in 19:15 2019/4/11
 * @Modified By
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    /** Map of bean definition objects, keyed by bean name */
    //BeanDefinition:存储配置文件信息的载体
    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
}
