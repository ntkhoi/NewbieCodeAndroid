package movie.dustin.com.movie.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.ButterKnife;
import movie.dustin.com.movie.R;
import movie.dustin.com.movie.model.Movie;

/**
 * Created by dustin on 11/26/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    private List<Movie> mMovies;

    public MovieAdapter(@NonNull Context context, List<Movie> movies) {
        super(context, -1);
        mMovies = movies;

    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent , false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.ivTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.ivOverview);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Movie movie = mMovies.get(position);
        // Fill the Data
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        Configuration configuration =  getContext().getResources().getConfiguration();
        if ( configuration.orientation == Configuration.ORIENTATION_PORTRAIT )
            Glide.with(getContext())
                    .load(movie.getPosterPath())
                    .apply(new RequestOptions().placeholder(R.drawable.place_holder))
                    .into(viewHolder.ivCover);
        else {
            Glide.with(getContext())
                    .load(movie.getBackdropPath())
                    .apply(new RequestOptions().placeholder(R.drawable.place_holder))
                    .into(viewHolder.ivCover);
        }




        return convertView;
    }

    private class ViewHolder {
        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView ivCover;


    }
}
