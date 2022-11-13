package com.example.oficinarrhhservice.model;

import java.sql.Date;

public class AutorizacionModel {
    private String rutEmpleado;
    private Date fecha;
    private int cantidadHorasExtras;
    private int autorizado;

    public String getRutEmpleado(){
        return rutEmpleado;
    }

    public int getAutorizado(){
        return autorizado;
    }

    public int getCantidadHorasExtras(){
        return cantidadHorasExtras;
    }
}
