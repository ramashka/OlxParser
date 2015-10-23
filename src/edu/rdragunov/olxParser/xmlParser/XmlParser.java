package edu.rdragunov.olxParser.xmlParser;

import edu.rdragunov.olxParser.config.Config;
import edu.rdragunov.olxParser.httpRequest.URLLoader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static edu.rdragunov.olxParser.config.properties.DEFAULT_ROOT_ELEMENT;

public class XmlParser {
    private static Config config = Config.CONFIG;
    private static XPathFactory xFactory=XPathFactory.instance();
    private SAXBuilder saxBuilder = new SAXBuilder();
    private Document document = null;
    private Integer pagesCount = 0;
    private String urlLoad;
    private URLLoader loader;

    public XmlParser(URLLoader urlLoader) {
        loader=urlLoader;
        document=new Document(new Element(DEFAULT_ROOT_ELEMENT));
        for (StringBuilder sb : urlLoader.getPages().values()){
            //String xml=handleXmlErrors(sb.toString());
            String s=sb.toString();
            try {
                InputStream is=new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
                Document doc=saxBuilder.build(is);
                List<Element> table=getElementsByXpath(doc,config.getTableCssSelector());
                document.getRootElement().addContent(table);
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /*
        pagesCount = config.getMaxParsePages();
        pagesCount = pagesCount < 1 ? 1 : pagesCount;
        pagesCount = pagesCount > MAX_PAGES_TO_PARSE ? MAX_PAGES_TO_PARSE : pagesCount;
        urlLoad = config.getUrlPage();
        Element root=new Element(DEFAULT_ROOT_ELEMENT);
        document=new Document(root);

            for (int i = 1; i <= pagesCount; i++) {
                URL olx=null;
                try {
                    olx = new URL(urlLoad + i);
                    System.out.println(olx);
                    Document doc = saxBuilder.build(olx);
                    List<Element> elements = getElementsByXpath(doc, config.getTableCssSelector());
                    document.getRootElement().addContent(elements);


                } catch (JDOMParseException e) {
                    System.out.println("Fail to parse url: " + olx);
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
    }

    private static List<Element> getElementsByXpath(Document doc, String xPath){
        XPathExpression<Element> expr=xFactory.compile(xPath, Filters.element());
        return expr.evaluate(doc);
    }

    public Document getDocument() {
        return document;
    }

    public String handleXmlErrors(String xml){
        XmlErrorHandler myHandler=new XmlErrorHandler();
        saxBuilder.setErrorHandler(myHandler);
        InputStream is=new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        StringBuilder fixedXml=null;
        try {
            saxBuilder.build(is);
        } catch (JDOMException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (Exception e) {
            //e
        } finally {
            //System.out.println("size:" + myHandler.getErrors().size());
            //System.out.println(myHandler.getErrors());
            List<Integer> errorLines=myHandler.getErrors();
            List<String> splittedXml=new LinkedList<>();
            splittedXml.addAll(Arrays.asList(xml.split("\n")));
            for (int i=errorLines.size()-1; i>=0 ; i--){
                System.out.println(splittedXml.get(errorLines.get(i)));
                System.out.println(errorLines.get(i));
                splittedXml.remove(errorLines.get(i));
            }
            fixedXml=new StringBuilder();
            Iterator<String> iterator=splittedXml.iterator();
            while (iterator.hasNext()){
                fixedXml.append(iterator.next());
                if (iterator.hasNext()){
                    fixedXml.append("\n");
                }
            }
        }
        return fixedXml.toString();
    }
}
