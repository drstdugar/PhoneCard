package com.dristi.phonecard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CardDetail extends AppCompatActivity {

    CardView cnumber, clocation, cmail, cinfo, cweb;

    TextView number, location, mail, info, web, name, post, company;

    int id;

    Infodb cardinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        id = getIntent().getIntExtra("id",0);

        cardinfo = new Infodb(this);

        cnumber = findViewById(R.id.cnumber);
        clocation = findViewById(R.id.clocation);
        cmail = findViewById(R.id.cmail);
        cinfo = findViewById(R.id.cinfo);
        cweb = findViewById(R.id.cwebsite);

        number = findViewById(R.id.number);
        location  = findViewById(R.id.location);
        mail = findViewById(R.id.mail);
        info = findViewById(R.id.info);
        web = findViewById(R.id.website);
        name = findViewById(R.id.name);
        post = findViewById(R.id.post);
        company = findViewById(R.id.compname);

        final Cardinfo infocard = cardinfo.getcardinfo(id);

        cmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String [] mailid = {infocard.email};
                intent.putExtra(Intent.EXTRA_EMAIL,mailid);
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent,"Send mail"));
            }
        });

        cnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String cell = String.format("tel: %s",infocard.mobile);
                intent.setData(Uri.parse(cell));
                startActivity(intent);
            }
        });

        clocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mapuri = Uri.parse("geo:0,0?q=" + infocard.address);
                Intent intent = new Intent(Intent.ACTION_VIEW,mapuri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        cweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showcarddetail();
    }

    public void showcarddetail() {

        Cardinfo infocard = cardinfo.getcardinfo(id);

        if(infocard.mobile.isEmpty()){
            cnumber.setVisibility(View.GONE);
        }
        else{
            number.setText(infocard.mobile);
        }

        if(infocard.email.isEmpty()){
            cmail.setVisibility(View.GONE);
        }
        else{
            mail.setText(infocard.email);
        }

        if(infocard.address.isEmpty()){
            clocation.setVisibility(View.GONE);
        }
        else{
            location.setText(infocard.address);
        }

        if(infocard.website.isEmpty()){
            cweb.setVisibility(View.GONE);
        }
        else{
            web.setText(infocard.website);
        }

        if(infocard.post.isEmpty()){
            post.setVisibility(View.GONE);
        }
        else{
            post.setText(infocard.post);
        }

        name.setText(infocard.name);
        company.setText(infocard.company);

    }

}
