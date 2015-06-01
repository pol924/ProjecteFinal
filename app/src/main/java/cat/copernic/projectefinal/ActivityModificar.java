package cat.copernic.projectefinal;

import android.content.Intent;
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


public class ActivityModificar extends ActionBarActivity {


    Usuario user;
    CheckBox modificar, crear, persianaGaraje, calefaccion, aireAcondicionado;
    EditText nombre,password;
    Button buttonModificar;
    boolean d = false;
    MainActivity m = new MainActivity();
    String s = "";


    public void Eviar(Socket client){
        try {
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

            String us = null;
            String mod = null;
            String cre = null;
            String pers = null;
            String cal = null;
            String aire = null;


            if (user.isModificar()) {
                mod = "true";
            } else {
                mod = "false";
            }
            if (user.isCrear()) {
                cre = "true";
            } else {
                cre = "false";
            }
            if (user.isPersianaGaraje()) {
                pers = "true";
            } else {
                pers = "false";
            }
            if (user.isCalefaccion()) {
                cal = "true";
            } else {
                cal = "false";
            }
            if (user.isAireAcondicionado()) {
                aire = "true";
            } else {
                aire = "false";
            }


            String ua = "user"+"="+user.getNombre().toString()+"="+user.getPassword().toString()+"="+mod + "=" + cre + "=" + pers + "=" + cal + "=" + aire;


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

            String s2 = "modificar";
            writer.println(s2);
            writer.flush();
            writer.println(ua);
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
                s = "Usuario modificado";
            } else {
                d = false;
                s = "Usuario NO modificado";
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
                    Eviar(client);
                    //RECIBIR
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
        setContentView(R.layout.activity_modificar);

        nombre=(EditText)findViewById(R.id.textoNombre);
        password=(EditText)findViewById(R.id.textoPassword);

        modificar = (CheckBox) findViewById(R.id.checkBoxModificar);
        crear = (CheckBox)findViewById(R.id.checkBoxCrear);
        persianaGaraje = (CheckBox) findViewById(R.id.checkBoxGaraje);
        calefaccion = (CheckBox)findViewById(R.id.checkBoxCalefaccion);
        aireAcondicionado = (CheckBox) findViewById(R.id.checkBoxAire);

        buttonModificar=(Button) findViewById(R.id.buttonModificar);


        user = (Usuario)getIntent().getSerializableExtra("usuario");
        nombre.setText(user.getNombre());
        password.setText(user.getPassword());
        modificar.setChecked(user.isModificar());
        crear.setChecked(user.isCrear());
        persianaGaraje.setChecked(user.isPersianaGaraje());
        calefaccion.setChecked(user.isCalefaccion());
        aireAcondicionado.setChecked(user.isAireAcondicionado());


        buttonModificar.setOnClickListener(new View.OnClickListener() {
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
        getMenuInflater().inflate(R.menu.menu_activity_modificar, menu);
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