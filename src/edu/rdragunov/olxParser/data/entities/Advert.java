package edu.rdragunov.olxParser.data.entities;

import javax.persistence.*;
import java.io.*;
import java.net.*;


@Entity
@Table(name = "ADVERT")
public class Advert implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    private Float price;

    @Column(name="IMAGE_URL")
    private String image;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "ADVERT_URL")
    private URI advertUrl;

    public Advert() {
    }

    public Advert(String category, String subject, String price, String imageUrl, String text, String advertUrl) {
        this.category = category;
        this.subject = subject;
        setPrice(price);
        this.text = text;
        image=imageUrl;
        try {
            this.advertUrl = new URI(advertUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(String price) {
        if (price == null || price.isEmpty()) {
            this.price = null;
            return;
        } else if (price.equalsIgnoreCase("Бесплатно")) {
            this.price = 0f;
            return;
        } else {
            price = price.replaceAll("\\s+", "").replaceAll("uah.", "").replaceAll("uah", "грн.");
            this.price = Float.parseFloat(price);
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        image = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URI getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(URI advertUrl) {
        this.advertUrl = advertUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "subject='" + subject + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", text='" + text + '\'' +
                ", advertUrl=" + advertUrl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (!category.equals(advert.category)) return false;
        if (!subject.equals(advert.subject)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}
