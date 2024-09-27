package com.example.emprende.emprende.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;  // Importamos para mostrar mensajes al usuario
import com.example.emprende.emprende.R;

public class MainActivity extends AppCompatActivity {

    // Declaracion de los botones que corresponden a las diferentes funcionalidades
    Button btCliente, btUbicacion, btRepartidor, btCatalogo, btProducto, btProforma, btEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asigna el layout de la actividad principal
        setContentView(R.layout.activity_main);

        // Inicializacion de los botones usando los IDs definidos en el archivo XML
        btCliente = findViewById(R.id.btCliente);
        btUbicacion = findViewById(R.id.btUbicacion);
        btRepartidor = findViewById(R.id.btRepartidor);
        btCatalogo = findViewById(R.id.btCatalogo);
        btProducto = findViewById(R.id.btProducto);
        btProforma = findViewById(R.id.btProforma);
        btEntrega = findViewById(R.id.btEntrega);

        // Asignar acciones a cada boton usando setOnClickListener
        btCliente.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de cliente
            vistaClient();
        });

        btUbicacion.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de ubicacion
            vistaUbi();
        });

        btRepartidor.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de repartidor
            vistaRepartidor();
        });

        btCatalogo.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de catalogo
            vistaCatalogo();
        });

        btProducto.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de producto
            vistaProducto();
        });

        btProforma.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de proforma
            vistaProforma();
        });

        btEntrega.setOnClickListener(view -> {
            // Llamar al metodo que abre la pantalla de entrega
            vistaEntrega();
        });
    }

    // Metodo que abre la pantalla de ubicacion, y captura posibles errores
    private void vistaUbi() {
        try {
            Intent ubi = new Intent(this, PUbicacionMapsActivity.class);
            // Inicia la actividad de ubicacion
            startActivity(ubi);
        } catch (Exception e) {
            // En caso de error, muestra un mensaje amigable al usuario
            Toast.makeText(this, "Error al abrir la vista de Ubicacion", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo que abre la pantalla de cliente
    public void vistaClient() {
        try {
            Intent pCliente = new Intent(this, PClienteActivity.class);
            // Inicia la actividad de cliente
            startActivity(pCliente);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir la vista de Cliente", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo que abre la pantalla de repartidor
    public void vistaRepartidor() {
        try {
            Intent pRepartidor = new Intent(this, PRepartidorActivity.class);
            // Inicia la actividad de repartidor
            startActivity(pRepartidor);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir la vista de Repartidor", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo que abre la pantalla de catalogo
    private void vistaCatalogo() {
        try {
            Intent pCatalogo = new Intent(this, PCatalogoActivity.class);
            // Inicia la actividad de catalogo
            startActivity(pCatalogo);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir la vista de Catalogo", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo que abre la pantalla de producto
    private void vistaProducto() {
        try {
            Intent pProducto = new Intent(this,PProductoActivity.class);
            // Inicia la actividad de producto
            startActivity(pProducto);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir la vista de Producto", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo que abre la pantalla de proforma
    private void vistaProforma() {
        try {
            Intent pProforma = new Intent(this, PProformaActivity.class);
            // Inicia la actividad de proforma
            startActivity(pProforma);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir la vista de Proforma", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo que abre la pantalla de entrega
    private void vistaEntrega() {
        try {
            Intent pEntrega = new Intent(this, PEntregaActivity.class);
            // Inicia la actividad de entrega
            startActivity(pEntrega);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir la vista de Entrega", Toast.LENGTH_SHORT).show();
        }
    }
}
