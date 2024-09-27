package com.example.emprende.emprende.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.db.ConexionSQLite;

import java.util.ArrayList;

public class DCatalogo {
    ConexionSQLite conextion;
    Cursor cursor;


    long id;
    String nombre;


    public DCatalogo(@Nullable Context context) {

        conextion = new ConexionSQLite(context);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public long guardarCatalogo() {
        long id = 0;
        try {
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            id = db.insert(conextion.table_catalogo, null, registro);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<String> mostrarCatalogo() {
        SQLiteDatabase db = conextion.getWritableDatabase();
        ArrayList<String> listaRepartidor = new ArrayList<>();
        String repartidor = "";
        cursor = null;
        cursor = db.rawQuery("select * from catalogo", null);
        if (cursor.moveToFirst()) {
            do {
                repartidor = String.valueOf(cursor.getInt(0)) + "\n";
                repartidor += (cursor.getString(1)) + "\n";
                repartidor += String.valueOf(cursor.getInt(2));
                listaRepartidor.add(repartidor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaRepartidor;
    }


    public boolean modficarCatalogo() {
        boolean correcto = false;
        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + conextion.table_catalogo + " SET nombre= '" + nombre +  "' WHERE id= '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarCatalogo() {
        boolean correcto = false;
        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            //db.execSQL("DELETE FROM"  + conextion.table_repartidor + "' WHERE id= '"+id+"'");
            db.execSQL("DELETE FROM " + conextion.table_detalle_catalogo + " WHERE idcatalogo = '" + id + "'");
            db.execSQL("DELETE FROM " + conextion.table_catalogo + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
