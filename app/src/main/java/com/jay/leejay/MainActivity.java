package com.jay.leejay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editLoginEmail, editLoginPwd;
    Button btnLogin;
    TextView tvSignUp, tvLostPwd;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        editLoginEmail = (EditText) findViewById(R.id.editLoginEmail);
        editLoginPwd = (EditText) findViewById(R.id.editLoginPwd);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvLostPwd = findViewById(R.id.tvLostPwd);

        SignUpActivity.MyDBHelper myDBHelper = new SignUpActivity.MyDBHelper(this);

        //로그인 버튼 누를시
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myDBHelper.getReadableDatabase();
                Cursor cursor = null;
                String sql = "SELECT PWD FROM student_informationTBL WHERE EMAIL = '" + editLoginEmail.getText().toString() + "'";
                cursor = db.rawQuery(sql, null);

                //student_informationTBL 테이블 속 email과 pwd를 비교
                if (editLoginPwd.getText().toString().equals(cursor)) {
                    //로그인 성공
                    Log.v("test", "성공?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setTitle("로그인 성공!");
                    builder.setMessage("다음 화면으로 이동하시겠습니까?");
                    //로그인 성공 후 리스트로 정보 표시
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "리스트로 이동", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, CustomActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
                // 로그인 실패
                else {
                    Log.v("test", "실패");
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그인 실패!");
                    builder.setMessage("이메일과 패스워드를 확인하시오");

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "확인", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, CustomActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                    editLoginEmail.setText("");
                    editLoginPwd.setText("");
                    editLoginEmail.requestFocus();

                }
            }
        });


        //신규 회원가입 화면 전환
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }


}
