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

/**
 * Created by rossbrazuk1 on 3/28/16.
 */
public class WatchListFragment extends Fragment {

    ListView lvWatchlist;
    TextView tvTitle;
    TextView yearReleased;


    ArrayList<Movie> mWatchlist;

    public static WatchListFragment newInstance(int page, String title) {
        WatchListFragment fragment = new WatchListFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWatchlist = new ArrayList<>();
        mWatchlist.add(new Movie("Cloud Atlas"));
        mWatchlist.add(new Movie("Midnight Special"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);

        lvWatchlist = (ListView) view.findViewById(R.id.lv_watchlist);
        WatchlistAdapter adapter = new WatchlistAdapter(getActivity(),mWatchlist);
        lvWatchlist.setAdapter(adapter);

        return view;
    }

    private class WatchlistAdapter extends ArrayAdapter<Movie> {

        public WatchlistAdapter(Context context, ArrayList<Movie> movies) {
            super(context,0, movies);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Movie movie = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_watchlist,parent,false);
            }

            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            yearReleased = (TextView) convertView.findViewById(R.id.tv_year);


            tvTitle.setText(movie.getTitle());
            yearReleased.setText(movie.getYearReleased());


            return convertView;


        }
    }
}
