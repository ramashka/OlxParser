package edu.rdragunov.olxParser.managers;

import edu.rdragunov.olxParser.config.Config;
import edu.rdragunov.olxParser.data.entities.Advert;
import edu.rdragunov.olxParser.utils.HtmlParser;

import java.util.ArrayList;
import java.util.List;


public class Manager {
    private Config config=Config.CONFIG;
    private List<String> excludeCategories;
    private List<Advert> adverties=new ArrayList<>();
    private Integer maxPrice=0;
    private HtmlParser htmlParser;

    public Manager(){
        htmlParser=new HtmlParser();
        excludeCategories=config.getExcludeCategories();
        maxPrice=config.getMaxPrice();
        adverties=filter(htmlParser.getAdverts());
    }

    private List<Advert> filter(List<Advert> adverts){
        for (Advert advert : adverts){
            if (filter(advert)){
                adverties.add(advert);
            }
        }
        return adverties;
    }

    public List<Advert> getAdverties() {
        return adverties;
    }

    private boolean filter(Advert advert){
        if (excludeCategories.contains(advert.getSubject())){
            return false;
        }
        if (advert.getPrice()==null || advert.getPrice()>maxPrice){
            return false;
        }
        return true;
    }
}
