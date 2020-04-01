package com.sqt.edu.student.test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-23 18:00
 */
public class SpringTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/spring-test.xml");
        Home home = applicationContext.getBean("home",Home.class);
        System.out.println(home);
        ClassPathResource resource = new ClassPathResource("spring/spring-test.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        MyApplicationAware applicationAware = (MyApplicationAware) factory.getBean("myApplicationAware");
        applicationAware.display();

    }
}
