package com.example.kkaminets.myapplication4;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kkaminets.myapplication4.Fire.Fire;
import com.example.kkaminets.myapplication4.listOne.Spisok1;
import com.example.kkaminets.myapplication4.listTwo.Spisok2;

public class Second extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }


    public void Spisok1(View view) {
        Intent intent = new Intent(Second.this, Spisok1.class);
        startActivity(intent);
    }

    public void Spisok2(View view) {
        Intent intent = new Intent(Second.this, Spisok2.class);
        startActivity(intent);
    }

    public void Fire (View view){
        Intent intent = new Intent(Second.this, Fire.class);
        startActivity(intent);

    }
}
