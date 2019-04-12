package framework.context;

import framework.beans.BeanFactory;
import framework.beans.BeanWrapper;
import framework.beans.config.BeanDefinition;
import framework.beans.support.BeanDefinitionReader;
import framework.beans.support.DefaultListableBeanFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author aurora
 * @Description
 * @Date Created in 18:56 2019/4/11
 * @Modified By
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    BeanDefinitionReader reader;

    private  String[] configLocations;
    public ApplicationContext(String... configLocations){
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public void refresh(){
        // 1、定位配置文件（BeanDefinitionReader 核心类）
        // 拿到所有的注解
        reader = new BeanDefinitionReader(this.configLocations);
        // 2. 加载配置文件，扫描相关的类，把他们封装成BeanDefinition
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        // 3. 注册，把配置信息放到容器里面（伪ioc容器）
        doRegisterBeanDefinition(beanDefinitions);
        // 4. 把不是延时加载的类要提前初始化
        doAutowired();
    }
    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            // 非延时加载
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                getBean(beanName);
            }

        }

    }

    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) {

        for(BeanDefinition beanDefinition:beanDefinitions){

            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    // 依赖注入的方法
    @Override
    public Object getBean(String beanName) {
        //1. 初始化
        instantiateBean(beanName,new BeanDefinition());

        // 循环注入
        //2. 注入
        populateBean(beanName,new BeanDefinition(),new BeanWrapper());

        return null;
    }

    private void populateBean(String beanName, BeanDefinition beanDefinition, BeanWrapper beanWrapper) {
    }

    private void instantiateBean(String beanName, BeanDefinition beanDefinition) {
    }
}
