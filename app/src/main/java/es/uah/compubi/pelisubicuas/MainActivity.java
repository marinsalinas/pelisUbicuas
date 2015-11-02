package es.uah.compubi.pelisubicuas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Texto a Cargar en AutoComplete
    private String[] perfiles = { "Niño", "Niña", "Hombre",
            "Mujer", "Padre", "Madre", "Abuelo", "Abuela",
            "Admin", "Pruebas" };

    private AutoCompleteTextView perfilAutoComp;
    private EditText passwordTextV;
    private Button buttonCompenzar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inizializar Objetos de las vistas
        final AutoCompleteTextView perfilAutoComp= (AutoCompleteTextView) findViewById(R.id.profile_auto);
        final EditText passwordTextV = (EditText) findViewById(R.id.password);
        Button buttonCompenzar = (Button) findViewById(R.id.comenzar_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, perfiles);

        perfilAutoComp.setThreshold(0);
        perfilAutoComp.setAdapter(adapter);

        buttonCompenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(perfilAutoComp.getText().toString()) || TextUtils.isEmpty(passwordTextV.getText().toString())){
                    Toast.makeText(getApplicationContext(), R.string.perfil_password_val, Toast.LENGTH_LONG).show();
                    return;
                }
                startActivity(new Intent(getApplicationContext(), CategoriasActivity.class));
                //finish();
            }
        });

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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Estas seguro de cerrar la aplicación")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }


}
