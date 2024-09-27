package com.example.emprende.emprende.Presentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.emprende.emprende.Negocio.NCliente;
import com.example.emprende.emprende.Negocio.NEntrega;
import com.example.emprende.emprende.Negocio.NRepartidor;
import com.example.emprende.emprende.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class PUbicacionMapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {


    NRepartidor nr;
    NCliente nc;
    NEntrega ne;

    GoogleMap mMap;
    EditText txtLongitud,txtLatitud;
    Spinner spinner1,spinner2;

    Button enviarUb;
    ArrayList<String> dato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_maps);

        nr= new NRepartidor(PUbicacionMapsActivity.this);
        nc= new NCliente(PUbicacionMapsActivity.this);
        ne= new NEntrega(PUbicacionMapsActivity.this);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud= findViewById(R.id.txtLongitud);
        enviarUb= findViewById(R.id.btEnviarUb);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        cargarspinner();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        enviarUb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();
            }
        });
    }

    private void cargarspinner() {
        dato = nr.listarRepartidor();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        spinner1.setAdapter(adapter);
        dato = nc.listarCliente();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dato);
        spinner2.setAdapter(adapter);
    }

    private void enviar() {
        String cel = this.spinner1.getSelectedItem().toString().split("\n")[2]; // Número del repartidor
        Long rep = Long.valueOf(this.spinner1.getSelectedItem().toString().split("\n")[0]);
        Long cli = Long.valueOf(this.spinner2.getSelectedItem().toString().split("\n")[0]);

        String empresaNombre = "MegaPet"; // Nombre de la empresa
        String ubicacionUrl = "http://maps.google.com/maps?saddr=" + txtLatitud.getText().toString() + "," + txtLongitud.getText().toString();
        String mensaje = "Hola, " + empresaNombre + " te asignó un nuevo pedido. Por favor, entrega el pedido al cliente usando la siguiente ubicación:\n\n" + ubicacionUrl + "\n\nGracias por tu trabajo. ¡Estamos confiando en ti para una entrega exitosa! 🚚😊";

        if (cel.isEmpty()) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_VIEW);
            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
            String uri = "whatsapp://send?phone=" + cel + "&text=" + Uri.encode(mensaje);
            sendIntent.setData(Uri.parse(uri));
            startActivity(sendIntent);
        }
        ne.insertar(rep, cli, txtLatitud.getText().toString(), txtLongitud.getText().toString());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        LatLng bolivia =  new LatLng(-17.7455448,-63.1173779);
        mMap.addMarker(new MarkerOptions().position(bolivia).title("Bolivia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bolivia));
    }

    private void ubic(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng puntoMarcado = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(puntoMarcado)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(puntoMarcado));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);
    }
}