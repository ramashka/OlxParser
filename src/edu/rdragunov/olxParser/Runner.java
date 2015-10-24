package edu.rdragunov.olxParser;

import edu.rdragunov.olxParser.data.entities.Advert;
import edu.rdragunov.olxParser.data.factories.Factory;
import edu.rdragunov.olxParser.data.interfaces.AdvertService;
import edu.rdragunov.olxParser.managers.Manager;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class Runner {
    public static void main(String[] args) throws IOException {


        Manager manager=new Manager();
        Factory factory=Factory.getFactory();

        //factory.getAdvertService().dropAdverts();

        System.out.println("--- GET FROM INTERNET ---");
        for (Advert a : manager.getAdverties()){
            System.out.println(a);
        }
        System.out.println("--- EOF GET FROM INTERNET ---\n");

        System.out.println("--- TRY SAVE TO H2 ---");

        AdvertService advertService=factory.getAdvertService();
        advertService.saveAdverts(manager.getAdverties());
        System.out.println("--- EOF SAVE TO H2 ---\n");

        System.out.println("--- GET from H2 ---");
        Set<Advert> advertSet=advertService.getAllAdverts();
        for (Advert a : advertSet){
            System.out.println(a);
        }
        System.out.println("--- EOF GET from H2 ---\n");

        System.out.println(manager.getAdverties().size());
        System.out.println(advertSet.size());

    }
}
