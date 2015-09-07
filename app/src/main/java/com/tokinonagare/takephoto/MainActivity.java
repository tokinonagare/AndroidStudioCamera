package com.tokinonagare.takephoto;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView tokinonagareImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tokinonagareButton = (Button) findViewById(R.id.tokinonagareButton);
        tokinonagareImageView = (ImageView) findViewById(R.id.tokinonagareImageView);

        //Disable the button if user has no camera
        if (!hasCamera())
            tokinonagareButton.setEnabled(false);

    }

    //Check if the user has a camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult;
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //If you want ti return the image taken


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            tokinonagareImageView.setImageBitmap(photo);
        }
    }
}
