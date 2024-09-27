package com.example.emprende.emprende.Presentacion;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.emprende.emprende.Negocio.NCliente;
import com.example.emprende.emprende.Negocio.NDetalleCatalogo;
import com.example.emprende.emprende.Negocio.NProducto;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emprende.emprende.Negocio.NProducto;
import com.example.emprende.emprende.Negocio.NProforma;
import com.example.emprende.emprende.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class PProformaActivity extends AppCompatActivity{
    NProforma negocio;

    NProducto np;
    NCliente nc;

    ListView lista;
    Button btAgregar,btEliminar,btPDF,btSeleccion;
    Spinner spinner1,spinner2;
    long id,idProducto,idCliente;
    int precio;
    EditText txtprecio,txtid,txtRutaArchivo;
    ArrayList<String> dato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proforma);

        np= new NProducto(PProformaActivity.this);
        nc= new NCliente(PProformaActivity.this);
        negocio= new NProforma(PProformaActivity.this);


        btAgregar=findViewById(R.id.btAgregar);

        btEliminar=findViewById(R.id.btEliminar);
        btPDF=findViewById(R.id.btPDF);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);

        cargarspinner();
        txtid=findViewById(R.id.txtid);
        txtprecio=findViewById(R.id.txtprecio);



        lista=findViewById(R.id.lista);
        listar();
        ActivityCompat.requestPermissions(this,new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtener();
                agregar();
            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtener();
                eliminar();
            }
        });

        btPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crerPDF();
            }
        });

    }

    /*private void archivo() {
        String data;
        PdfDocument pdfDocument = negocio.seleccionar();
        String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+id+"2.pdf";
        File file = new File(filePath);
        Uri uri;
       // String rutaArchivo = uri.getPath();
        String rutaArchivo = file.getPath();
        txtRutaArchivo.setText(rutaArchivo);
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }
*/
    private void crerPDF() {
        PdfDocument pdfDocument = negocio.cargarPDF(id);
        String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+id+".pdf";
        File file = new File(filePath);
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }

    private void cargarspinner() {
        dato = np.listarProducto();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        spinner1.setAdapter(adapter);

        dato = nc.listarCliente();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        spinner2.setAdapter(adapter);
    }

    private void obtener() {
        //parte de la interfaz
        id = Integer.parseInt(txtid.getText().toString());
        idProducto= Integer.valueOf(this.spinner1.getSelectedItem().toString().split("\n")[0]);
        idCliente= Integer.valueOf(this.spinner2.getSelectedItem().toString().split("\n")[0]);
        precio= Integer.parseInt(txtprecio.getText().toString());
    }

    private void agregar()  {
        negocio.agregar(id,idProducto,idCliente,precio);
        listar();
    }
    //se llama asi mismo porque es parte de la clase proforma
    private void listar() {
        dato=negocio.listar(idProducto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        lista.setAdapter(adapter);
    }



    private void eliminar() {
        negocio.eliminar(id);
        listar();
    }

}
