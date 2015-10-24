package edu.rdragunov.olxParser.data.entities;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static edu.rdragunov.olxParser.config.properties.OLX_DELIMETER;

@Entity
@Table(name="IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    @Column(name="IMAGE")
    private byte[] imageBytes;

    public Image(){}

    public Image(String url) {
        Integer index = url.indexOf(OLX_DELIMETER) + OLX_DELIMETER.length() + 1;
        String path = url.substring(index);
        url = url.substring(0, index);
        try {
            path = URLEncoder.encode(path, "UTF-8");
            URL httpUrl=new URL(url+path);
            httpUrl.openConnection().getInputStream().read(imageBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }


}
