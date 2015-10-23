package edu.rdragunov.olxParser.config;

public interface properties {
    Integer MAX_PAGES_TO_PARSE=100;
    Integer DEFAULT_PAGE_LOAD_WAIT_MSEC=20000;
    String DEFAULT_ROOT_ELEMENT ="root";

    String KEY_MAX_PAGES_TO_PARSE ="count_pages_for_search";
    String KEY_URL_PAGE_TO_PARSE ="url";
    String KEY_TABLE_CSS_SELECTOR ="table_css_selector";
    String KEY_TIMEOUT_FOR_LOAD_PAGE_SEC="default_loading_page_timeout_in_seconds";
    String KEY_MAX_PRICE="max_price";
    String KEY_EXCLUDE_CATEGORY="excludeCategories";
}
