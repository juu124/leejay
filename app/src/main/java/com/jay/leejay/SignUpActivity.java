package com.jay.leejay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    SQLiteDatabase db;

    EditText edtDNO, edtName, editTel, editEmail, editPwd;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtDNO = findViewById(R.id.edtDNO);
        edtName = findViewById(R.id.edtName);
        editTel = findViewById(R.id.editTel);
        editEmail = findViewById(R.id.editEmail);
        editPwd = findViewById(R.id.editPwd);
        btnSignUp = findViewById(R.id.btnSignUp);

        MyDBHelper myDBHelper = new MyDBHelper(this);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("DNO", edtDNO.getText().toString());
                values.put("NAME", edtName.getText().toString());
                values.put("TEL", editTel.getText().toString());
                values.put("EMAIL", editEmail.getText().toString());
                values.put("PWD", editPwd.getText().toString());

                db.insert("student_informationTBL", null, values);
                db.close();

                Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(@Nullable Context context){
            super(context, "student_information.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE student_informationTBL (DNO INTEGER PRIMARY KEY, NAME VARCHAR(20) NOT NULL, TEL VARCHAR(13), EMAIL VARCHAR(100) NOT NULL, PWD CHAR(10) NOT NULL);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS student_informationTBL;";
            db.execSQL(sql);
            onCreate(db);

        }
    }
}