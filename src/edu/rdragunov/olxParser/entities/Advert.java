package edu.rdragunov.olxParser.entities;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class Advert {

    private static String OLX_DELIMETER=".olx.ua";

    private String subject;
    private String category;
    private Integer price;
    private BufferedImage image;
    private String text;
    private URI advertUrl;

    public Advert() {
    }

    public Advert(String category, String subject, String price, String imageUrl, String text, String advertUrl) {
        this.category=category;
        this.subject = subject;
        setPrice(price);
        this.text = text;
        try {
            this.advertUrl=new URI(advertUrl);
            setImage(imageUrl);
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(String price) {
        if (price==null || price.isEmpty()){
            this.price=null;
            return;
        } else if (price.equalsIgnoreCase("Бесплатно")){
            this.price=0;
            return;
        } else {
            price=price.replaceAll("\\D+","");
            this.price = Integer.parseInt(price);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        if (imageUrl==null || imageUrl.isEmpty()){
            image=null;
            return;
        }
        //System.out.println("inputUrl: " + imageUrl);
        Integer index=imageUrl.indexOf(OLX_DELIMETER) + OLX_DELIMETER.length()+1;
        String path=imageUrl.substring(index);
        imageUrl=imageUrl.substring(0,index);
        //imageUrl="http://img13.olx.ua/";
        //path="images_slandocomua%2F215287558_1_261x203_prodam-vw-caddy-19-tdi-rvne.jpg";
        try {
            path= URLEncoder.encode(path, "UTF-8");
            URL url=new URL(imageUrl + path);
            //System.out.println("imageurl: " + imageUrl + path);
            image=ImageIO.read(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.image = image;
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
}
