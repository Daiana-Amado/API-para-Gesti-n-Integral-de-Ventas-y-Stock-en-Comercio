package com.bazar.api.controller;

import com.bazar.api.dto.VerProductosSinStockDTO;
import com.bazar.api.dto.VerStockProductoDTO;
import com.bazar.api.model.Producto;
import com.bazar.api.service.ProductoService;
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
public class ProductoController {
    
    @Autowired
    ProductoService producServ;
    
    @PostMapping("/producto/crear")
    public Producto crearProducto(@RequestBody Producto produ){
        producServ.crearProducto(produ);
        return produ;
    }
    
    @GetMapping("/productos")
    public List<Producto> traerProductos(){
        return producServ.traerProducto();
    }
    
    @GetMapping("/producto/{codigoProducto}")
    public Producto buscarProducto(@PathVariable Long codigoProducto){
        return producServ.buscarProducto(codigoProducto);
    }
    
    @DeleteMapping("/producto/eliminar/{codigoProducto}")
    public String eliminarProducto(@PathVariable Long codigoProducto){
        return producServ.borrarProducto(codigoProducto);
    }
    
    @PutMapping("/producto/editar/{codigoProducto}")
    public ResponseEntity<String> editarProducto(@PathVariable Long codigoProducto, 
                                   @RequestParam String nuevoNombre,
                                   @RequestParam String nuevaMarca,
                                   @RequestParam double nuevoCosto,
                                   @RequestParam int nuevaCantidadDisponible,
                                   @RequestParam List nuevaVentas){
        return ResponseEntity.ok("Producto editado correctamente");  
    }
    
    //MÉTODOS DTOs
    
    @GetMapping("/producto/ver/stock/{codigoProducto}")
    public VerStockProductoDTO verStock(@PathVariable Long codigoProducto){
        return producServ.verStock(codigoProducto);
    }
    
    @GetMapping("/productos/stock/menor/a/{cantidad}")
    public VerProductosSinStockDTO verStockMenorA(@PathVariable int cantidad){
        return producServ.verProductosSinStock(cantidad);
    }
}
