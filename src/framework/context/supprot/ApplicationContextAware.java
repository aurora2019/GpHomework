package framework.context.supprot;

import framework.context.ApplicationContext;

/**
 * @Author aurora
 * @Description 通过解耦的方式获得ioc容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口
 * 将自动调用setApplicationContext()方法，从而将ioc容器注入到目标类中
 * @Date Created in 19:37 2019/4/11
 * @Modified By
 */
public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext);
}
