package com.example.emprende.emprende.Dato;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.db.ConexionSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.emprende.emprende.db.ConexionSQLite;



public class DCliente{
    ConexionSQLite conextion;
    Cursor cursor;
    long id;
    String nombre;
    int telefono;

    public DCliente(@Nullable Context context) {
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

    public long guardarCliente() {
        long id=0;
        try{
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("telefono",telefono);
            id = db.insert(conextion.table_cliente,null,registro);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<String> mostrarClientess() {
        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaCliente = new ArrayList<>();
        String cliente = "";
        cursor = null;

        cursor = db.rawQuery("select * from cliente", null);
        if (cursor.moveToFirst()) {
            do {
                cliente=  String.valueOf(cursor.getInt(0)) +"\n";
                cliente+=  (cursor.getString(1));
                cliente+= String.valueOf(cursor.getInt(2));
                listaCliente.add(cliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaCliente;
    }

    public boolean modficarCliente() {
        boolean correcto = false;

        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+ conextion.table_cliente + " SET nombre= '"+nombre+"', telefono= '"+telefono+"' WHERE id= '"+id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarCliente() {
        boolean correcto = false;
        SQLiteDatabase db = conextion.getWritableDatabase();
        try {
            int filasAfectadas = db.delete(conextion.table_cliente, "id = ?", new String[]{String.valueOf(id)});

            if (filasAfectadas > 0) {
                correcto = true;
            } else {
                System.out.println("No se encontró ningún cliente con el id proporcionado.");
            }
        } catch (Exception ex) {
            System.out.println("Error al eliminar el cliente: " + ex.getMessage());
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
