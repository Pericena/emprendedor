package com.example.emprende.emprende.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;
import android.graphics.pdf.PdfDocument.PageInfo;


import com.example.emprende.emprende.BuildConfig;
import com.example.emprende.emprende.Negocio.NCliente;
import com.example.emprende.emprende.Negocio.NRepartidor;
import com.example.emprende.emprende.R;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PClienteActivity extends AppCompatActivity {

    NCliente negocio;

    Button btAgregar,btModificar,btEliminar;
    EditText txtId,txtNombre,txtTelefono;
    ListView lista;

    long id;
    String nombre;
    int telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepartidor);

        negocio= new NCliente(PClienteActivity.this);

        btAgregar=findViewById(R.id.btAgregar);
        btModificar=findViewById(R.id.btModificar);
        btEliminar=findViewById(R.id.btEliminar);


        txtId=findViewById(R.id.txtid);
        txtNombre=findViewById(R.id.txtnombre);
        txtTelefono=findViewById(R.id.txtTelefono);

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
    }

    private void obtener() {
        id = Integer.parseInt(txtId.getText().toString());
        nombre= txtNombre.getText().toString();
        telefono= Integer.valueOf(txtTelefono.getText().toString());
    }

    private void agregar()  {
        negocio.agregar(id, nombre, telefono);
        this.limpiar();
        listar();
    }

    private void listar() {
        ArrayList<String> dato=negocio.listarCliente();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        lista.setAdapter(adapter);
    }

    private void limpiar() {
        try {
            // Validar que los campos no sean nulos antes de limpiar
            if (txtId != null && txtTelefono != null && txtNombre != null) {
                txtId.setText("");
                txtTelefono.setText("");
                txtNombre.setText("");
            } else {
                // Si algún campo es nulo, lanza una advertencia en el log o la interfaz
                System.out.println("Advertencia: Uno o más campos están nulos.");
            }
        } catch (Exception e) {
            // Manejar cualquier excepción inesperada
            System.out.println("Error al limpiar los campos: " + e.getMessage());
        }
    }


    private void modificar() {
        negocio.modificar(id,nombre,telefono);
        this.limpiar();
        listar();
    }

    private void eliminar() {
        if (id > 0) {  // Validar que el id sea un valor positivo
            boolean resultado = negocio.eliminar(id);
            if (resultado) {
                this.limpiar();
                listar();
                System.out.println("El cliente ha sido eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el cliente. Verifique si el id es correcto.");
            }
        } else {
            System.out.println("El id no es válido. Ingrese un id correcto.");
        }
    }


}