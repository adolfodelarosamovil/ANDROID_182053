package com.example.miprimeraapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private static String PREGUNTA = "¿A donde quieres ir?";
    private static String RESPUESTA_VERSIONES = "ir a versiones";
    private static String RESPUESTA_LETRAS = "ir a letras";
    private static String RESPUESTA_IMC = "ir a imc";
    private static String RESPUESTA_CAJAS = "ir a cajas";
    private static String RESPUESTA_WHATSUP = "ir a";
    private static String RESPUESTA_SALIR = "salir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void cajas(View view) {

        Intent intent = new Intent(this, CajaActivity.class);
        //lanzo
        startActivity(intent);

    }


    public void letras(View view) {
        Intent intent = new Intent(this, DaLaVueltaActivity.class);
        //lanzo
        startActivity(intent);

    }

    public void version(View view) {
        Intent intent = new Intent(this, VersionActivity.class);
        //lanzo
        startActivity(intent);

    }

    public void imc(View view) {
        Intent intent = new Intent(this, IMCActivity.class);
        //lanzo
        startActivity(intent);

    }

    public void whatsup(View view) {
        Intent intent = new Intent(this, WhatsUpActivity.class);
        //lanzo
        startActivity(intent);
    }

    //Debo sobreescribir este metodo para cargar mi menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater(); // Este objeto será el encargado de cargar/inflar mi vista
        mi.inflate(R.menu.menu_ppal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Log.d("MIAPP", "Has tocado la flecha para atrás");
                finish();
                break;
            case R.id.salir:
                Log.d("MIAPP", "Has tocado la opción de Preguntar");
                pregunta();
                break;
            case R.id.buscar:
                Log.d("MIAPP", "Has tocado la opción de buscar");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void pregunta() {


        mediaPlayer = MediaPlayer.create(this, R.raw.pregunta_2);
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(100, 100);

        Intent intent = new Intent
                (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, PREGUNTA);
        startActivityForResult(intent, 200);

        mediaPlayer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra
                        (RecognizerIntent.EXTRA_RESULTS);
                String mAnswer = results.get(0).toLowerCase();

                Log.d("MIAPP", mAnswer);
                if (mAnswer.equals(RESPUESTA_VERSIONES))
                {
                    Log.d("MIAPP", "RESPUESTA VERSIONES");
                    Intent intent = new Intent(this, VersionActivity.class);
                    startActivity(intent);
                }else if (mAnswer.equals(RESPUESTA_LETRAS)) {
                    Log.d("MIAPP", "RESPUESTA LETRAS");
                    Intent intent = new Intent(this, DaLaVueltaActivity.class);
                    startActivity(intent);
                } else if (mAnswer.equals(RESPUESTA_IMC)) {
                    Log.d("MIAPP", "RESPUESTA IMC");
                    Intent intent = new Intent(this, IMCActivity.class);
                    startActivity(intent);
                } else if (mAnswer.equals(RESPUESTA_CAJAS)) {
                    Log.d("MIAPP", "RESPUESTA CAJAS");
                    Intent intent = new Intent(this, CajaActivity.class);
                    startActivity(intent);
                } else if (mAnswer.equals(RESPUESTA_WHATSUP)) {
                    Log.d("MIAPP", "RESPUESTA WHATSUP");
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                } else if (mAnswer.equals(RESPUESTA_SALIR)) {
                    Log.d("MIAPP", "RESPUESTA SALIR");
                    finish();
                } else {
                    Log.d("MIAPP", "RESPUESTA MALA");
                    Toast.makeText(this, "RESPUESTA MALA", Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(this, R.raw.dennis_2);
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setVolume(100, 100);
                    mediaPlayer.start();
                }
            }
        }
    }


}
