package com.example.emprende.emprende.Negocio;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DCliente;
import com.example.emprende.emprende.Dato.DRepartidor;

import java.util.ArrayList;

public class NRepartidor {

    DRepartidor dr;



    public NRepartidor(@Nullable Context context) {
        this.dr = new DRepartidor(context);
    }

    public void agregar(long id, String nombre, int telefono) {
        dr.setId(id);
        dr.setNombre(nombre);
        dr.setTelefono(telefono);
        dr.guardarRepartidor();
    }

    public void modificar(long id, String nombre, int telefono) {
        dr.setId(id);
        dr.setNombre(nombre);
        dr.setTelefono(telefono);
        dr.modficarRepartidor();
    }

    public void eliminar(long id) {
        dr.setId(id);

        dr.eliminarRepartidor();
    }

    public ArrayList<String> listarRepartidor() {
        return dr.mostrarRepartidor();

    }

}
