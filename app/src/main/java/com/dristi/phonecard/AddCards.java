package com.dristi.phonecard;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;

public class AddCards extends AppCompatActivity {

    Button add;

    EditText fullname, compname, post, mobile, landline, fax, address, email, website;

    Spinner category;

    Infodb cardinfo;

    ImageView addcard;

    int editId, addit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);

        cardinfo = new Infodb(this);

        addit = getIntent().getIntExtra("addit",0);

        editId = getIntent().getIntExtra("editId",0);

        add = findViewById(R.id.addbutton);

        addcard = findViewById(R.id.addcardpic);

        fullname = findViewById(R.id.name);
        compname = findViewById(R.id.compname);
        post = findViewById(R.id.post);
        mobile = findViewById(R.id.mobile);
        landline = findViewById(R.id.landline);
        fax = findViewById(R.id.fax);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        website = findViewById(R.id.website);

        category = findViewById(R.id.category);
        category.setAdapter(new CategorySpinner(this, cardinfo.getcategorylist()));

        if(addit == 1){

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nameval = fullname.getText().toString();
                    String emailval = email.getText().toString();
                    String addressval = address.getText().toString();
                    String mobileval = mobile.getText().toString();
                    String companyval = compname.getText().toString();
                    String postval = post.getText().toString();
                    String faxval = fax.getText().toString();
                    String webval = website.getText().toString();
                    String landlineval = landline.getText().toString();
                    String categoryval = (String) category.getSelectedItem();

                    if(nameval.isEmpty()){
                        fullname.setText("Necessary field");
                        fullname.setTextColor(R.drawable.error);
                    }

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Name", nameval);
                    contentValues.put("Email", emailval);
                    contentValues.put("Landline", landlineval);
                    contentValues.put("Address", addressval);
                    contentValues.put("Company", companyval);
                    contentValues.put("Mobile", mobileval);
                    contentValues.put("Fax", faxval);
                    contentValues.put("Post", postval);
                    contentValues.put("Website", webval);
                    contentValues.put("Category", categoryval);
                    if(bitmap != null)
                        contentValues.put("Cardpic", getBlob(bitmap));

                    cardinfo.insertcard(contentValues);

                    startActivity(new Intent(AddCards.this, MainActivity.class));
                    finish();

                }
            });
        }
        else{

            getSupportActionBar().setTitle("Edit Card");

            add.setText("Edit");

            Cardinfo info = cardinfo.getcardinfo(editId);

            fullname.setText(info.name);
            compname.setText(info.company);
            post.setText(info.post);
            landline.setText(info.landline);
            email.setText(info.email);
            website.setText(info.website);
            address.setText(info.address);
            fax.setText(info.fax);
            mobile.setText(info.mobile);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameval = fullname.getText().toString();
                    String emailval = email.getText().toString();
                    String addressval = address.getText().toString();
                    String mobileval = mobile.getText().toString();
                    String companyval = compname.getText().toString();
                    String postval = post.getText().toString();
                    String faxval = fax.getText().toString();
                    String webval = website.getText().toString();
                    String landlineval = landline.getText().toString();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Name", nameval);
                    contentValues.put("Email", emailval);
                    contentValues.put("Landline", landlineval);
                    contentValues.put("Address", addressval);
                    contentValues.put("Company", companyval);
                    contentValues.put("Mobile", mobileval);
                    contentValues.put("Fax", faxval);
                    contentValues.put("Post", postval);
                    contentValues.put("Website", webval);

                    cardinfo.updatecard(editId,contentValues);

                    startActivity(new Intent(AddCards.this, MainActivity.class));
                    finish();
                }
            });
        }



        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);

            }
        });

    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 101){
                bitmap = (Bitmap) data.getExtras().get("data");
                addcard.setImageBitmap(bitmap);
            }
        }

    }

    public static byte[] getBlob(Bitmap bitmap){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100 ,bos);
        byte[] bArray = bos.toByteArray();
        return bArray;

    }

    public static Bitmap getBitmap(byte[] byteArray){

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

    }
}
