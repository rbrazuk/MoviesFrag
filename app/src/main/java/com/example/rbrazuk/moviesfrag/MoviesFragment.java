package com.example.rbrazuk.moviesfrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment {

    ListView lvMovies;
    TextView tvTitle;
    TextView tvYear;
    TextView tvRating;

    ArrayList<Movie> mMovies;

    public static MoviesFragment newInstance(int page, String title) {
        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        moviesFragment.setArguments(args);
        return moviesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(getActivity());

        mMovies = new ArrayList<>();

        mMovies.add(new Movie("Star Wars", 5, "1977"));
        mMovies.add(new Movie("Blade Runner",4,"1982"));




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_movies,container,false);

        ButterKnife.bind(getActivity());

        MoviesAdapter adapter = new MoviesAdapter(getActivity(),mMovies);

        lvMovies = (ListView) view.findViewById(R.id.lv_movies);
        lvMovies.setAdapter(adapter);



        return view;

    }

    private class MoviesAdapter extends ArrayAdapter<Movie> {

        public MoviesAdapter(Context context, ArrayList<Movie> movies) {
            super(context,0, movies);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Movie movie = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie,parent,false);
            }

            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvYear = (TextView) convertView.findViewById(R.id.tv_year);
            tvRating = (TextView) convertView.findViewById(R.id.tv_rating);

            tvTitle.setText(movie.getTitle());
            tvYear.setText(movie.getYearReleased());
            tvRating.setText(movie.getRating() + "");

            return convertView;


        }
    }
}
