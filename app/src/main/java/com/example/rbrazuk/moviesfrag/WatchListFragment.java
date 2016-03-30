package com.example.rbrazuk.moviesfrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;

public class WatchListFragment extends Fragment {

    ListView lvWatchlist;
    TextView tvTitle;
    TextView yearReleased;
    CheckBox cbWatchList;

    FirebaseListAdapter<Movie> mFirebaseListAdapter;

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


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);

        final Firebase ref = new Firebase("https://moviefragment.firebaseio.com/");
        final Firebase watchlistRef = ref.child("watchlist");

        lvWatchlist = (ListView)view.findViewById(R.id.lv_watchlist);

        mFirebaseListAdapter = new FirebaseListAdapter<Movie>(getActivity(),Movie.class,R.layout.list_item_watchlist,watchlistRef) {
            @Override
            protected void populateView(View view, final Movie movie, int position) {
                ((TextView)view.findViewById(R.id.tv_title)).setText(movie.getTitle());
                ((TextView)view.findViewById(R.id.tv_year)).setText(movie.getYearReleased());

                cbWatchList = (CheckBox) view.findViewById(R.id.cb_watched);

                cbWatchList.setFocusable(false);

                cbWatchList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        Firebase moveRef = watchlistRef.child(movie.getMovieId());
                        Firebase moviesRef = ref.child("movies");
                        Firebase newPostRef = moviesRef.push();
                        String movieId = newPostRef.getKey();
                        movie.setMovieId(movieId);
                        newPostRef.setValue(movie);

                        moveRef.removeValue();
                        Toast.makeText(getActivity(),movie.getTitle() + " moved to Movies",Toast.LENGTH_LONG).show();
                    }
                });
            }


        };

        lvWatchlist.setAdapter(mFirebaseListAdapter);


        lvWatchlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);

                showEditDialog(movie);
                return true;
            }
        });

        return view;
    }

    private void showEditDialog(Movie movie) {
        FragmentManager fm = getFragmentManager();
        EditMovieDialog editMovieDialog = EditMovieDialog.newInstance(movie.getTitle(),movie.getDirector(),movie.getYearReleased(),movie.getMovieId(),true);
        editMovieDialog.show(fm, "dialog_edit_movie");

    }


}
