package co.waitdialog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyMaterialProgressView imageView= (MyMaterialProgressView) findViewById(R.id.progressview);
        imageView.setColorSchemeColors(new int[]{getResources().getColor(R.color.colorPrimary)});
        imageView.setProgressBackgroundColor(Color.parseColor("#ffffff"));
        imageView.setColorViewAlpha(255);
    }
}
