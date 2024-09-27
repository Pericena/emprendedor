package com.example.emprende.emprende.Presentacion;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.emprende.emprende.Negocio.NDetalleCatalogo;
import com.example.emprende.emprende.Negocio.NProducto;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emprende.emprende.Negocio.NProducto;
import com.example.emprende.emprende.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class PDetalleCatalogo extends AppCompatActivity {

    NDetalleCatalogo negocio;

    NProducto np;
    TextView textViewid, textViewnombre;
    ListView lista;
    Button btAgregar, btEliminar, btPDF, btW,btimagen;
    Spinner spinner1;
    long id, idProducto, idCatalogo;
    String nombre;
    ImageView imageView;
    ArrayList<String> dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_catalogo);

        negocio = new NDetalleCatalogo(PDetalleCatalogo.this);
        np = new NProducto(PDetalleCatalogo.this);



        btAgregar = findViewById(R.id.btAgregar);
        btEliminar = findViewById(R.id.btEliminar);
        btPDF = findViewById(R.id.btPDF);
        btW = findViewById(R.id.btW);
        spinner1 = findViewById(R.id.spinner1);


        String catalogo = getIntent().getExtras().getString("catalogo");

        this.idCatalogo = Integer.valueOf(catalogo.split("\n")[0]);
        nombre = catalogo.split("\n")[1];
        cargarspinner();


        textViewid = findViewById(R.id.textViewid);
        textViewid.setText("Identificador N: " + idCatalogo);
        textViewnombre = findViewById(R.id.textViewnombre);
        textViewnombre.setText(nombre);

        lista = findViewById(R.id.lista);
        listar();
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

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

        btW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarW();
            }
        });



    }

    private void enviarW() {
        String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+nombre+".pdf";
        //String filePath = getFilesDir().getAbsolutePath() + "/Download/"+nombre+".pdf";
// Crea un URI a partir del archivo
        Uri fileUri = Uri.parse("file://" + filePath);
        //File outputFile = new File(Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DOWNLOADS), nombre+".pdf");
        File outputFile = new File(String.valueOf(filePath));
        Uri uri = Uri.fromFile(outputFile);
       /* Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage("com.whatsapp");
        startActivity(share);
*/
// Crea un intent con la acción de enviar
        /*Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.setPackage("com.whatsapp"); // Especifica que se abrirá WhatsApp*/

// Comprueba si WhatsApp está instalado en el dispositivo
       /* if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // WhatsApp no está instalado
            Toast.makeText(getApplicationContext(), "WhatsApp no está instalado", Toast.LENGTH_SHORT).show();
        }*/

        /*String filePath = Environment.getExternalStorageDirectory().getPath() + "/Download/" + nombre + ".pdf";
        File file = new File(filePath);

        Uri fileUri = FileProvider.getUriForFile(this, "com.example.emprende.emprende", file);*/

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, fileUri);
        share.setPackage("com.whatsapp");

        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (share.resolveActivity(getPackageManager()) != null) {
            startActivity(share);
        } else {
            Toast.makeText(getApplicationContext(), "WhatsApp no está instalado", Toast.LENGTH_SHORT).show();
        }
    }





    private void crerPDF() {

        PdfDocument pdfDocument = negocio.cargarPDF(idCatalogo);
        String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+nombre+".pdf";
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
    }

    private void obtener() {

        id =negocio.obtenerNuevo_id(idCatalogo) ;
        idProducto= Integer.valueOf(this.spinner1.getSelectedItem().toString().split("\n")[0]);

    }

    private void agregar()  {
        negocio.agregar(idCatalogo, id, idProducto);
        listar();
    }

    private void listar() {
        dato=negocio.listar(idCatalogo);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        lista.setAdapter(adapter);
    }



    private void eliminar() {
        negocio.eliminar(idCatalogo,idProducto);
        listar();
    }


}


