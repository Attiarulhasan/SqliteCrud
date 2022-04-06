package com.example.sqlitecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
                imagePickDialog();

            }
        });
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void imagePickDialog() {

        String options[]={"Camera", "Gallery"};
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Select for Image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){

                    if(!checkCameraPermission()){

                        requestCameraPermission();
                    }
                    else {

                        pickFromCamera();
                    }
                }
                else  if(which==1){
                    if(!checkStoragePermission()){
                        checkStoragePermission();
                    }
                    else {
                        
                        pickFromStorage();
                    }
                }
                builder.create().show();

            }
        });

    }

    private void pickFromStorage() {

        Intent galleryIntent= new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
    }


    private boolean checkStoragePermission(){
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requestStoragePermission(){

        ActivityCompat.requestPermissions(this, storagePermission,STORAGE_REQUEST_CODE);
    }
    private boolean checkCameraPermission(){
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return  result && result1;
    }
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>1) {

               boolean cameraAccepted= grantResults[0]==PackageManager.PERMISSION_GRANTED;
               boolean storageAccepted= grantResults[0]==PackageManager.PERMISSION_GRANTED;
               if(cameraAccepted && storageAccepted){

                   pickFromCamera();
               }
               else {

                   Toast.makeText(this, "Camera permission is required!", Toast.LENGTH_SHORT).show();
               }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:
                if(grantResults.length>0){

                    boolean storageAccepted= grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted ){

                        pickFromStorage();
                    }
                    else {

                        Toast.makeText(this, "Storage permission required!", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
       return super.onNavigateUp();
    }
}