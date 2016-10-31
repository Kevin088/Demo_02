package com.demo.cn.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    MyAdapter adapter;
    List<String> list=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<100;i++){
            list.add("aa"+i);
        }
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.oneCity);
        adapter = new MyAdapter(this,list);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.enoughToFilter();
    }
}
