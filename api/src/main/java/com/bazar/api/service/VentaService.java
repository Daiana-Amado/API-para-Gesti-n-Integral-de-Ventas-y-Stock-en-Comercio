package com.bazar.api.service;

import com.bazar.api.dto.RecaudacionPorDiaDTO;
import com.bazar.api.dto.VerProductosPorVentaDTO;
import com.bazar.api.model.Cliente;
import com.bazar.api.model.Producto;
import com.bazar.api.model.Venta;
import com.bazar.api.repository.IProductoRepository;
import com.bazar.api.repository.IVentaRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    IVentaRepository ventRepo;
    
    @Autowired
    IProductoRepository producRepo;

    @Override
    @Transactional
    public void crearVenta(Venta vent) {
        
        //Se verifica que haya stock disponible
        List<Producto> listaVenta = vent.getListaProductos();
        List<Producto> productosDisponibles = new ArrayList<Producto>();
        double sumaTotal = 0.0;
        
        for(Producto produ : listaVenta){
            if(produ.getCantidadDisponible()>0){
                productosDisponibles.add(produ);
                //Se van guardando los costos de venta
                sumaTotal += produ.getCosto();
            }else{
                System.out.println("Sin stock del producto: " + produ.getNombre());
            }
        }
        
        vent.setTotal(sumaTotal);
        vent.setListaProductos(productosDisponibles);
        
        //Se guarda la venta
        ventRepo.save(vent);
        
        
        //Actualizar Stock
        for (Producto producto : productosDisponibles) {
            Producto productoEnBD = producRepo.findById(producto.getCodigoProducto()).orElse(null);
            if (productoEnBD != null) {
                productoEnBD.setCantidadDisponible(productoEnBD.getCantidadDisponible() - 1);
                producRepo.save(productoEnBD);
            }
        }
        
    }

    @Override
    public void borrarVenta(Long codigoVenta) {
        ventRepo.deleteById(codigoVenta);
    }

    @Override
    public List<Venta> traerVentas() {
        List<Venta> listaVentas = ventRepo.findAll();
        return listaVentas;
    }

    @Override
    public Venta buscarVenta(Long codigoVenta) {
        Venta ventaEncontrada = ventRepo.findById(codigoVenta).orElse(null);
        return ventaEncontrada;
    }

    @Override
    public void editarVenta(Long codigoVenta, LocalDate nuevaFechaVenta, double nuevoTotal, List<Producto> nuevaListaProductos, Cliente nuevoCliente) {
        
        Venta vent = this.ventRepo.findById(codigoVenta).orElse(null);
        
        if(vent != null){
            vent.setFechaVenta(nuevaFechaVenta);
            vent.setTotal(nuevoTotal);
            vent.getListaProductos().clear();
            vent.getListaProductos().addAll(nuevaListaProductos);
            vent.setUnCliente(nuevoCliente);
        
            this.ventRepo.save(vent);
            
        }else{
            System.out.println("Venta inexistente");
        }   
    } 

    //MÉTODOS DTO
    
    @Override
    public VerProductosPorVentaDTO verListaProductos(Long codigoVenta) {
        Venta venta = this.buscarVenta(codigoVenta);
        VerProductosPorVentaDTO dto = new VerProductosPorVentaDTO();
        
        if (venta != null) {
            dto.setCodigoVenta(venta.getCodigoVenta());
            dto.setTotal(venta.getTotal());
            dto.setListaProductosPorVenta(venta.getListaProductos());
        }
        
        return dto;
    }

    @Override
    public RecaudacionPorDiaDTO RecaudacionPorDia(LocalDate fechaVenta) {
        
        List<Venta> ventasDia = ventRepo.findAll();
        double total = 0.0;
        
        for(Venta lista : ventasDia){
            if(lista.getFechaVenta().isEqual(fechaVenta)){
                total += lista.getTotal();
            }
        }
        
        RecaudacionPorDiaDTO recaudacionDto = new RecaudacionPorDiaDTO();
        
        recaudacionDto.setFechaVenta(fechaVenta);
        recaudacionDto.setRecaudacion(total);
        
        return recaudacionDto;    
    }

}
