package com.example.cap4st;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivityDeplasareRotatie extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//change this!1
    }
    public void MyClick(View v)
    { try {Intent myIntentA1A2 = new
            Intent(MainActivityDeplasareRotatie.this,Draw1.class);//change this2!
        startActivityForResult(myIntentA1A2,1);
    } catch (Exception e) {
        Toast.makeText(getBaseContext(),
                e.getMessage(),
                Toast.LENGTH_LONG).show();
    }
    }
}