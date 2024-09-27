package com.example.emprende.emprende.Negocio;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DCliente;

import java.util.ArrayList;

public class NCliente {

    DCliente dc;



    public NCliente(@Nullable Context context) {
        this.dc = new DCliente(context);
    }

    public void agregar(long id, String nombre, int telefono) {
        dc.setId(id);
        dc.setNombre(nombre);
        dc.setTelefono(telefono);
        dc.guardarCliente();
    }

    public void modificar(long id, String nombre, int telefono) {
        dc.setId(id);
        dc.setNombre(nombre);
        dc.setTelefono(telefono);
        dc.modficarCliente();
    }

    public boolean eliminar(long id) {
        if (id > 0) {  // Validar que el id sea un valor válido
            dc.setId(id);
            return dc.eliminarCliente();
        } else {
            System.out.println("Error: El id proporcionado no es válido.");
            return false;
        }
    }


    public ArrayList<String> listarCliente() {
        return dc.mostrarClientess();

    }


}
