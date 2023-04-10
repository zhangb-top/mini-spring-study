package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Person;
import org.springframework.test.ioc.common.CustomBeanFactoryPostProcessor;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {
    @Test
    public void testBeanFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 在BeanDefinition创建完，bean未创建时添加
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        // 修改了xml配置文件中person的name值为王麻子，注意输出结果
        System.out.println(person);
    }
}
