package com.softserve.edu.teachua.data;

public enum UrlContents {

    DEFAULT_IFRAME("JMAF_pSOBws", "https://www.youtube.com/watch?v=JMAF_pSOBws"),
    WEBINAR_IFRAME("JMAF_pSOBws", "https://www.youtube.com/watch?v=JMAF_pSOBws"),;

    private String searchVideo;
    private String fullUrl;

    private UrlContents(String searchVideo, String fullUrl) {
        this.searchVideo = searchVideo;
        this.fullUrl = fullUrl;
    }

    public String getSearchVideo() {
        // TODO
        return searchVideo;
    }

    public String getFullUrl() {
        // TODO
        return fullUrl;
    }

    @Override
    public String toString() {
        // TODO
        return searchVideo;
    }

}
