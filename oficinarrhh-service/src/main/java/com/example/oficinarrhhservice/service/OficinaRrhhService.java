package com.example.oficinarrhhservice.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.oficinarrhhservice.model.*;

@Service
public class OficinaRrhhService{

    RestTemplate restTemplate = new RestTemplate();

    public double calcularSueldoFijoMensual(String rutEmpleado){


        EmpleadoModel[] empleados = getEmpleados();
        int i = 0;
        String cat = new String();
        while (i < empleados.length){
            if(empleados[i].getRutEmpleado().equals(rutEmpleado)){
                cat = empleados[i].getCategoria(); 
            }
            i++;
        }

        double sueldoFijoMensual = 0;

        if(cat.equals("A")){
            sueldoFijoMensual = 1700000;
        }
        else if(cat.equals("B")){
            sueldoFijoMensual = 1200000;
        }
        else{
            sueldoFijoMensual = 800000;
        }

        return sueldoFijoMensual;
    }

    public double calcularBonificacionPorAniosServicio(String rutEmpleado){

        double sueldoFijoMensual = calcularSueldoFijoMensual(rutEmpleado);

        LocalDate localDate = LocalDate.now();
        EmpleadoModel[] empleados = getEmpleados();
        int i = 0;
        while(i < empleados.length){
            if(empleados[i].getRutEmpleado().equals(rutEmpleado)){
                localDate = empleados[i].getFechaIngreso().toLocalDate();
            }
            i = i + 1;
        }

        double bonificacionPorServicio = 0;

        int anioIngreso = localDate.getYear();
        int anioActual  = LocalDateTime.now().getYear();
        int aniosServicio = anioActual - anioIngreso;

        if(aniosServicio >= 25){bonificacionPorServicio = sueldoFijoMensual * 0.17;}
        else if(aniosServicio >= 20){bonificacionPorServicio = sueldoFijoMensual * 0.14;}
        else if(aniosServicio >= 15){bonificacionPorServicio = sueldoFijoMensual * 0.11;}
        else if(aniosServicio >= 10){bonificacionPorServicio = sueldoFijoMensual * 0.08;}
        else if(aniosServicio >= 5){bonificacionPorServicio = sueldoFijoMensual * 0.05;}
        else{bonificacionPorServicio = 0;}

        DecimalFormat formato = new DecimalFormat("#");
        String bonifStr = formato.format(bonificacionPorServicio);
        return Double.parseDouble(bonifStr);
    }

    public double calcularPagoHorasExtras(String rutEmpleado){

        AutorizacionModel[] autorizaciones = getAutorizaciones();

        EmpleadoModel[] empleados = getEmpleados();
        EmpleadoModel empleado = new EmpleadoModel();
        int i = 0;
        while(i<empleados.length){
            if(empleados[i].getRutEmpleado().equals(rutEmpleado)){
                empleado = empleados[i];
            }
            i = i + 1;
        }

        double pagoHorasExtras = 0;

        i=0;
        while(i<autorizaciones.length){
            if(autorizaciones[i].getRutEmpleado().equals(empleado.getRutEmpleado())) {
                if (autorizaciones[i].getAutorizado() == 1) {
                    if (empleado.getCategoria().equals("A")) {
                        pagoHorasExtras = autorizaciones[i].getCantidadHorasExtras() * 25000;
                    } else if (empleado.getCategoria().equals("B")) {
                        pagoHorasExtras = autorizaciones[i].getCantidadHorasExtras() * 20000;
                    } else {
                        pagoHorasExtras = autorizaciones[i].getCantidadHorasExtras() * 10000;
                    }
                }
            }
            i=i+1;
        }


        return pagoHorasExtras;
    }

    public double calcularDescuentoPorAtraso(String rutEmpleado){

        double sueldoFijoMensual = calcularSueldoFijoMensual(rutEmpleado);
        List<Integer> atrasos = getAtrasos(rutEmpleado);

        double descuentoPorAtraso = 0;

        descuentoPorAtraso = atrasos.get(0)*sueldoFijoMensual*0.01 + atrasos.get(1)*sueldoFijoMensual*0.03 + atrasos.get(2)*sueldoFijoMensual*0.06;

        return descuentoPorAtraso;
    }

    public double calcularDescuentoPorInasistencia(String rutEmpleado){
        
        double sueldoFijoMensual = calcularSueldoFijoMensual(rutEmpleado);
        JustificativoModel[] justificativos = getJustificativos();
        
        EmpleadoModel[] empleados = getEmpleados();
        EmpleadoModel empleado = new EmpleadoModel();
        int i = 0;
        while(i<empleados.length){
            if(empleados[i].getRutEmpleado().equals(rutEmpleado)){
                empleado = empleados[i];
            }
            i = i + 1;
        }


        double descuentoPorInasistencia = 0;

        int cont=0;
        i=0;
        while(i< justificativos.length){
            if(justificativos[i].getRutEmpleado().equals(empleado.getRutEmpleado())){
                if(justificativos[i].getJustificada()==0){
                    cont = cont + 1 ;
                }
            }
            i = i + 1;
        }
        descuentoPorInasistencia = cont*sueldoFijoMensual*0.15;

        return descuentoPorInasistencia;
    }


    public EmpleadoModel[] getEmpleados(){
        EmpleadoModel[] empleados = restTemplate.getForObject("http://localhost:8002/empleado", EmpleadoModel[].class);
        return empleados;
    }

    public AutorizacionModel[] getAutorizaciones(){
        AutorizacionModel[] autorizaciones = restTemplate.getForObject("http://localhost:8001/autorizacion", AutorizacionModel[].class);
        return autorizaciones;
    }

    public List<Integer> getAtrasos(String rutEmpleado){
        List<Integer> atrasos = restTemplate.getForObject("http://localhost:8003/oficina/getAtrasos/" + rutEmpleado, List.class);
        return atrasos;
    }

    public JustificativoModel[] getJustificativos(){
        JustificativoModel[] justificativos = restTemplate.getForObject("http://localhost:8004/justificativo", JustificativoModel[].class);
        return justificativos;
    }

}
