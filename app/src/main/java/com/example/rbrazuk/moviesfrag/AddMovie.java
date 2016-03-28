package com.example.rbrazuk.moviesfrag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.firebase.client.Firebase;

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

    @OnClick(R.id.bt_save)
    public void saveMovie(View view) {

        Movie movie = new Movie();

        movie.setTitle(etTitle.getText().toString());
        movie.setYearReleased(etYear.getText().toString());
        movie.setDirector(etDirector.getText().toString());
        movie.setIsOnWatchList(cbWatchList.isChecked());

        if(cbWatchList.isChecked()) {
            Firebase watchlistRef = ref.child("watchlist");
            watchlistRef.push().setValue(movie);
        } else {
            Firebase moviesRef = ref.child("movies");
            moviesRef.push().setValue(movie);
        }


    }



}
