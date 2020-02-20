package cn.itcast.test;

import cn.itcast.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        AccountService as = (AccountService)ac.getBean("accountService");

        as.findAll();

    }

//    @Test
//    public void tstr(){
//        String str1 = "a";
//        String str2 = "a";
//        String str3 = new String("a");
//        System.err.println(str1 == str2);
//        System.err.println(str1 == str3);
//        str3 = str3.intern();
//        System.err.println(str1 == str3);
//    }

}
