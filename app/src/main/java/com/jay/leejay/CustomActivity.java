package com.jay.leejay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        ListView customListView = findViewById(R.id.customListView);
        ListViewAdapter adapter = new ListViewAdapter();
        customListView.setAdapter(adapter);


    }
}