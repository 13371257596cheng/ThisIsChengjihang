package com.bawei.thisischengjihang.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bawei.thisischengjihang.R;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        requestPermission();



    }
    private void requestPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }else{

            Log.w("dj", "run here");
            Object object = null;
            object.toString();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        // 首先判断请求码
        switch (requestCode) {
            case 100: {
                // 因为我这里只申请了一个权限，所以我只判断第一个结果是授权成功的
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功后需要执行的代码
                    Log.w("dj", "run here");
                    Object object = null;
                    object.toString();
                } else {
                    // 授权拒绝后需要执行的代码
                    Toast.makeText(Main3Activity.this, "授权被拒绝",Toast.LENGTH_SHORT).show();
                }
                return;
            }
            default:
        }
    }
}
