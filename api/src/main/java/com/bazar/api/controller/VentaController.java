package com.bazar.api.controller;

import com.bazar.api.dto.RecaudacionPorDiaDTO;
import com.bazar.api.dto.VerProductosPorVentaDTO;
import com.bazar.api.model.Cliente;
import com.bazar.api.model.Producto;
import com.bazar.api.model.Venta;
import com.bazar.api.service.VentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
     @Autowired
    VentaService ventServ;
        
    @PostMapping("/venta/crear")
    public void crearVenta(@RequestBody Venta vent){
        ventServ.crearVenta(vent);
    }
    
    @DeleteMapping("/venta/borrar/{codigoVenta}")
    public void borrarVenta(@PathVariable Long codigoVenta){
        ventServ.borrarVenta(codigoVenta);
    }
    
    @GetMapping("/ventas")
    public List<Venta> traerVentas(){
        return ventServ.traerVentas();
    }
    
    @GetMapping("/venta/{codigoVenta}")
    public Venta buscarVenta(@PathVariable Long codigoVenta){
        return ventServ.buscarVenta(codigoVenta);
    }

    @PutMapping("/venta/editar/{codigoVenta}")
    public ResponseEntity<String> editarVenta(@PathVariable Long codigoVenta,
                             @RequestParam LocalDate nuevaFechaVenta,
                             @RequestParam double nuevoTotal,
                             @RequestParam List<Producto> nuevoListaProductos,
                             @RequestParam Cliente nuevoCliente){
        
        return ResponseEntity.ok("Venta editada correctamente"); 
    }
    
    //MÉTODOS DTO
    
    @GetMapping("/venta/lista/productos/{codigoVenta}")
    public VerProductosPorVentaDTO verListaProductos(@PathVariable Long codigoVenta){
        return ventServ.verListaProductos(codigoVenta);
    }
    
    @GetMapping("/venta/recaudacion/{fechaVenta}")
    public RecaudacionPorDiaDTO verListaProductos(@PathVariable LocalDate fechaVenta){
        return ventServ.RecaudacionPorDia(fechaVenta);
    }
    
}
