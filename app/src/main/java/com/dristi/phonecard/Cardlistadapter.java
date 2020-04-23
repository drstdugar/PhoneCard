package com.dristi.phonecard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Cardlistadapter extends ArrayAdapter <Cardinfo> {

    Context context;

    public Cardlistadapter(@NonNull Context context, ArrayList<Cardinfo> list) {
        super(context, 0, list);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardlistview, null);

        TextView fullname = view.findViewById(R.id.name),
                companyname = view.findViewById(R.id.company);

        ImageView cardpic = view.findViewById(R.id.cardpic);

        CardView cardView = view.findViewById(R.id.cardview);

        final Cardinfo info = getItem(position);

        fullname.setText(info.name);
        companyname.setText(info.company);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardDetail.class);
                intent.putExtra("id", info.id);
                context.startActivity(intent);
            }
        });

        final int id = info.id;

        cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.setHeaderTitle(info.name);
                menu.setHeaderIcon(R.color.menuhead);

                menu.add(id, v.getId(), 1, "Edit");
                menu.add(id, v.getId(), 2, "Delete");
            }
        });

        return view;
    }

}
