package com.dristi.phonecard;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ViewCategory extends AppCompatActivity {

    ListView cardlist;

    Infodb cardinfo;

    Cardlistadapter adapterbycomp, adapterbyname;

    String categ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        categ = getIntent().getStringExtra("category");

        cardinfo = new Infodb(this);

        cardlist = findViewById(R.id.cardlist);

        getSupportActionBar().setTitle(categ);

        adapterbycomp = new Cardlistadapter(this,cardinfo.getcardcategorycomp(categ));
        adapterbyname = new Cardlistadapter(this,cardinfo.getcardcategoryname(categ));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sortmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.byname:
                cardlist.setAdapter(adapterbyname);
                break;

            case R.id.bycompany:
                cardlist.setAdapter(adapterbycomp);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private int id;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getOrder()){
            case 1:
                id = item.getGroupId();
                showAlertDialog();
                break;
            case 2:
                Toast.makeText(this,"Edit",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    public void showAlertDialog(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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
                adapt();
            }
        });

        dialog.show();
    }

    public void adapt(){
        cardlist.setAdapter(new Cardlistadapter(this,cardinfo.getcardcategoryname(categ)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        cardlist.setAdapter(new Cardlistadapter(this,cardinfo.getcardcategoryname(categ)));
    }
}
