package com.example.oficinarrhhservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getSueldoFijoMensual/{id}")
    public ResponseEntity<Double> getSueldoFijoMensual(@PathVariable Long id){
        return ResponseEntity.ok(oficinaRrhhService.calcularSueldoFijoMensual(id));
    }

    @GetMapping("/getBonificacionPorAniosServicio/{id}")
    public ResponseEntity<Double> getBonificacionPorAniosServicio(@PathVariable Long id){
        return ResponseEntity.ok(oficinaRrhhService.calcularBonificacionPorAniosServicio(id));
    }

    @GetMapping("/getPagoHorasExtras/{id}")
    public ResponseEntity<Double> getPagoHorasExtras(@PathVariable Long id){
        return ResponseEntity.ok(oficinaRrhhService.calcularPagoHorasExtras(id));
    }

    @GetMapping("/getDescuentoPorAtrasos/{id}")
    public ResponseEntity<Double> getDescuentoPorAtrasos(@PathVariable Long id){
        return ResponseEntity.ok(oficinaRrhhService.calcularDescuentoPorAtraso(id));
    }

    @GetMapping("/getDescuentoPorInasistencia/{id}")
    public ResponseEntity<Double> getDescuentoPorInasisitencia(@PathVariable Long id){
        return ResponseEntity.ok(oficinaRrhhService.calcularDescuentoPorInasistencia(id));
    }
    
}
