package com.acts.cdac.SpringFirst;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("first.xml")){
        	Cards objCards = (Cards) context.getBean("objCards");
        	System.out.println(objCards);
        }
        
    }
}
