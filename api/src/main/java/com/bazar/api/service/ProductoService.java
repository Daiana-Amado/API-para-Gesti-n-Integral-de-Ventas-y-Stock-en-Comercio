package com.bazar.api.service;

import com.bazar.api.dto.VerProductosSinStockDTO;
import com.bazar.api.dto.VerStockProductoDTO;
import com.bazar.api.model.Producto;
import com.bazar.api.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    IProductoRepository producRepo;

    @Override
    public String crearProducto(Producto produ) {
        producRepo.save(produ);
        return "Producto guardado correctamente";
    }

    @Override
    public void editarProducto(Long codigoProducto, String nuevoNombre, String nuevaMarca, double nuevoCosto, int nuevaCantidadDisponible, List nuevaVentas) {
        
        Producto produ = this.producRepo.findById(codigoProducto).orElse(null);
        
        if(produ != null){
            produ.setNombre(nuevoNombre);
            produ.setMarca(nuevaMarca);
            produ.setCosto(nuevoCosto);
            produ.setCantidadDisponible(nuevaCantidadDisponible);
            produ.setVentas(nuevaVentas);
        
            this.producRepo.save(produ);
        }else{
            System.out.println("El producto no existe");
        }
       
    }

    @Override
    public String borrarProducto(Long codigoProducto) {
        producRepo.deleteById(codigoProducto);
        return "Producto borrado correctamente";
    }

    @Override
    public List<Producto> traerProducto() {
        List<Producto> listaProductos = producRepo.findAll();
        return listaProductos;
    }

    @Override
    public Producto buscarProducto(Long codigoProducto){
        Producto productoEncontrado = producRepo.findById(codigoProducto).orElse(null);
        return productoEncontrado;
    }

    //MÉTODOS DTO 
    @Override
    public VerStockProductoDTO verStock(Long codigoProducto) {
        Producto produ = this.producRepo.findById(codigoProducto).orElse(null);
        VerStockProductoDTO producDto = new VerStockProductoDTO();
    
        if(produ != null){           
            producDto.setCodigoProducto(produ.getCodigoProducto());
            producDto.setNombre(produ.getNombre());
            producDto.setCantidadDisponible(produ.getCantidadDisponible());
        }
    
        return producDto;
    }   

    @Override
    public VerProductosSinStockDTO verProductosSinStock(int cantidad) {
        
        List<Producto> listaProductos = producRepo.findAll();
        
        List<Producto> listaDto = new ArrayList<Producto>();
        
        if (listaProductos != null) {
            for (Producto lista : listaProductos) {
                if (lista.getCantidadDisponible() <= cantidad) {
                    listaDto.add(lista);
                }
            }
        }
        
        VerProductosSinStockDTO objetoDto = new VerProductosSinStockDTO(listaDto);
        
        return objetoDto;
    }

}
        
