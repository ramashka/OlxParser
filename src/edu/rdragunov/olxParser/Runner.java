package edu.rdragunov.olxParser;

import edu.rdragunov.olxParser.config.Config;
import edu.rdragunov.olxParser.entities.Advert;
import edu.rdragunov.olxParser.htmlParser.HtmlParser;
import edu.rdragunov.olxParser.httpRequest.URLLoader;
import edu.rdragunov.olxParser.managers.Manager;
import edu.rdragunov.olxParser.xmlParser.XmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.*;
import java.net.URL;

public class Runner {
    public static void main(String[] args) throws IOException {
        //URLLoader urlLoader=URLLoader.loadPages();
        //System.out.println(urlLoader.getPages().get(1));
        //XmlParser xmlParser=new XmlParser(urlLoader);
        //System.out.println(xmlParser.getDocument());
        //XmlParser xmlParser=new XmlParser();
        //System.out.println(xmlParser.getDocument().getContent());

        //Config config= Config.CONFIG;
        //URL url=new URL(config.getUrlPage() + 1);
        //Document document= Jsoup.parse(url,20000);
        HtmlParser htmlParser=new HtmlParser();
        Manager manager=new Manager(htmlParser.getAdverts());
        for (Advert a : manager.getAdverties()){
            System.out.println(a);
        }

    }
}
