package com.example.emprende.emprende.Dato;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.emprende.emprende.db.ConexionSQLite;

import com.example.emprende.emprende.R;
import com.example.emprende.emprende.BuildConfig;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class DRepartidor {
    ConexionSQLite conextion;
    Cursor cursor;

    long id;
    String nombre;
    int telefono;

    public DRepartidor(@Nullable Context context) {

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public long guardarRepartidor() {
        long id=0;
        try{
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("telefono",telefono);
            id = db.insert(conextion.table_repartidor,null,registro);

        }catch (Exception ex){
            ex.toString();

        }
        return id;

    }
    public ArrayList<String> mostrarRepartidor() {
        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaRepartidor = new ArrayList<>();
        String repartidor = "";
        cursor = null;

        cursor = db.rawQuery("select * from repartidor", null);
        if (cursor.moveToFirst()) {
            do {
                repartidor=  String.valueOf(cursor.getInt(0)) +"\n";
                repartidor+=  (cursor.getString(1))+"\n";
                repartidor+= String.valueOf(cursor.getInt(2));
                listaRepartidor.add(repartidor);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaRepartidor;
    }


    public boolean modficarRepartidor() {
        boolean correcto = false;

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+ conextion.table_repartidor + " SET nombre= '"+nombre+"', telefono= '"+telefono+"' WHERE id= '"+id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarRepartidor() {
        boolean correcto = false;

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            //db.execSQL("DELETE FROM"  + conextion.table_repartidor + "' WHERE id= '"+id+"'");
            db.execSQL("DELETE FROM " + conextion.table_repartidor + " WHERE id = '" + id + "'");
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
