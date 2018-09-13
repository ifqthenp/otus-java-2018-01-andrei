package com.otus.hw_16.back;

import com.otus.hw_16.back.util.DatabaseUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BackMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        DatabaseUtil.emulateLoad(context);
    }

}
