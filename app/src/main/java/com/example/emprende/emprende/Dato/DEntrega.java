package com.example.emprende.emprende.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.emprende.emprende.db.ConexionSQLite;

import java.util.ArrayList;

public class DEntrega {
    String lat,lon;
    Long id,idrep,idcliente;

    ConexionSQLite conextion;
    Cursor cursor;
    public DEntrega(@Nullable Context context) {

        conextion= new ConexionSQLite(context);
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdrep(Long idrep) {
        this.idrep = idrep;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public void agregar() {
        try{
            SQLiteDatabase db = conextion.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("idrep",idrep);
            registro.put("idcliente",idcliente);
            registro.put("latit",lat);
            registro.put("longit",lon);
            id = db.insert(conextion.table_Entrega,null,registro);
        }catch (Exception ex){
            ex.toString();
        }
    }

    public ArrayList<String> mostrar() {
        SQLiteDatabase db = conextion.getWritableDatabase();

        ArrayList<String> listaEntrega = new ArrayList<>();
        String entrega = "Nro\t Repartidor \t latitud \t longitud";
        cursor = null;
        listaEntrega.add(entrega);

//        cursor = db.rawQuery("SELECT * FROM entrega ", null);
        cursor = db.rawQuery("SELECT e.id, r.nombre,c.nombre, e.latit, e.longit FROM entrega as e, repartidor as r, cliente as c WHERE r.id=e.idrep and e.idcliente=c.id", null);
        if (cursor.moveToFirst()) {
            do {
                entrega=  String.valueOf(cursor.getInt(0)) +"\t\t\t";
                entrega+= cursor.getString(1)+"\t\t\t";
                entrega+= cursor.getString(2)+"\t\t\t\t\t\t\t\t";
                entrega+= cursor.getString(3)+"\t\t";
                entrega+= cursor.getString(4);
                listaEntrega.add(entrega);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaEntrega;
    }
}
