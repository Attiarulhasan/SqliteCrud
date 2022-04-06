package com.example.sqlitecrud;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.net.URI;

public class AddRecordActivity extends AppCompatActivity {
       private ImageView pImageView;
       private EditText pNameEt, pAgeEt, pPhoneEt;
       Button saveInfo;
       ActionBar actionBar;

       private static final int CAMERA_REQUEST_CODE=100;
       private static final int STORAGE_REQUEST_CODE=101;


      private static final int IMAGE_PICK_CAMERA_CODE=102;
      private static final int IMAGE_PICK_GALLERY_CODE=102;

      private String [] cameraPermissions;
      private String[] storagePermission;
      private URI imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Add Information");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        pImageView=findViewById(R.id.personImage);
        pNameEt=findViewById(R.id.et_personName);
        pAgeEt=findViewById(R.id.et_personAge);
        pPhoneEt=findViewById(R.id.et_personPhone);

        saveInfo=findViewById(R.id.btn_addRecord);

        cameraPermissions= new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
       return super.onNavigateUp();
    }
}