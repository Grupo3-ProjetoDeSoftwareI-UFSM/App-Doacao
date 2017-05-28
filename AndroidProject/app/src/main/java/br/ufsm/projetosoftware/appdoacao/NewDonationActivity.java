package br.ufsm.projetosoftware.appdoacao;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import br.ufsm.projetosoftware.appdoacao.view.NewDonationView;
import br.ufsm.projetosoftware.appdoacao.view.NewDonationViewImpl;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class NewDonationActivity extends AppCompatActivity
        implements NewDonationView.SelectImageListener,
        NewDonationView.RegisterDonationListener{

    NewDonationView newDonationView;
    private static int PICK_IMAGE = 1;
    private static int REQUEST_READ_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);
        newDonationView = new NewDonationViewImpl(getWindow().getDecorView().getRootView());
        newDonationView.setSelectImageListener(this);
    }

    private boolean mayRequestStorage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
            //TODO exibir justificativa da permissão
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        } else {
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        }
    }

    @Override
    public void onSelectImageClick() {
        if(!mayRequestStorage()){
            return;
        }
        pickImage();

    }

    private void pickImage(){
        System.out.println("get here");
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE){
            Bitmap image = null;
            if(data != null){
                try {
                    image = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newDonationView.setImage(image);
            }
        }
    }

    @Override
    public void onRegisterDonationClick() {
        //TODO
    }
}
