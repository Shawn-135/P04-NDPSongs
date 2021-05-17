package com.myapplicationdev.android.p04_ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etId, etTitle, etSinger, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    SecondActivity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            DBHelper dbh = new DBHelper(ThirdActivity.this);
            dbh.updateThirdActivity(data);
            dbh.close();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteThirdActivity(data.getId());
                dbh.close();
            }
        });

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContentEditText.setText(data.getNoteContent());
    }
}
