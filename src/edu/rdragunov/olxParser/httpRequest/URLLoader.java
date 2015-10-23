package edu.rdragunov.olxParser.httpRequest;

import edu.rdragunov.olxParser.config.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;


import static edu.rdragunov.olxParser.config.properties.MAX_PAGES_TO_PARSE;


public class URLLoader {

    private static Config config=Config.CONFIG;

    private Map<Integer, StringBuilder> pages;
    private Integer pagesCount;
    private String urlPage;

    private URLLoader(){
        pagesCount =config.getMaxParsePages();
        pagesCount = pagesCount < 1 ? 1 : pagesCount;
        pagesCount = pagesCount > MAX_PAGES_TO_PARSE ? MAX_PAGES_TO_PARSE : pagesCount;
        pages=new LinkedHashMap<>(this.pagesCount);
        urlPage=config.getUrlPage();
    }

    public static URLLoader loadPages(){
        URLLoader urlLoader =new URLLoader();
        try {
            for (int i=1 ; i <= urlLoader.pagesCount ; i++){
                URL olx=new URL(urlLoader.urlPage + i);
                olx.openConnection();
                BufferedReader in=new BufferedReader(new InputStreamReader(olx.openStream()));
                StringBuilder sb=new StringBuilder();
                String inputLine;
                while ((inputLine=in.readLine()) != null){
                    String s=olxWorkAround(inputLine);
                    if (s!=null && !s.isEmpty()) {
                        sb.append(s);
                        sb.append("\n");
                    }
                }
                urlLoader.pages.put(i,sb);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlLoader;
    }

    public Map<Integer, StringBuilder> getPages() {
        return pages;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    private static String olxWorkAround(String str){
        str=str.replaceAll("&","&amp;");
        if (str.contains("<link")){
            if (! (str.contains("/>") || str.contains("link/>"))){
                str=null;
            }
        }
        return str;
    }
}
