package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dustin on 12/3/17.
 */

public class ArticleSearchResult {
    @SerializedName("docs")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}

