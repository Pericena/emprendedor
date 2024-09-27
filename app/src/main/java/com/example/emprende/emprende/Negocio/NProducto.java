package com.example.emprende.emprende.Negocio;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DProducto;
import com.example.emprende.emprende.Dato.DProducto;

import java.util.ArrayList;

public class NProducto {
    DProducto dp;
    public NProducto(@Nullable Context context) {
        this.dp = new DProducto(context);
    }

    public void agregar(long id, String nombre, String descripcion) {
        dp.setId(id);
        dp.setNombre(nombre);
        dp.setDescripcion(descripcion);
        dp.guardarProducto();
    }

    public void modificar(long id, String nombre, String descripcion) {
        dp.setId(id);
        dp.setNombre(nombre);
        dp.setDescripcion(descripcion);
        dp.modficarProducto();
    }

    public void eliminar(long id) {
        dp.setId(id);

        dp.eliminarProducto();
    }

    public ArrayList<String> listarProducto() {
        return dp.mostrarProducto();

    }
}
