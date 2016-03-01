package es.iessanclemente.a14felipecm.proyecto_a14felipecm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Alumno extends AppCompatActivity {
    private SharedPreferences datosPrivados;
    private static final int DIALOGO_ENTRADA_TEXTO = 6;

    // Variable para crear as ventás de diálogo
    AlertDialog.Builder venta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        datosPrivados = getSharedPreferences("login", MODE_PRIVATE);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOGO_ENTRADA_TEXTO:
                // Primeiro preparamos o interior da ventá de diálogo inflando o seu
                // fichero XML
                String infService = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(infService);
                // Inflamos o compoñente composto definido no XML
                View inflador = li.inflate(R.layout.layout_cambio_pass, null);
                // Buscamos os compoñentes dentro do Diálogo
                final TextView etContrasinauno = (TextView) inflador.findViewById(R.id.et_cambio_pass_primera);
                final TextView etContrasinados = (TextView) inflador.findViewById(R.id.et_cambio_pass_segunda);
                venta = new AlertDialog.Builder(this);
                venta.setTitle("Indica la nueva contraseña");
                // Asignamos o contido dentro do diálogo (o que inflamos antes)
                venta.setView(inflador);
                // Non se pode incluír unha mensaxe dentro deste tipo de diálogo!!!
                venta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        //comprobamos los datos introducidos con los del sharedpreferences
                        if (etContrasinauno.getText().toString().equals(etContrasinados.getText().toString())){
                            SharedPreferences.Editor editor = datosPrivados.edit();
                            editor.putString("password", etContrasinauno.getText().toString());
                            editor.commit();
                            Toast.makeText(Alumno.this, "Contraseña cambiada", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Alumno.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
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
    public void verCambioPass(View v){
        showDialog(DIALOGO_ENTRADA_TEXTO);
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
