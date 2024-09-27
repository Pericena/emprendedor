package com.example.emprende.emprende.Negocio;

import android.content.Context;
import android.graphics.pdf.PdfDocument;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.Dato.DDetalleCatalogo;
import com.example.emprende.emprende.Dato.DProducto;
import com.example.emprende.emprende.Presentacion.PDetalleCatalogo;

import java.util.ArrayList;

public class NDetalleCatalogo {
    DDetalleCatalogo dato;

    public NDetalleCatalogo(@Nullable Context context) { this.dato = new DDetalleCatalogo(context);
    }
    public long obtenerNuevo_id(long idCatalogo) {
        return dato.obtenerNuevo_id();
    }

    public void agregar(long idCatalogo, long id, long idProducto) {
        dato.setIdCatalogo(idCatalogo);
        dato.setId(id);
        dato.setIdProducto(idProducto);
        dato.agregar();
    }

    public ArrayList<String> listar(long idCatalogo) {
        dato.setIdCatalogo(idCatalogo);
        return dato.listar();
    }

    public void eliminar(long idCatalogo, long idProducto) {
        dato.setIdCatalogo(idCatalogo);
        dato.setIdProducto(idProducto);
        dato.eliminar();
    }

    public PdfDocument cargarPDF(long idCatalogo) {
        dato.setIdCatalogo(idCatalogo);
        return dato.cargarPDF();
    }
}
