package framework.beans.config;

import com.sun.istack.internal.Nullable;

/**
 * @Author aurora
 * @Description
 * @Date Created in 19:20 2019/4/11
 * @Modified By
 */

public class BeanDefinition {

    private  String beanClassName;
    private  boolean lazyInit = false;
    private String  factoryBeanName= null;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    /**
     * Specify the bean class name of this bean definition.
     * <p>The class name can be modified during bean factory post-processing,
     * typically replacing the original class name with a parsed variant of it.
     * @see #setFactoryBeanName
     */
    //void setBeanClassName(@Nullable String beanClassName);

    /**
     * Return whether this bean should be lazily initialized, i.e. not
     * eagerly instantiated on startup. Only applicable to a singleton bean.
     */
   // boolean isLazyInit();

    /**
     * Specify the factory bean to use, if any.
     * This the name of the bean to call the specified factory method on.
     */
    //void setFactoryBeanName(@Nullable String factoryBeanName);
}
