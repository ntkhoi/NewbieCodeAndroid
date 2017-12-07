package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import Utils.Constants;

/**
 * Created by dustin on 12/3/17.
 */

public class Article {
    @SerializedName("headline") private Headline headline;
    @SerializedName("snippet") private String snippet;
    @SerializedName("web_url") private String webUrl;
    @SerializedName("multimedia") private List<Media> multiMedias;

    public boolean haveImage(){
        if (multiMedias != null && !multiMedias.isEmpty()) return true;
        return false;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline.getMain();
    }

    public String getSnippet() {
        return snippet;
    }

    public String getImageUrl(){
        return multiMedias.get(0).getUrl();
    }

    private class Media{
        @SerializedName("url")
        private String url;

        @SerializedName("height")
        private int height;

        @SerializedName("width")
        private int width;

        @SerializedName("type")
        private String type;

        private String getUrl() {
            return Constants.BASE_URL_IMAGE + url;
        }
    }
    private class Headline{
        @SerializedName("main") private String main;
        public String getMain() {
            return main;
        }
    }
}
