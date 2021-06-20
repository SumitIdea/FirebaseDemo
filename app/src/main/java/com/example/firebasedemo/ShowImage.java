package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ShowImage extends AppCompatActivity {
    ImageView imageView;
    Button btn_show_image;
    String url="https://firebasestorage.googleapis.com/v0/b/fir-demo-7bb00.appspot.com/o/image%2Fpic.jpg0?alt=media&token=6d0097e2-53d4-4a9e-89f2-2f1ff4bf905d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imageView=findViewById(R.id.imageView);
        btn_show_image=findViewById(R.id.btn_showImage);

        btn_show_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(ShowImage.this);
                progressDialog.setTitle("Downloading");
                progressDialog.show();
                Glide.with(getApplicationContext()).load(url).into(imageView);
                progressDialog.dismiss();
                Toast.makeText(ShowImage.this, "Downloading..", Toast.LENGTH_SHORT).show();
            }
        });


    }
}