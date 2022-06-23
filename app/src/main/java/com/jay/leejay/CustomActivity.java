package com.jay.leejay;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CustomActivity extends AppCompatActivity {
    SignUpActivity.MyDBHelper myDBHelper;

    ListView customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        customListView = findViewById(R.id.customListView);

        list();

    }
    void list(){
        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        String sql = "SELECT EMAIL"+ "," + " PWD FROM student_informationTBL;";
        Cursor cursor = db.rawQuery(sql, null);

        ListViewAdapter adapter = new ListViewAdapter();

        while (cursor.moveToFirst()){
            adapter.addItem(cursor.getString(0), cursor.getString(0));
        }

        customListView.setAdapter(adapter);


        /*adapter.addItem();

        String sql = "SELECT EMAIL, PWD FROM student_informationTBL;";
        Cursor cursor = db.rawQuery(sql, null);
        String[] strings = new String[]{"txt"};
        int[] ints = new int[] {R.id.customListView};

        SimpleCursorAdapter adapter1 = null;
        adapter1 = new SimpleCursorAdapter(customListView.getContext(), R.layout.listview_item, cursor, strings, ints, 0);
        customListView.setAdapter(adapter1);*/

    }
}