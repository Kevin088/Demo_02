package com.tinkertest.cn;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_load).setOnClickListener(this);

        Toast.makeText(this, "正确的 !!!!! ~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_load:
                String patchPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xll";
                TinkerInstaller.onReceiveUpgradePatch(this.getApplication(), patchPath);
                break;
        }
    }
}
