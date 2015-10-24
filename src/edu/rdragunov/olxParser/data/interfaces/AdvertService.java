package edu.rdragunov.olxParser.data.interfaces;

import edu.rdragunov.olxParser.data.entities.Advert;

import java.util.Collection;
import java.util.Set;

/**
 * Created by dtv on 23.10.2015.
 */
public interface AdvertService {
    Advert getAdvert(Integer id);
    Advert saveAdvert(Advert advert);
    void saveAdverts(Collection<Advert> adverts);
    Set<Advert> getAllAdverts();
    Advert removeAdvert(Advert advert);
    void dropAdverts();
}
