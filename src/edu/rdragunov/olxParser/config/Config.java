package edu.rdragunov.olxParser.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.rdragunov.olxParser.config.properties.*;

public enum Config {
    CONFIG;

    private Integer maxParsePages = 1;
    private ConfigLoader configLoader;
    private String urlPage;
    private String tableCssSelector;
    private Integer pageLoadWait = 0;
    private Integer maxPrice = 0;
    private List<String> excludeCategories=new ArrayList<>();

    private Config() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        configLoader = (ConfigLoader) context.getBean("ConfigLoader");
        setProperties();
    }

    public Integer getMaxParsePages() {
        return maxParsePages;
    }

    private void setMaxParsePages(String maxParsePages) {
        Integer i = Integer.parseInt(maxParsePages);
        this.maxParsePages = i < 1 ? 1 : i;
        this.maxParsePages = i > MAX_PAGES_TO_PARSE ? MAX_PAGES_TO_PARSE : i;
    }


    public String getUrlPage() {
        return urlPage;
    }

    public void setUrlPage(String urlPage) {
        this.urlPage = urlPage;
    }

    public String getTableCssSelector() {
        return tableCssSelector;
    }

    private void setTableCssSelector(String tableCssSelector) {
        this.tableCssSelector = tableCssSelector;
    }

    private void setProperties() {
        setMaxParsePages(configLoader.getProperty(KEY_MAX_PAGES_TO_PARSE));
        setUrlPage(configLoader.getProperty(KEY_URL_PAGE_TO_PARSE));
        setTableCssSelector(configLoader.getProperty(KEY_TABLE_CSS_SELECTOR));
        setPageLoadWait(configLoader.getProperty(KEY_TIMEOUT_FOR_LOAD_PAGE_SEC));
        setMaxPrice(configLoader.getProperty(KEY_MAX_PRICE));
        setExcludeCategories();
    }

    public Integer getPageLoadWait() {
        return pageLoadWait;
    }

    private void setPageLoadWait(String timeout) {
        this.pageLoadWait = Integer.parseInt(timeout) * 1000;
        if (pageLoadWait < 1) {
            pageLoadWait = DEFAULT_PAGE_LOAD_WAIT_MSEC;
        }
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    private void setMaxPrice(String price) {
        if (price == null) {
            maxPrice = null;
        } else {
            maxPrice = Integer.parseInt(price.replaceAll("\\D", ""));
            if (maxPrice < 0) {
                maxPrice = 0;
            }
        }
    }

    public List<String> getExcludeCategories() {
        return excludeCategories;
    }

    private void setExcludeCategories() {
        Set<String> keys=configLoader.stringPropertyNames();
        for (String s : keys){
            if (s.startsWith(KEY_EXCLUDE_CATEGORY)){
                excludeCategories.add(configLoader.getProperty(s));
            }
        }
    }
}
