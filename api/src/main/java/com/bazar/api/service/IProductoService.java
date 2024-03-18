package com.bazar.api.service;

import com.bazar.api.dto.VerProductosSinStockDTO;
import com.bazar.api.dto.VerStockProductoDTO;
import com.bazar.api.model.Producto;
import java.util.List;

public interface IProductoService {
 
    public String crearProducto(Producto produ);
    
    public void editarProducto(Long codigoProducto, String nuevoNombre, String nuevaMarca, double nuevoCosto, int nuevaCantidadDisponible, List nuevaVentas);
    
    public String borrarProducto(Long codigoProducto);
    
    public List<Producto> traerProducto();
    
    public Producto buscarProducto(Long codigoProducto);
    
    public VerStockProductoDTO verStock(Long codigoProducto);
    
    public VerProductosSinStockDTO verProductosSinStock(int cantidad);
    
}
