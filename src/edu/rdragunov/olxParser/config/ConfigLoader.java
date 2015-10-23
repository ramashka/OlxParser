package edu.rdragunov.olxParser.config;

import java.io.*;
import java.util.Properties;

public class ConfigLoader extends Properties{
    private String file;



    public ConfigLoader(String file){
        this.file=file;
        File f=new File(file);
        if (!f.exists() || f.isDirectory()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Not able to load property file: " + file);
                e.printStackTrace();
            }
        }
        InputStream is=null;

        try {
            is=new FileInputStream(f);
            load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
