package framework.beans.support;

import framework.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author aurora
 * @Description
 * @Date Created in 20:06 2019/4/11
 * @Modified By
 */
public class BeanDefinitionReader {

    // 类名
    private List<String> registryBeanClasses = new ArrayList<>();

    private Properties config = new Properties();
    // 固定配置文件中的可以，相当于xml中的规范
    private final String SCAN_PACKAGE= "scanPackage";
    public BeanDefinitionReader(String... locations){
        // 通过url定位找到其所对应的文件，然后转化为文件流，读取
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));

        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));

    }

    private void doScanner(String property) {

        // 转换文件就，实际就是把.替换/
        URL url = this.getClass().getClassLoader().getResource("/"+property.replaceAll("\\.","/"));
        File classpath = new File(url.getFile());
        for(File file : classpath.listFiles()){
            if(file.isDirectory()){
                doScanner(property + "." +file.getName());
            }else{

                if(!file.getName().endsWith(".class")){continue;}
                String className = (property+"."+file.getName().replace(".class",""));
                registryBeanClasses.add(className);
            }
        }
    }

    // 把配置文件中扫描到的所有的配置信息转换为BeanDefinition对象
    public List<BeanDefinition> loadBeanDefinitions(){
        List<BeanDefinition> result = new ArrayList<>();
        for (String className : registryBeanClasses) {
            BeanDefinition beanDefinition = doCreatedBeanDefinition(className);
            if (null == beanDefinition) {continue;}

            result.add(beanDefinition);

        }

        return result;
    }

    // 把每个配置信息解析成一个BeanDefinition
    private BeanDefinition doCreatedBeanDefinition(String className) {

        try {
            Class<?> beanClass = Class.forName(className);
            // 是不是接口，用它的实现类作为beanClassName
            if (beanClass.isInterface()) {
                return null;
            }
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(beanClass.getSimpleName());
            return beanDefinition;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
