package framework.beans;

/**
 * @Author aurora
 * @Description 单例工厂的顶层设计
 * @Date Created in 18:51 2019/4/11
 * @Modified By
 */
public interface BeanFactory {
    /**
     * ioc容器单例  全局访问点
     * 根据beanName从Ioc容器中获得一个实例
     *
     * 延时加载问题？doGetBean
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
