package com.demoblaze.models;

public class DatosCompra {
    private final String nombre;
    private final String pais;
    private final String ciudad;
    private final String tarjeta;
    private final String mes;
    private final String anio;

    public DatosCompra(String nombre, String pais, String ciudad, 
                       String tarjeta, String mes, String anio) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.tarjeta = tarjeta;
        this.mes = mes;
        this.anio = anio;
    }

    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public String getTarjeta() { return tarjeta; }
    public String getMes() { return mes; }
    public String getAnio() { return anio; }
}