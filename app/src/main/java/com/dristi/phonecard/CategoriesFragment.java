package com.dristi.phonecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class CategoriesFragment extends Fragment {

    CardView companies, restaurants, schools;
    CardView shops, banks, travel, hospitals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.categories,null);

        companies = view.findViewById(R.id.companies);
        travel = view.findViewById(R.id.travel);
        restaurants = view.findViewById(R.id.restaurant);
        schools = view.findViewById(R.id.school);
        shops = view.findViewById(R.id.shop);
        banks = view.findViewById(R.id.bank);
        hospitals = view .findViewById(R.id.hospitals);

        companies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Corporates and Industries");
                startActivity(intent);
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Travels and Tours");
                startActivity(intent);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Hotels and Restaurants");
                startActivity(intent);
            }
        });

        schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Educational Institutions");
                startActivity(intent);
            }
        });

        shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Stores");
                startActivity(intent);
            }
        });

        banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Financial Institutions");
                startActivity(intent);
            }
        });

        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCategory.class);
                intent.putExtra("category","Hospitals and Medicines");
                startActivity(intent);
            }
        });

        return view;

    }
}
