package com.example.emprende.emprende.Negocio;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DCatalogo;
import com.example.emprende.emprende.Dato.DCliente;
import com.example.emprende.emprende.Dato.DRepartidor;

import java.util.ArrayList;

public class NCatalogo {

    DCatalogo dr;



    public NCatalogo(@Nullable Context context) {
        this.dr = new DCatalogo(context);
    }

    public void agregar(long id, String nombre) {
        dr.setId(id);
        dr.setNombre(nombre);
        dr.guardarCatalogo();
    }

    public void modificar(long id, String nombre) {
        dr.setId(id);
        dr.setNombre(nombre);
        dr.modficarCatalogo();
    }

    public void eliminar(long id) {
        dr.setId(id);
        dr.eliminarCatalogo();
    }

    public ArrayList<String> listarCatalogo() {
        return dr.mostrarCatalogo();

    }
}
