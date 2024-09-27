package com.example.emprende.emprende.Presentacion;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.emprende.emprende.Negocio.NCatalogo;
import com.example.emprende.emprende.Negocio.NCliente;
import com.example.emprende.emprende.Negocio.NRepartidor;
import com.example.emprende.emprende.R;
import com.example.emprende.emprende.db.ConexionSQLite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PCatalogoActivity extends AppCompatActivity {


    NCatalogo negocio;

    Button btAgregar,btModificar,btEliminar;
    EditText txtnombre,txtid;
    ListView lista;

    long id;
    long idProducto;
    String nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);


        negocio= new NCatalogo(PCatalogoActivity.this);


        btAgregar=findViewById(R.id.btAgregar);
        btModificar=findViewById(R.id.btModificar);
        btEliminar=findViewById(R.id.btEliminar);



        txtnombre=findViewById(R.id.txtnombre);
        txtid= findViewById(R.id.txtid);

        lista=findViewById(R.id.lista);
        listar();

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtener();
                agregar();
            }
        });
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtener();
                modificar();
            }
        });
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtener();
                eliminar();
            }
        });

        //Configura Listener.
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ite= lista.getItemAtPosition(position).toString();
                        // Abre una nueva Activity:
                        Bundle parmetros = new Bundle();
                        parmetros.putString("catalogo", ite);
                        Intent myIntent = new Intent(view.getContext(), PDetalleCatalogo.class);
                        myIntent.putExtras(parmetros);
                        startActivity(myIntent);

                    }
                }
        );
    }


    private void obtener() {

        id = Integer.parseInt(txtid.getText().toString());
        nombre= txtnombre.getText().toString();
    }

    private void agregar()  {


        negocio.agregar(id, nombre);

        this.limpiar();
        listar();
    }

    private void listar() {
        if(negocio != null){
            ArrayList<String> dato=negocio.listarCatalogo();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
            lista.setAdapter(adapter);
        }

    }

    private void limpiar() {
        txtid.setText("");
        txtnombre.setText("");
    }

    private void modificar() {
        negocio.modificar(id,nombre);
        this.limpiar();
        listar();
    }

    private void eliminar() {
        negocio.eliminar(id);
        this.limpiar();
        listar();
    }

}

