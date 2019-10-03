package com.example.miprimeraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

public class IMCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        dibujarFlechaAtras();

        ImageView imageView = findViewById(R.id.imagen_playa);
        imageView.setImageResource(R.drawable.playa);

    }

    private void dibujarFlechaAtras() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("MIAPP", "Se ha tocado un elemento de la barra/menú");

        if (android.R.id.home ==  item.getItemId()){
            Log.d("MIAPP", "Ha tocado la flecha para atrás");
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
