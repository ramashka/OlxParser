package edu.rdragunov.olxParser.data.factories;

import edu.rdragunov.olxParser.data.interfaces.AdvertDao;
import edu.rdragunov.olxParser.data.interfaces.AdvertService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final public class Factory {
    private static  Factory factory=new Factory();
    private AdvertDao advertDao;
    private AdvertService advertService;

    private Factory(){
        advertDao=(AdvertDao)new ClassPathXmlApplicationContext("spring.xml").getBean("advertDao");
        advertService=(AdvertService)new ClassPathXmlApplicationContext("spring.xml").getBean("advertService");
    }

    public static Factory getFactory(){
        return factory;
    }

    public AdvertDao getAdvertDao(){
        return advertDao;
    }

    public AdvertService getAdvertService(){
        return advertService;
    }
}
