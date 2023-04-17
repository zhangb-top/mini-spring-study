package org.springframework.test.ioc.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author derekyi
 * @date 2020/11/24
 */
public class Person implements InitializingBean, DisposableBean {

    private String name;

    private int age;

    private Car car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void customInitMethod() {
        System.out.println("I was born in the method named customInitMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("I was born in the method named afterPropertiesSet");
    }

    public void customDestroyMethod() {
        System.out.println("I died in the method named customDestroyMethod");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("I died in the method named destroy");
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
