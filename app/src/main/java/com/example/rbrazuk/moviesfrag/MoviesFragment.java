package com.example.rbrazuk.moviesfrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

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
    FirebaseListAdapter<Movie> mFirebaseListAdapter;

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

        //ButterKnife.bind(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_movies,container,false);

        ButterKnife.bind(getActivity());

        lvMovies = (ListView) view.findViewById(R.id.lv_movies);


        Firebase ref = new Firebase("https://moviefragment.firebaseio.com/");
        final Firebase moviesRef = ref.child("movies");



        mFirebaseListAdapter = new FirebaseListAdapter<Movie>(getActivity(),Movie.class,R.layout.list_item_movie,moviesRef) {
            @Override
            protected void populateView(View view, Movie movie, int position) {
                ((TextView)view.findViewById(R.id.tv_title)).setText(movie.getTitle());
                ((TextView)view.findViewById(R.id.tv_year)).setText(movie.getYearReleased());

                if (movie.getRating() == 0) {
                    ((TextView)view.findViewById(R.id.tv_rating)).setText("-");
                } else {
                    ((TextView)view.findViewById(R.id.tv_rating)).setText(movie.getRating() + "");
                }
            }
        };

        lvMovies.setAdapter(mFirebaseListAdapter);

        lvMovies.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);

                Firebase deleteRef = moviesRef.child(movie.getMovieId());

                deleteRef.removeValue();

                return true;
            }
        });

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFirebaseListAdapter != null)
        mFirebaseListAdapter.cleanup();
    }



}
