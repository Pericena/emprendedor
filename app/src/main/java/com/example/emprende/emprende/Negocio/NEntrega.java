package com.example.emprende.emprende.Negocio;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DCliente;
import com.example.emprende.emprende.Dato.DEntrega;
import com.example.emprende.emprende.db.ConexionSQLite;

import java.util.ArrayList;

public class NEntrega {

    DEntrega d;
    public NEntrega(@Nullable Context context) {
        this.d = new DEntrega(context);
    }

    public void insertar(Long rep,Long idcliente, String latitud, String longitud) {
        d.setIdrep(rep);
        d.setIdcliente(idcliente);
        d.setLat(latitud);
        d.setLon(longitud);
        d.agregar();
    }


    public ArrayList<String> listar() {
        return d.mostrar();
    }
}
