package es.iessanclemente.a14felipecm.proyecto_a14felipecm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Oferta_Educativa extends Activity {


    Spinner sp;
    ListView lv;
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta__educativa);




        sp = (Spinner) findViewById(R.id.spin_oferta_pres);
        lv = (ListView) findViewById(R.id.lista_modulos);


        /*ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,R.array.presenciales,android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);*/
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                switch (sp.getSelectedItemPosition()){
                    case 0:
                        adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.array_smr_texto));
                        lv.setAdapter(adaptador);
                        break;
                    case 1:
                        adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.array_asir_presencial));
                        lv.setAdapter(adaptador);
                        break;
                    case 2:
                        adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.array_dam_presencial));
                        lv.setAdapter(adaptador);
                        break;
                    case 3:
                        adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.array_asir_modular));
                        lv.setAdapter(adaptador);
                        break;
                    case 4:
                        adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.array_daw_modular));
                        lv.setAdapter(adaptador);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_youtube){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_youtube))));
            return true;
        }
        if (id == R.id.action_facebook) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_facebook))));
            return true;
        }
        if (id == R.id.action_sair){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
