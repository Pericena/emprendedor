package com.example.emprende.emprende.Presentacion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emprende.emprende.Negocio.NCliente;
import com.example.emprende.emprende.Negocio.NProducto;
import com.example.emprende.emprende.R;

import java.io.IOException;
import java.util.ArrayList;

public class PProductoActivity extends AppCompatActivity {
    private static final int REQUEST_SELECT_IMAGE = 1 ;
    NProducto negocio;

    Button btAgregar,btModificar,btEliminar,btimagen;
    EditText txtId,txtNombre,txtdescripcion;
    ListView lista;
    ImageView imageView;
    long id;
    String nombre;
    String descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);


        negocio= new NProducto(PProductoActivity.this);


        btAgregar=findViewById(R.id.btAgregar);
        btModificar=findViewById(R.id.btModificar);
        btEliminar=findViewById(R.id.btEliminar);
        btimagen=findViewById(R.id.btimagen);
        imageView= findViewById(R.id.imageView);

        txtId=findViewById(R.id.txtid);
        txtNombre=findViewById(R.id.txtnombre);
        txtdescripcion=findViewById(R.id.txtdescripcion);

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
        btimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un intent para abrir la galería
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });
    }

    private void obtener() {
        id = Integer.parseInt(txtId.getText().toString());
        nombre= txtNombre.getText().toString();
        descripcion= txtdescripcion.getText().toString();
    }

    private void agregar()  {

        negocio.agregar(id, nombre, descripcion);

        this.limpiar();
        listar();
    }

    private void listar() {
        ArrayList<String> dato=negocio.listarProducto();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        lista.setAdapter(adapter);
    }

    private void limpiar() {
        if (txtId.getText().length() > 0 || txtdescripcion.getText().length() > 0 || txtNombre.getText().length() > 0) {
            txtId.setText("");
            txtdescripcion.setText("");
            txtNombre.setText("");
            System.out.println("Campos limpiados correctamente");
        } else {
            System.out.println("Los campos ya están vacíos");
        }
    }


    private void modificar() {
        negocio.modificar(id,nombre,descripcion);
        this.limpiar();
        listar();
    }

    private void eliminar() {
        negocio.eliminar(id);
        this.limpiar();
        listar();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            // Obtiene la URI de la imagen seleccionada
            Uri imageUri = data.getData();

            try {
                // Convierte la URI a un Bitmap (puedes omitir esto si no necesitas mostrar la imagen)
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Muestra el Bitmap en un ImageView (puedes omitir esto si no necesitas mostrar la imagen)
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
