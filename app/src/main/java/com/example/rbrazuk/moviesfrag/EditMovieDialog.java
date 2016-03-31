package com.example.rbrazuk.moviesfrag;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;

/**
 * Created by rossbrazuk1 on 3/30/16.
 */
public class EditMovieDialog extends DialogFragment {

    private EditText etTitle;
    private EditText etDirector;
    private EditText etYear;
    private Button btDelete;
    private Button btSave;

    public EditMovieDialog() {

    }

    public static EditMovieDialog newInstance(String title,String director, String year, String id,boolean onWatchList) {
        EditMovieDialog frag = new EditMovieDialog();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("director",director);
        args.putString("year",year);
        args.putString("id",id);
        args.putBoolean("watchlist",onWatchList);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_edit_movie,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString("title","");
        String director = getArguments().getString("director","");
        String year = getArguments().getString("year","");
        final String id = getArguments().getString("id","error");
        final boolean isOnWatchList = getArguments().getBoolean("watchlist");

        etTitle = (EditText) view.findViewById(R.id.dialog_et_title);
        etDirector = (EditText) view.findViewById(R.id.dialog_et_director);
        etYear = (EditText) view.findViewById(R.id.dialog_et_year);
        btDelete = (Button) view.findViewById(R.id.dialog_bt_delete);
        btSave = (Button) view.findViewById(R.id.dialog_bt_save);

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnWatchList) {
                    Firebase ref = new Firebase("https://moviefragment.firebaseio.com/watchlist/" + id);
                    ref.removeValue();
                } else {
                    Firebase ref = new Firebase("https://moviefragment.firebaseio.com/movies/" + id);
                    ref.removeValue();
                }

                dismissDialog();

            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnWatchList) {
                    Firebase ref = new Firebase("https://moviefragment.firebaseio.com/watchlist/" + id);
                    ref.child("title").setValue(etTitle.getText().toString());
                    ref.child("director").setValue(etDirector.getText().toString());
                    ref.child("yearReleased").setValue(etYear.getText().toString());
                } else {
                    Firebase ref = new Firebase("https://moviefragment.firebaseio.com/movies/" + id);
                    ref.child("title").setValue(etTitle.getText().toString());
                    ref.child("director").setValue(etDirector.getText().toString());
                    ref.child("yearReleased").setValue(etYear.getText().toString());
                }

                dismissDialog();
            }
        });

        etTitle.setText(title);
        etDirector.setText(director);
        etYear.setText(year);

    }

    @Override
    public void onResume() {

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        super.onResume();
    }

    public void dismissDialog(){
        Fragment prev = getFragmentManager().findFragmentByTag("dialog_edit_movie");
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
    }
}
