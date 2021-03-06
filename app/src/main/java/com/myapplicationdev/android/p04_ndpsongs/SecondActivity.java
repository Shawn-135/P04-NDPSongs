package com.myapplicationdev.android.p04_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private static final int THIRD_ACTIVITY_REQUEST_CODE = 2;

    ListView lv;
    ArrayAdapter aa, spinnerAdapter;
    ArrayList<Song> songsList;
    ArrayList<Integer> songYearsList;
    Button btnShowFiveStars;
    Spinner yearSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DBHelper db = new DBHelper(SecondActivity.this);

        lv = findViewById(R.id.lv);
        btnShowFiveStars = findViewById(R.id.btnShowFiveStars);
        yearSpinner = findViewById(R.id.yearSpinner);

        songsList = new ArrayList<Song>();
        songsList.addAll(db.getAllSongs());

        songYearsList = new ArrayList<Integer>();
        songYearsList.addAll(db.getSongYears());


        aa = new SongsArrayAdapter(SecondActivity.this, R.layout.row, songsList);
        lv.setAdapter(aa);

        spinnerAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, songYearsList);

        yearSpinner.setAdapter(spinnerAdapter);


        btnShowFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbFS = new DBHelper(SecondActivity.this);
                songsList.clear();
                songsList.addAll(dbFS.getAllFiveStarSongs());
                dbFS.close();

                aa.notifyDataSetChanged();
            }
        });


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer selectedYear = songYearsList.get(position);
                DBHelper dbFS = new DBHelper(SecondActivity.this);
                songsList.clear();
                songsList.addAll(dbFS.getSongsByYear(selectedYear));
                dbFS.close();

                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song selectedSong = songsList.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("selectedSong", selectedSong);
                startActivityForResult(i, THIRD_ACTIVITY_REQUEST_CODE);
            }
        });


    }//end of onCreate


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == THIRD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                DBHelper db = new DBHelper(SecondActivity.this);

                songYearsList.clear();
                songYearsList.addAll(db.getSongYears());

                songsList.clear();
                songsList.addAll(db.getAllSongs());

                aa = new SongsArrayAdapter(SecondActivity.this, R.layout.row, songsList);
                lv.setAdapter(aa);

                spinnerAdapter = new ArrayAdapter(
                        this, android.R.layout.simple_spinner_item, songYearsList);

                yearSpinner.setAdapter(spinnerAdapter);
            }
        }
    }//end of onActivityResult




}//end of class