package com.example.oficinarrhhservice.model;

import java.sql.Date;

public class JustificativoModel {
    private String rutEmpleado;
    private int justificada;
    private Date fecha;

    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public int getJustificada() {
        return justificada;
    }
}
