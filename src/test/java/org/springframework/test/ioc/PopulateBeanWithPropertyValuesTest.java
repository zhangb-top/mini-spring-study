package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.bean.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class PopulateBeanWithPropertyValuesTest {
    @Test
    public void testPopulateBeanWithPropertyValues() {
        // 1、获得beanFactory对象
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        // 2、定义bean的属性集合
        PropertyValues propertyValues = new PropertyValues();
        // 3、为bean的属性集合添加属性
        propertyValues.addPropertyValue(new PropertyValue("name", "张三"));
        propertyValues.addPropertyValue(new PropertyValue("age", 18));
        // 4、创建定义bean的对象
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        // 5、注册定义bean的对象
        defaultListableBeanFactory.registryBeanDefinition("Person", beanDefinition);
        // 6、获取工厂里面的bean对象
        Person person = (Person) defaultListableBeanFactory.getBean("Person");
        // 7、使用bean
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("张三");
        assertThat(person.getAge()).isEqualTo(18);
    }
}
