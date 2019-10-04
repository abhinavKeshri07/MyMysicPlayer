package info.abhinavkeshri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.MODIFY_AUDIO_SETTINGS,
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.RECORD_AUDIO
    };
    private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checking for permissions
        if(ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permissions were given", Toast.LENGTH_LONG).show();
        }
        else{
            requestStoragePermission();
        }

    }
    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("permission needed")
                    .setMessage("This permission is needed to read External storage for music files.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, permissions, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }else{
            ActivityCompat.requestPermissions(this, permissions, STORAGE_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Permission was not given.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
