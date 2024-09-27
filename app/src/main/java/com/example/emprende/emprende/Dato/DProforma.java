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
public class DProforma {


    ConexionSQLite conextion;
    Cursor cursor;
    long id,idProducto,idCliente;
    int precio;

    public DProforma(@Nullable Context context) {
        conextion= new ConexionSQLite(context);
    }
/*
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
    }*/

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }




    public void agregar() {
        try {
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("idproducto", idProducto);
            registro.put("idcliente", idCliente);
            registro.put("precio", precio);
            id = db.insert(conextion.table_proforma, null, registro);

        } catch (Exception ex) {
            ex.toString();

        }
    }

    public ArrayList<String> listar() {
        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaDProforma = new ArrayList<>();
        String pProforma = "";
        cursor = null;

        cursor = db.rawQuery("select * from proforma",null);
        if (cursor.moveToFirst()) {
            do {
                pProforma = String.valueOf(cursor.getInt(0)) + "\n";
                pProforma += String.valueOf(cursor.getInt(1))+ "\n";
                pProforma += String.valueOf(cursor.getInt(2));
                listaDProforma.add(pProforma);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaDProforma;
    }

    public void eliminar() {

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            //db.execSQL("DELETE FROM"  + conextion.table_detalle_catalogo + "' WHERE idcatalogo= '"+idCatalogo+"'");
            db.execSQL("DELETE FROM " + conextion.table_proforma + " WHERE  idproducto = '"+idProducto+"'");
        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }

    }

    public PdfDocument cargarPDF() {
        SQLiteDatabase db = conextion.getWritableDatabase();
        ArrayList<String> listaDProforma = new ArrayList<>();
        String pProforma = "";
        cursor = null;
        int y=25;
        int i=1;
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        cursor = db.rawQuery("select pf.id, p.nombre,pf.precio from proforma as pf , producto as p "+ "WHERE pf.idproducto = p.id  and pf.id = '"+ id +"'" , null);
        if (cursor.moveToFirst()) {
            do {
                pProforma = String.valueOf(cursor.getInt(0)) + "\t";
                pProforma += String.valueOf(cursor.getString(1))+ "\t";
                pProforma += String.valueOf(cursor.getInt(2));
                i++;
                //page.getCanvas().drawText(pCatalogo,10, 25, new Paint());
                page.getCanvas().drawText(pProforma,10, y*i, new Paint());
            } while (cursor.moveToNext());
        }
        cursor.close();
        pdfDocument.finishPage(page);
        return pdfDocument;
    }




    public PdfDocument cargarPDF(long id) {
        SQLiteDatabase db = conextion.getWritableDatabase();
        PdfDocument pdfDocument = new PdfDocument();
        Cursor cursor = null;
        try {
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
            Paint paint = new Paint();
            int y = 25;
            int i = 1;

            String query = "SELECT pf.id, p.nombre, pf.precio FROM proforma AS pf JOIN producto AS p ON pf.idproducto = p.id WHERE pf.id = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                do {
                    String pProforma = cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getInt(2);
                    page.getCanvas().drawText(pProforma, 10, y * i++, paint);
                } while (cursor.moveToNext());
            }

            pdfDocument.finishPage(page);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return pdfDocument;
    }

}
