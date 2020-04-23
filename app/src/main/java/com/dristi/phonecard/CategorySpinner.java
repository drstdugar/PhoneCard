package com.dristi.phonecard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategorySpinner extends ArrayAdapter <String> {

    Context context;

    public CategorySpinner(@NonNull Context context, ArrayList <String> list) {
        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String categorylist = getItem(position);

        TextView textView = new TextView(context);
        textView.setText(categorylist);
        textView.setPadding(10,10,10,10);
        textView.setTextSize(17);

        return textView;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String categorylist = getItem(position);

        TextView textView = new TextView(context);
        textView.setText(categorylist);
        textView.setPadding(10,10,10,10);
        textView.setTextSize(17);

        return textView;

    }
}
