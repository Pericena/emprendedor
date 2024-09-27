package com.example.emprende.emprende.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.emprende.emprende.Dato.DCliente;


public class ConexionSQLite extends SQLiteOpenHelper {
    ConexionSQLite conexion;
    private static final int data_base_v =1;
    private static final String database_Nombre ="emprende.db";
    public static final String table_cliente ="cliente";
    public static final String table_producto ="producto";
    public static final String table_repartidor ="repartidor";
    public static final String table_catalogo ="catalogo";
    public static final String table_detalle_catalogo ="detallecatalogo";
    public static final String table_proforma ="proforma";
    public static final String table_Entrega ="entrega";

    // Constructor de la clase que recibe el contexto
    public ConexionSQLite(@Nullable Context context) {
        super(context, database_Nombre, null, data_base_v);
    }

    // Método para crear las tablas en la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Habilitar claves foráneas
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");

        // Creación de la tabla Cliente
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_cliente + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "telefono TEXT NOT NULL)");

        // Creación de la tabla Repartidor
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_repartidor + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "telefono TEXT NOT NULL)");

        // Creación de la tabla Producto
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_producto + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "imagen BLOB, " +
                "descripcion TEXT)");

        // Creación de la tabla Catálogo
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_catalogo + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL)");

        // Creación de la tabla Detalle Catálogo
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_detalle_catalogo + " (" +
                "idcatalogo INTEGER NOT NULL, " +
                "idproducto INTEGER NOT NULL, " +
                "FOREIGN KEY (idcatalogo) REFERENCES " + table_catalogo + "(id), " +
                "FOREIGN KEY (idproducto) REFERENCES " + table_producto + "(id))");

        // Creación de la tabla Proforma
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_proforma + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idproducto INTEGER NOT NULL, " +
                "idcliente INTEGER NOT NULL, " +
                "precio REAL NOT NULL, " +
                "FOREIGN KEY (idproducto) REFERENCES " + table_producto + "(id), " +
                "FOREIGN KEY (idcliente) REFERENCES " + table_cliente + "(id))");

        // Creación de la tabla Entrega
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_Entrega + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idrep INTEGER NOT NULL, " +
                "idcliente INTEGER NOT NULL, " +
                "latit TEXT, " +
                "longit TEXT, " +
                "FOREIGN KEY (idrep) REFERENCES " + table_repartidor + "(id), " +
                "FOREIGN KEY (idcliente) REFERENCES " + table_cliente + "(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_repartidor);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_cliente);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_producto);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_catalogo);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_detalle_catalogo);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_proforma);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_Entrega);
        onCreate(sqLiteDatabase);
    }
}
