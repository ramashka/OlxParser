package edu.rdragunov.olxParser.data.services;

import edu.rdragunov.olxParser.data.entities.Advert;
import edu.rdragunov.olxParser.data.interfaces.AdvertDao;
import edu.rdragunov.olxParser.data.interfaces.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service(value = "advertService")
@Transactional
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertDao advertDao;

    @Override
    public Advert getAdvert(Integer id) {
        return advertDao.getAdvert(id);
    }

    @Override
    public Advert saveAdvert(Advert advert) {
        return advertDao.saveAdvert(advert);
    }

    @Override
    public void saveAdverts(Collection<Advert> adverts) {
        for(Advert a : adverts){
            advertDao.saveAdvert(a);
        }
    }

    @Override
    public Set<Advert> getAllAdverts() {
        return advertDao.getAllAdverts();
    }

    @Override
    public Advert removeAdvert(Advert advert) {
        return advertDao.removeAdvert(advert);
    }

    @Override
    public void dropAdverts() {
        advertDao.dropAdverts();
    }
}
