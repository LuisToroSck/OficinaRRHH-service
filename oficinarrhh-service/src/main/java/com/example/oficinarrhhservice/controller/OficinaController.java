package com.example.oficinarrhhservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.oficinarrhhservice.service.OficinaRrhhService;

@Controller
@RequestMapping("/oficina")
public class OficinaController {

    @Autowired
    OficinaRrhhService oficinaRrhhService;

    @GetMapping("/getSueldoFijoMensual/{rutEmpleado}")
    public double getSueldoFijoMensual(@PathVariable String rutEmpleado){
        return oficinaRrhhService.calcularSueldoFijoMensual(rutEmpleado);
    }

    @GetMapping("/getBonificacionPorAniosServicio/{rutEmpleado}")
    public double getBonificacionPorAniosServicio(@PathVariable String rutEmpleado){
        return oficinaRrhhService.calcularBonificacionPorAniosServicio(rutEmpleado);
    }

    @GetMapping("/getPagoHorasExtras/{rutEmpleado}")
    public double getPagoHorasExtras(@PathVariable String rutEmpleado){
        return oficinaRrhhService.calcularPagoHorasExtras(rutEmpleado);
    }
    
}
