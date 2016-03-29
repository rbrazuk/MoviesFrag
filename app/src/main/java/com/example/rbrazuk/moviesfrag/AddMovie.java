package com.example.rbrazuk.moviesfrag;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMovie extends AppCompatActivity {

    @Bind(R.id.et_title) EditText etTitle;
    @Bind(R.id.et_year) EditText etYear;
    @Bind(R.id.et_director) EditText etDirector;
    @Bind(R.id.bt_save) Button btSave;
    @Bind(R.id.cb_watch_list) CheckBox cbWatchList;

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ref = new Firebase("https://moviefragment.firebaseio.com/");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.bt_save)
    public void saveMovie(View view) {

        Movie movie = new Movie();

        movie.setTitle(etTitle.getText().toString());
        movie.setYearReleased(etYear.getText().toString());
        movie.setDirector(etDirector.getText().toString());

        String id = String.valueOf(UUID.randomUUID());
        movie.setId(id);

        if(cbWatchList.isChecked()) {
            Firebase watchlistRef = ref.child("watchlist");
            Firebase newPostRef = watchlistRef.push();
            String movieId = newPostRef.getKey();
            movie.setMovieId(movieId);
            newPostRef.setValue(movie);


        } else {
            Firebase moviesRef = ref.child("movies");
            Firebase newPostRef = moviesRef.push();
            String movieId = newPostRef.getKey();
            movie.setMovieId(movieId);
            newPostRef.setValue(movie);
        }


    }



}
