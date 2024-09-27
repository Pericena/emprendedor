package com.example.emprende.emprende.Presentacion;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emprende.emprende.Negocio.NRepartidor;
import com.example.emprende.emprende.R;

import java.util.ArrayList;

public class PRepartidorActivity extends AppCompatActivity {

    NRepartidor negocio;

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


        negocio= new NRepartidor(PRepartidorActivity.this);


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
        ArrayList<String> dato=negocio.listarRepartidor();
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
        negocio.eliminar(id);
        this.limpiar();
        listar();
    }
}
