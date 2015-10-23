package edu.rdragunov.olxParser.htmlParser;

import edu.rdragunov.olxParser.config.Config;
import edu.rdragunov.olxParser.entities.Advert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
    private static Config config=Config.CONFIG;
    private String BASE_URI="localhost";

    private List<Advert> adverts=new ArrayList<>();

    public HtmlParser(){
        Elements advertTables=new Elements();

        for (int i= 1 ; i<=config.getMaxParsePages(); i++){
            URL url=null;
            Document doc=null;
            try {
                url=new URL(config.getUrlPage() + i);
                doc=Jsoup.parse(url, config.getPageLoadWait());
                advertTables.addAll(doc.select(config.getTableCssSelector()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Element advertTable : advertTables){
            adverts.add(parseAdver(advertTable));
        }
    }

    public Advert parseAdver(Element advertTable){
        //System.out.println(advertTable);
        //select link to advert
        String adLink=advertTable.select("td > a").attr("href");
        //select subject
        String subject=advertTable.select("div[class=space rel] > h3 > a > strong").text();
        //select category
        String category=advertTable.select("div[class=space rel] > p > small").text();
        //select price
        String price=advertTable.select("div[class=space rel] > p.price > strong").text().replaceAll("\\D","");
        //select image
        String imageUrl=advertTable.select("img.fleft").attr("src");
        return new Advert(category,subject,price,imageUrl,null,adLink);
    }

    public List<Advert> getAdverts() {
        return adverts;
    }
}
