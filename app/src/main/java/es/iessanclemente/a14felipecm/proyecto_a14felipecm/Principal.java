package es.iessanclemente.a14felipecm.proyecto_a14felipecm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    private SharedPreferences datosPrivados;
    private static final int DIALOGO_ENTRADA_TEXTO = 6;

    // Variable para crear as ventás de diálogo
    AlertDialog.Builder venta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Gestionamos las preferencias para el loguin
        datosPrivados = getSharedPreferences("login", MODE_PRIVATE);
        if (datosPrivados.getString("password","").equals("")){
            SharedPreferences.Editor editor = datosPrivados.edit();
            editor.putString("password", "abc123.");
            editor.putString("nome","felipe");
            editor.commit();
            Log.i("PREFERENCIAS","Primer uso, inserto contraseña por defecto");
            //Toast.makeText(Principal.this, datosPrivados.getString("password",""), Toast.LENGTH_SHORT).show();
        } else {
            Log.i("PREFERENCIAS","2 uso o contraseña cambiada, no insertamos");
/*            Toast.makeText(Principal.this, datosPrivados.getString("password",""), Toast.LENGTH_SHORT).show();
            Toast.makeText(Principal.this, "lleno", Toast.LENGTH_SHORT).show();*/
        }
    }

    public void verCentro(View v){
        Intent o = new Intent(this, Centro.class);
        startActivity(o);
    }

    public void verOferta(View v){
        Intent u = new Intent(this, Oferta_Educativa.class);
        startActivity(u);
    }

    public void verAlumno(View v){
        showDialog(DIALOGO_ENTRADA_TEXTO);
    }

    public void verContacto(View v){
        Intent c = new Intent(this, Contacto.class);
        startActivity(c);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case DIALOGO_ENTRADA_TEXTO:
                // Primeiro preparamos o interior da ventá de diálogo inflando o seu
                // fichero XML
                String infService = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(infService);
                // Inflamos o compoñente composto definido no XML
                View inflador = li.inflate(R.layout.layout_loguin, null);
                // Buscamos os compoñentes dentro do Diálogo
                final TextView etNome = (TextView) inflador.findViewById(R.id.et_nome);
                final TextView etContrasinal = (TextView) inflador.findViewById(R.id.et_contrasinal);
                venta = new AlertDialog.Builder(this);
                venta.setTitle("Indica usuario e contrasinal");
                // Asignamos o contido dentro do diálogo (o que inflamos antes)
                venta.setView(inflador);
                // Non se pode incluír unha mensaxe dentro deste tipo de diálogo!!!
                venta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        //comprobamos los datos introducidos con los del sharedpreferences
                        if (datosPrivados.getString("nome","").compareToIgnoreCase(etNome.getText().toString())==0&&datosPrivados.getString("password","").equals(etContrasinal.getText().toString())){
                            startActivity(new Intent(getApplicationContext(),Alumno.class));
                        }else{
                            Toast.makeText(Principal.this, "Loguin Error", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                venta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        //Toast.makeText(getApplicationContext(), "Premeches en 'Cancelar'", 1).show();
                    }
                });
                return venta.create();
        }
        return null;
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
