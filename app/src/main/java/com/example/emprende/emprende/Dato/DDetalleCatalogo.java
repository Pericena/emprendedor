package com.example.emprende.emprende.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.db.ConexionSQLite;

import java.util.ArrayList;

public class DDetalleCatalogo {

    ConexionSQLite conextion;
    Cursor cursor;
    long idCatalogo,id,idProducto;

    public DDetalleCatalogo(@Nullable Context context) {
        conextion= new ConexionSQLite(context);
    }

    public long obtenerNuevo_id() {
        long i=1;
        SQLiteDatabase db = conextion.getWritableDatabase();
        cursor = null;

        cursor = db.rawQuery("select count(*) from detallecatalogo where idcatalogo='"+idCatalogo+"'", null);
        cursor.moveToFirst();
        long x=cursor.getInt(0);

        if (x!=0){
            cursor = db.rawQuery("select max(id) from detallecatalogo where idcatalogo='"+idCatalogo+"'", null);
            cursor.moveToFirst();
            i=cursor.getInt(0)+1;
        }
        cursor.close();
        return i;
    }

    public long getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(long idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public void agregar() {
        try {
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("idcatalogo", idCatalogo);
            registro.put("id", id);
            registro.put("idproducto", idProducto);
            id = db.insert(conextion.table_detalle_catalogo, null, registro);

        } catch (Exception ex) {
            ex.toString();

        }
    }

    public ArrayList<String> listar() {
        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaDCatalogo = new ArrayList<>();
        String pCatalogo = "";
        cursor = null;

        cursor = db.rawQuery("select dc.idcatalogo, dc.id, p.nombre from detallecatalogo as dc , producto as p "+ "WHERE dc.idcatalogo= '"+idCatalogo+"' and dc.idproducto = p.id ", null);
        if (cursor.moveToFirst()) {
            do {
                pCatalogo = String.valueOf(cursor.getInt(0)) + "\n";
                pCatalogo += String.valueOf(cursor.getInt(1))+ "\n";
                pCatalogo += String.valueOf(cursor.getString(2));
                listaDCatalogo.add(pCatalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaDCatalogo;
    }

    public void eliminar() {

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            //db.execSQL("DELETE FROM"  + conextion.table_detalle_catalogo + "' WHERE idcatalogo= '"+idCatalogo+"'");
            db.execSQL("DELETE FROM " + conextion.table_detalle_catalogo + " WHERE idcatalogo = '" + idCatalogo + "' and idproducto = '"+idProducto+"'");
        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }

    }

    public PdfDocument cargarPDF() {

        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaDCatalogo = new ArrayList<>();
        String pCatalogo = "";
        cursor = null;

        int y=25;
        int i=1;

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600,1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);


        cursor = db.rawQuery("select dc.idcatalogo, dc.id, p.nombre from detallecatalogo as dc , producto as p "+ "WHERE dc.idcatalogo= '"+idCatalogo+"' and dc.idproducto = p.id ", null);
        if (cursor.moveToFirst()) {
            do {
                pCatalogo = String.valueOf(cursor.getInt(0)) + "\t";
                pCatalogo += String.valueOf(cursor.getInt(1))+ "\t";
                pCatalogo += String.valueOf(cursor.getString(2));

                i++;

                //page.getCanvas().drawText(pCatalogo,10, 25, new Paint());
                page.getCanvas().drawText(pCatalogo,10, y*i, new Paint());

            } while (cursor.moveToNext());
        }
        cursor.close();
        pdfDocument.finishPage(page);

        return pdfDocument;
    }
}
