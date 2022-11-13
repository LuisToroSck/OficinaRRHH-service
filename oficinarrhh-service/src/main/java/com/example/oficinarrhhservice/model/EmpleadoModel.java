package com.example.oficinarrhhservice.model;

import java.sql.Date;

public class EmpleadoModel {
    
    private String rutEmpleado;
    private String apellidos;
    private String nombres;
    private Date fechaIngreso;
    private String categoria;
    private Date fechaNacimiento;

    public String getCategoria(){
        return categoria;
    }

    public String getRutEmpleado(){
        return rutEmpleado;
    }

    public Date getFechaIngreso(){
        return fechaIngreso;
    }
}
