package edu.rdragunov.olxParser.managers;

import edu.rdragunov.olxParser.config.Config;
import edu.rdragunov.olxParser.entities.Advert;

import java.util.ArrayList;
import java.util.List;


public class Manager {
    private Config config=Config.CONFIG;
    private List<String> excludeCategories;
    private List<Advert> adverties=new ArrayList<>();
    private Integer maxPrice=0;

    public Manager(List<Advert> adverts){
        excludeCategories=config.getExcludeCategories();
        maxPrice=config.getMaxPrice();
        adverties=filter(adverts);
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
