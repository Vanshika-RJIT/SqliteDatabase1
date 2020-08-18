package com.example.sqlitedatabase1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText t1,t2,t3;
    Button b1,b2,b3;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        t1 = findViewById(R.id.Email);
        t2 = findViewById(R.id.Password);
        t3 = findViewById(R.id.Phone);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        sqLiteDatabase = openOrCreateDatabase("USER_DATABASE", MODE_PRIVATE, null);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=t1.getText().toString();
                String password=t2.getText().toString();
                String phone=t3.getText().toString();

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS table1(Email varchar,Password varchar,Phone varchar);");
                sqLiteDatabase.execSQL("INSERT INTO table1 VALUES('"+username+"','"+password+"','"+phone+"');");
                Toast.makeText(MainActivity2.this,"INSERTED DATA SUCCESSFULLY",Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(i);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   @SuppressLint("Recycle") Cursor result=sqLiteDatabase.rawQuery("SELECT * FROM table1",null);
                   while(result.moveToNext())
                   {
                       String username=result.getString(0);
                       String password=result.getString(1);
                       String phone=result.getString(2);
                       Toast.makeText(MainActivity2.this,"Username:"+username+"\n"+"Password:"+password+"\n"+"Phone"+phone,Toast.LENGTH_LONG).show();
                   }

            }
        });
    }
}