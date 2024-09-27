package com.example.emprende.emprende.Presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emprende.emprende.Negocio.NCliente;
import com.example.emprende.emprende.Negocio.NEntrega;
import com.example.emprende.emprende.R;

import java.util.ArrayList;

public class PEntregaActivity  extends AppCompatActivity {

    ListView lista;

    NEntrega negocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pentrega);


        negocio= new NEntrega(PEntregaActivity.this);


        lista=findViewById(R.id.lista);

        listar();

    }


    private void listar() {
        ArrayList<String> dato=negocio.listar();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        lista.setAdapter(adapter);
    }

}
