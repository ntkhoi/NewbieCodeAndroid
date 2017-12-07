package activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import NetworkLayer.ArticleApi;
import NetworkLayer.SearchRequest;
import Utils.Retrofits;
import adapter.ArticleAdapter;
import model.Article;
import model.ArticleSearchResult;
import news.dustin.com.nytime.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  ArticleAdapter.Listener, SettingDialogFragment.Listener  {

    RecyclerView rvArticle;
    ProgressBar pbLoading;
    ArticleApi mArticleApi;
    SearchView searchView;
    SearchRequest mSearchRequest;
    ArticleAdapter mAdapter;
    List<Article> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvArticle = (RecyclerView) findViewById(R.id.rvArticle);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        mSearchRequest = new SearchRequest("20160101", "oldest", null, 0, false, false, false);
        mArticleApi = Retrofits.get().create(ArticleApi.class);
        search();
    }
    private void search() {
        pbLoading.setVisibility(View.VISIBLE);
        mArticleApi.getSearch(mSearchRequest.toQuery()).enqueue(new Callback<ArticleSearchResult>() {
            @Override
            public void onResponse(Call<ArticleSearchResult> call, Response<ArticleSearchResult> response) {
                Log.e("body", String.valueOf(response.isSuccessful()));
                if (null != response.body()) {
                    mList = response.body().getArticles();
                    mAdapter = new ArticleAdapter(mList);
                    rvArticle.setAdapter(mAdapter);
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                    rvArticle.setLayoutManager(layoutManager);
                    pbLoading.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "END", Toast.LENGTH_SHORT).show();
                    pbLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArticleSearchResult> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClicked(View itemView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                mSearchRequest.setKeySearch(query);
                search();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort:
                // Show dialog
                showSetting();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSetting(){
        android.app.FragmentManager fm = getFragmentManager();
        SettingDialogFragment settingDialog = SettingDialogFragment.newInstance(mSearchRequest, MainActivity.this);
        settingDialog.show(fm, "Setting");
    }

    @Override
    public void onSaveSetting(SearchRequest searchRequest) {
        Log.d("tag","main activity: onSaveSetting");
        mSearchRequest = searchRequest;
        search();
    }
}
