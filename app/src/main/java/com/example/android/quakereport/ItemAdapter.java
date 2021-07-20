package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.drawable.GradientDrawable;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatmag(double mag) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        String formatMag = formatter.format(mag);
        return formatMag;
    }

    private String[] formatLoc(String Loc) {
        int f = 0;
        String formattedLoc[] = new String[2];
        String s = "";
        for (int i = 0; i < Loc.length(); i++) {
            if (Loc.charAt(i) != ' ') {
                s += Loc.charAt(i);
            } else {

                if (s.equals("of")) {
                    f = 1;
                    formattedLoc[0] = Loc.substring(0, i);
                    formattedLoc[1] = Loc.substring(i + 1, Loc.length());
                    break;
                }
                s = "";
            }
        }
        if (f == 0) {
            formattedLoc[0] = getContext().getString(R.string.near_the);
            formattedLoc[1] = Loc;

        }
        return formattedLoc;
    }

    private int getMagnitudeColor(double mag) {
        int magR;
        int magf = (int) Math.floor(mag);
        switch (magf) {
            case 0:
            case 1:
                magR = R.color.magnitude1;
                break;
            case 2:
                magR = R.color.magnitude2;
                break;
            case 3:
                  magR=R.color.magnitude3;
                  break;
            case 4:
                magR = R.color.magnitude4;
                break;
            case 5:
                magR = R.color.magnitude5;
                break;
            case 6:
                magR=R.color.magnitude6;
                break;
            case 7:
                magR=R.color.magnitude7;
                break;
            case 8:
                magR = R.color.magnitude8;
                break;
            case 9:
                magR = R.color.magnitude9;
                break;
            default:
                magR=R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),magR);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemview, parent, false);
        }
        TextView first = (TextView) convertView.findViewById(R.id.first_text);

        GradientDrawable magnitudeCircle = (GradientDrawable) first.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(item.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        first.setText(formatmag(item.getMagnitude()));
        String Location = item.getLoc();
        String twoloc[] = formatLoc(Location);
        TextView second = (TextView) convertView.findViewById(R.id.second_text);
        second.setText(twoloc[0]);
        TextView offSet = (TextView) convertView.findViewById(R.id.offset);
        offSet.setText(twoloc[1]);
        long tme = item.getDate();
        Log.v("MYTAGGG","time = "+tme);
        Date dateObject = new Date(tme);
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);
        TextView third = (TextView) convertView.findViewById(R.id.third_text);
        third.setText(formattedDate);
        TextView fourth = (TextView) convertView.findViewById(R.id.fourth_text);
        fourth.setText(formattedTime);
        return convertView;
    }
}
