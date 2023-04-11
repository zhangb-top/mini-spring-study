package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class ApplicationContextTest {
    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring" +
                        ".xml");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
    }
}
