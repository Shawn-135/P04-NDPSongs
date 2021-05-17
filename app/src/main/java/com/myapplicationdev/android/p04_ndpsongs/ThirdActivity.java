package com.myapplicationdev.android.p04_ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etId, etTitle, etSinger, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup radioStars;
    Song song;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etId = findViewById(R.id.etId);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        radioStars = findViewById(R.id.radioStars);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("selectedSong");

        etId.setEnabled(false);

        etId.setText(String.valueOf(song.getId()));
        etTitle.setText(song.getTitle());
        etSinger.setText(song.getSingers());
        etYear.setText(String.valueOf(song.getYear()));
        int stars = song.getStars();

        if(stars == 1){
            radioStars.check(R.id.radioButton1);
        }
        else if(stars == 2){
            radioStars.check(R.id.radioButton2);
        }
        else if(stars == 3){
            radioStars.check(R.id.radioButton3);
        }
        else if(stars == 4){
            radioStars.check(R.id.radioButton4);
        }
        else if(stars == 5){
            radioStars.check(R.id.radioButton5);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                String title = "";
                String singers = "";
                Integer year = 0;
                int stars = 0;


                if(etTitle.getText().toString().trim().length() != 0 && etSinger.getText().toString().trim().length() != 0 && etYear.getText().toString().trim().length() != 0 && radioStars.getCheckedRadioButtonId() != -1){
                    title = etTitle.getText().toString();
                    singers = etSinger.getText().toString();
                    year = Integer.parseInt(etYear.getText().toString());

                    if (radioStars.getCheckedRadioButtonId() == R.id.radioButton1){
                        stars = 1;
                    }
                    else if (radioStars.getCheckedRadioButtonId() == R.id.radioButton2){
                        stars = 2;
                    }
                    else if (radioStars.getCheckedRadioButtonId() == R.id.radioButton3){
                        stars = 3;
                    }
                    else if (radioStars.getCheckedRadioButtonId() == R.id.radioButton4){
                        stars = 4;
                    }
                    else if (radioStars.getCheckedRadioButtonId() == R.id.radioButton5){
                        stars = 5;
                    }

                    song.setTitle(title);
                    song.setSingers(singers);
                    song.setYear(year);
                    song.setStars(stars);

                    dbh.updateSong(song);
                    dbh.close();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(ThirdActivity.this, "Please ensure no fields are empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(song.getId());
                dbh.close();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }//end of onCreate

}//end of class
