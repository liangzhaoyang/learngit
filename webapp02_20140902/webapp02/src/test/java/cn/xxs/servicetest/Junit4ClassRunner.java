package cn.xxs.servicetest;

import java.io.FileNotFoundException;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class Junit4ClassRunner extends SpringJUnit4ClassRunner {  
	  
    static {  
        try {  
            Log4jConfigurer.initLogging("classpath:conf/log4j.properties");  
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }  
    }  
      
    public Junit4ClassRunner(Class<?> clazz) throws org.junit.runners.model.InitializationError {  
        super(clazz);  
    }  
  
} 