package com.bazar.api.service;

import com.bazar.api.dto.RecaudacionPorDiaDTO;
import com.bazar.api.dto.VerProductosPorVentaDTO;
import com.bazar.api.model.Cliente;
import com.bazar.api.model.Producto;
import com.bazar.api.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    
    public void crearVenta(Venta vent);
    
    public void borrarVenta(Long codigoVenta);
    
    public List<Venta> traerVentas();
    
    public Venta buscarVenta(Long codigoVenta);
    
    public void editarVenta(Long codigoVenta, LocalDate nuevaFechaVenta, double nuevoTotal, List<Producto> nuevaListaProductos, Cliente nuevoCliente);

    public VerProductosPorVentaDTO verListaProductos(Long codigoVenta);
    
    public RecaudacionPorDiaDTO RecaudacionPorDia(LocalDate fechaVenta);
    
}
