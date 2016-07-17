package com.demo.NotePad.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.demo.NotePad.R;

import java.io.File;

/**
 * Created by Administrator on 2016/7/16.
 */
public class CrimeCameraFragment extends Fragment{
    public static final String FILE_NAME = "CrimeCameraFragment_photo_name.jpg";
    private Uri imageUri;
    private ImageView mImageView;
    private Button cameraButton;
    private Button back;
    private static final  int TAKE_PHOTO = 1;
    private static final  int CROP_PHOTO= 2;
    private boolean success ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_camrea , container ,false);
         mImageView = (ImageView) view.findViewById(R.id.fragment_crime_image);
        back = (Button) view.findViewById(R.id.fragment_camera_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (success){
                   //getActivity().setResult();
                }
                getActivity().finish();
            }
        });
        cameraButton = (Button) view.findViewById(R.id.crime_camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory() , FILE_NAME);
                try {
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT , imageUri);
                //启动相机程序
                startActivityForResult(intent , TAKE_PHOTO);
            }
        });


        return view ;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK){
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.putExtra("scale" , true);
                    //启动裁剪程序
                    startActivityForResult(intent , CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getActivity().getContentResolver().openInputStream(imageUri));
                        mImageView.setImageBitmap(bitmap);
                        success  =true ;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
