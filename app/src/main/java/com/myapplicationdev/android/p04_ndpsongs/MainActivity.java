package com.myapplicationdev.android.p04_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnInsert, btnShowList;
    EditText editTextTitle, editTextSingers, editTextYear;
    RadioGroup rgStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSingers = findViewById(R.id.editTextSingers);
        editTextYear = findViewById(R.id.editTextYear);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stars = 0;
                String title = "";
                String singers = "";
                int year = 0;

                if(editTextTitle.getText().toString().trim().length() != 0){
                    title = editTextTitle.getText().toString();
                }
                else{
                    editTextTitle.setError("Please enter song title");
                }

                if(editTextYear.getText().toString().trim().length() != 0){
                    year = Integer.parseInt(editTextYear.getText().toString());
                }
                else{
                    editTextYear.setError("Please enter the year of release");
                }

                if(editTextSingers.getText().toString().trim().length() != 0){
                    singers = editTextSingers.getText().toString();
                }
                else{
                    editTextSingers.setError("Please enter singers name");
                }

                if (rgStars.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this, "Please select the amount of star(s)", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (rgStars.getCheckedRadioButtonId() == R.id.rb1){
                        stars = 1;
                    }
                    else if (rgStars.getCheckedRadioButtonId() == R.id.rb2){
                        stars = 2;
                    }
                    else if (rgStars.getCheckedRadioButtonId() == R.id.rb3){
                        stars = 3;
                    }
                    else if (rgStars.getCheckedRadioButtonId() == R.id.rb4){
                        stars = 4;
                    }
                    else if (rgStars.getCheckedRadioButtonId() == R.id.rb5){
                        stars = 5;
                    }

                DBHelper db = new DBHelper(MainActivity.this);
                    db.insertSong(title, singers, year, stars);
                    db.close();
                }
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}