package cat.copernic.projectefinal;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ActivityCrear extends ActionBarActivity {

    Usuario user;

    CheckBox modificar, crear, persianaGaraje, calefaccion, aireAcondicionado;
    EditText nombre,password;
    Button buttonCrear;
    boolean d = false;
    MainActivity m = new MainActivity();
    String s = "";

    public void Enviar(Socket client){
        try {
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

            String us = null;
            String mod = null;
            String cre = null;
            String pers = null;
            String cal = null;
            String aire = null;


            if (modificar.isChecked()) {
                mod = "true";
            } else {
                mod = "false";
            }
            if (crear.isChecked()) {
                cre = "true";
            } else {
                cre = "false";
            }
            if (persianaGaraje.isChecked()) {
                pers = "true";
            } else {
                pers = "false";
            }
            if (calefaccion.isChecked()) {
                cal = "true";
            } else {
                cal = "false";
            }
            if (aireAcondicionado.isChecked()) {
                aire = "true";
            } else {
                aire = "false";
            }


            us = "user"+"="+nombre.getText().toString() + "=" + password.getText().toString() + "=" + mod + "=" + cre + "=" + pers + "=" + cal + "=" + aire;

            String s2 = "crear";
            writer.println(s2);
            writer.flush();
            writer.println(us);
            writer.flush();


        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void Recibir(Socket client){
        try {
            BufferedReader reader = new BufferedReader((new InputStreamReader(client.getInputStream())));

            String c = reader.readLine();
            String ok = "1";
            if (ok.equals(c)) {
                d = true;
                s = "Usuario creado";
            } else {
                d = false;
                s = "Usuario NO creado";
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            s = "sin readline";
        }
    }

    public void Completado(String s){
        if (d) {
            nombre.setText("");
            password.setText("");
            modificar.setChecked(false);
            crear.setChecked(false);
            persianaGaraje.setChecked(false);
            calefaccion.setChecked(false);
            aireAcondicionado.setChecked(false);
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
        }
    }


    public void Connexion(){
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {

                String host = (String) params[0];
                Integer port = (Integer) params[1];

                try {
                    Socket client = new Socket(host, port);

                    //ENVIAR
                    Enviar(client);
                    //RECIBIR
                    //s = "Conexion Completa";

                    Recibir(client);

                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    s = "sin conexion";
                }


                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Completado(s);

            }
        }.execute(m.LOCALHOST_PC, m.PORT);
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);


        nombre=(EditText)findViewById(R.id.textoNombre);
        password=(EditText)findViewById(R.id.textoPassword);

        modificar = (CheckBox) findViewById(R.id.checkBoxModificar);
        crear = (CheckBox)findViewById(R.id.checkBoxCrear);
        persianaGaraje = (CheckBox) findViewById(R.id.checkBoxGaraje);
        calefaccion = (CheckBox)findViewById(R.id.checkBoxCalefaccion);
        aireAcondicionado = (CheckBox) findViewById(R.id.checkBoxAire);

        buttonCrear= (Button) findViewById(R.id.buttonCrearUsuario);

        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombre.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                }else{

                    Connexion();

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_crear, menu);


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
