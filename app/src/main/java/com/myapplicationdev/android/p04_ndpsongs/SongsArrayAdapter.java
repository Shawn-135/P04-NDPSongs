package com.myapplicationdev.android.p04_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.myapplicationdev.android.p04_ndpsongs.Song;

import java.util.ArrayList;

public class SongsArrayAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    ImageView ivIcon,iv1, iv2, iv3, iv4, iv5;
    TextView tvRYear, tvRTitle, tvRSinger;


    public SongsArrayAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        this.songs = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        //Match the UI components with Java variables
        tvRYear = rowView.findViewById(R.id.tvRYear);
        tvRTitle = rowView.findViewById(R.id.tvRTitle);
        tvRSinger = rowView.findViewById(R.id.tvRSinger);

        ivIcon = rowView.findViewById(R.id.ivIcon);
        iv1 = rowView.findViewById(R.id.star1);
        iv2 = rowView.findViewById(R.id.star2);
        iv3 = rowView.findViewById(R.id.star3);
        iv4 = rowView.findViewById(R.id.star4);
        iv5 = rowView.findViewById(R.id.star5);


        Song song = songs.get(position);
        int stars = song.getStars();

        tvRYear.setText(String.valueOf(song.getYear()));
        tvRTitle.setText(song.getTitle());
        tvRSinger.setText(song.getSingers());


        if (stars == 0) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_off);
            iv1.setImageResource(android.R.drawable.btn_star_big_off);
        }else if (stars == 1) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_off);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (stars == 2) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (stars == 3) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (stars == 4) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (stars == 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }


        return rowView;
    }



}