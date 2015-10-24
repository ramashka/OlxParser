package edu.rdragunov.olxParser.data.interfaces;

import edu.rdragunov.olxParser.data.entities.Advert;

import java.util.Set;

/**
 * Created by dtv on 23.10.2015.
 */
public interface AdvertDao {
    Advert getAdvert(Integer id);
    Advert saveAdvert(Advert advert);
    Set<Advert> getAllAdverts();
    Advert removeAdvert(Advert advert);
    void dropAdverts();
}
