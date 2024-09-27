package com.example.emprende.emprende.Negocio;

import android.content.Context;
import android.graphics.pdf.PdfDocument;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DDetalleCatalogo;

import java.util.ArrayList;
import android.graphics.pdf.PdfDocument;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DDetalleCatalogo;
import com.example.emprende.emprende.Dato.DProducto;
import com.example.emprende.emprende.Dato.DProforma;
import com.example.emprende.emprende.Presentacion.PDetalleCatalogo;

import java.util.ArrayList;

public class NProforma {
    DProforma dato;

    public NProforma(@Nullable Context context) { this.dato = new DProforma(context);
    }
    //public long obtenerNuevo_id(long idCatalogo) {
    //  return dato.obtenerNuevo_id();
    //}

    public void agregar(long id,long idProducto,long idCliente,int precio) {

        dato.setId(id);
        dato.setIdProducto(idProducto);
        dato.setIdCliente(idCliente);
        dato.setPrecio(precio);
        dato.agregar();
    }

    public ArrayList<String> listar(long idProducto) {
        dato.setIdProducto(idProducto);
        return dato.listar();
    }

    public void eliminar( long idProducto) {

        dato.setIdProducto(idProducto);
        dato.eliminar();
    }

    public PdfDocument cargarPDF(long id) {
        try {
            dato.setId(id);
            return dato.cargarPDF(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
