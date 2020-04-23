package com.dristi.phonecard;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

public class MainPageFragment extends Fragment {

    Infodb cardinfo;

    SharedPreferences preferences;

    ListView cardlist;

    Cardlistadapter adapterbycomp, adapterbyname;

    FloatingActionMenu menu;

    com.github.clans.fab.FloatingActionButton scan, manual;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mainpage,null);

        cardinfo = new Infodb(getActivity());

        cardlist = view.findViewById(R.id.cardlist);

        scan = view.findViewById(R.id.scan);

        menu = view.findViewById(R.id.add);

        scan = view.findViewById(R.id.scan);
        manual = view.findViewById(R.id.manual);

        adapterbycomp = new Cardlistadapter(getActivity(),cardinfo.getcardlistcomp());
        adapterbyname = new Cardlistadapter(getActivity(),cardinfo.getcardlistname());

        preferences = getActivity().getSharedPreferences("Userinfo", 0);

        preferences = getActivity().getSharedPreferences("Userinfo", 0);

        menu.setClosedOnTouchOutside(true);

        cardlist.setAdapter(adapterbyname);

        registerForContextMenu(cardlist);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Scan",Toast.LENGTH_SHORT).show();
            }
        });

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddCards.class);
                intent.putExtra("addit",1);
                startActivity(intent);
            }
        });

        return view;
    }

    private int id;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getOrder()){
            case 1:
                Intent intent = new Intent(getActivity(), AddCards.class);
                intent.putExtra("editId",item.getGroupId());
                startActivity(intent);
                break;

            case 2:
                id = item.getGroupId();
                showAlertDialog();
                break;
        }

        return true;
    }

    public void showAlertDialog(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Delete card");
        dialog.setMessage("Are you sure?");

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cardinfo.deletecard(id);
                cardlist.setAdapter(new Cardlistadapter(getActivity(),cardinfo.getcardlistname()));
            }
        });

        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        cardlist.setAdapter(new Cardlistadapter(getActivity(),cardinfo.getcardlistname()));
        menu.close(true);
    }
}
