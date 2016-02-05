package com.example.upam.almacenar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText Name,Age,Weigth,Heigth;
    ImageButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Name=(EditText)findViewById(R.id.editText);
        Age=(EditText)findViewById(R.id.editText2);
        Weigth=(EditText)findViewById(R.id.editText3);
        Heigth=(EditText)findViewById(R.id.editText4);

        SharedPreferences pref=getSharedPreferences("IMC", Context.MODE_PRIVATE);
        Name.setText(pref.getString("name",""));
        Age.setText(String.valueOf(pref.getInt("age", 0)));
        Weigth.setText(String.valueOf(pref.getInt("weigth", 0)));
        Heigth.setText(String.valueOf( pref.getFloat("heigth", 0f)));

    }

    public  void guardar(View v)
    {
      b1=(ImageButton)findViewById(R.id.imageButton2);

        Float imc;
        imc=Float.valueOf(Weigth.getText().toString())/(Float.valueOf(Heigth.getText().toString())*Float.valueOf(Heigth.getText().toString()));

        SharedPreferences pref=getSharedPreferences("IMC", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("name",Name.getText().toString());
        editor.putInt("age", Integer.parseInt(Age.getText().toString()));
        editor.putInt("weigth", Integer.parseInt(Weigth.getText().toString()));
        editor.putFloat("heigth", Float.valueOf(Heigth.getText().toString()));
        editor.putFloat("imc-calc",imc);
        editor.commit();
        b1.setVisibility(View.VISIBLE);

    }

    public void ver(View v)
    {
        SharedPreferences pref=getSharedPreferences("IMC", Context.MODE_PRIVATE);
        String n=pref.getString("name", "");
        String a=String.valueOf(pref.getInt("age", 0));
        String w=String.valueOf(pref.getInt("weigth", 0));
        String h=String.valueOf(pref.getFloat("heigth", 0f));
        String i=String.valueOf(pref.getFloat("imc-calc", 0f));
        Toast.makeText(this,"IMC Datos \n "+n+" \n"+a+" \n"+w+" \n"+h+" \n"+i,Toast.LENGTH_SHORT).show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
