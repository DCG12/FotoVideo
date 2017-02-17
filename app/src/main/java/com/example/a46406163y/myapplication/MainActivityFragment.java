package com.example.a46406163y.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private Button bttnfoto;
    private String mCurrentPhotoPath;

    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int ACTIVITAT_SELECCIONAR_IMATGE = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        bttnfoto =(Button) view.findViewById(R.id.bttnFoto);

        bttnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    dispatchTakePictureIntent();

            }
        });
        return view;

    }



    private File createImageFile(int requestTakePhoto)  {

        Log.d( "DEBBUG-3", "ENTRA CREATEIMAGEFILE");
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Log.d( "DEBBUG-4", timeStamp);
        String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d( "DEBBUG-5", imageFileName);
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        Log.d( "DEBBUG-6", storageDir.getAbsolutePath());
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d( "DEBBUG-7", "ENTRA CREATEIMAGEFILE");

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Log.d("DEBBUG--", mCurrentPhotoPath);
        return image;
    }



    private void dispatchTakePictureIntent() {

        Log.d("DEBBUG-1", "entra al metode");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {

            Log.d("DEBUGG-2", "ENTRA AL IF");
            File photoFile = null;
            photoFile = createImageFile(REQUEST_TAKE_PHOTO);

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                Log.d("DEBUGGGGING", "KKKK");
            }
        }
    }



}
