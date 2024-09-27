package com.example.emprende.emprende.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.db.ConexionSQLite;

import java.util.ArrayList;

public class DProducto {
    ConexionSQLite conextion;
    Cursor cursor;


    long id;
    String nombre;
    String descripcion;



    public DProducto(@Nullable Context context) {

        conextion= new ConexionSQLite(context);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long guardarProducto() {
        long id=0;
        try{
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("descripcion",descripcion);
            id = db.insert(conextion.table_producto,null,registro);

        }catch (Exception ex){
            ex.toString();

        }
        return id;

    }
    public ArrayList<String> mostrarProducto() {
        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaProducto = new ArrayList<>();
        String producto = "";
        cursor = null;

        cursor = db.rawQuery("select * from producto", null);
        if (cursor.moveToFirst()) {
            do {
                producto=  String.valueOf(cursor.getInt(0)) +"\n";
                producto+=  (cursor.getString(1));
                producto+= (cursor.getString(2));
                listaProducto.add(producto);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaProducto;
    }





    public boolean modficarProducto() {
        boolean correcto = false;

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+ conextion.table_producto + " SET nombre= '"+nombre+"', descripcion= '"+descripcion+"' WHERE id= '"+id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarProducto() {
        boolean correcto = false;

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM "+ conextion.table_producto + " WHERE id= '"+id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
