package com.myapplicationdev.android.p04_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private static final int THIRD_ACTIVITY_REQUEST_CODE = 2;

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Song> songsList;
    Button btnShowFiveStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        lv = findViewById(R.id.lv);
        btnShowFiveStars = findViewById(R.id.btnShowFiveStars);

        songsList = new ArrayList<Song>();

        aa = new ArrayAdapter<Song>(SecondActivity.this,
                android.R.layout.row, songsList);

        lv.setAdapter(aa);


        btnShowFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                songsList.clear();
                songsList.addAll(dbh.getAllFiveStarSongs());
                dbh.close();

                aa.notifyDataSetChanged();
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

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == THIRD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                aa.notifyDataSetChanged();
            }
        }
    }//end of onActivityResult




}//end of class