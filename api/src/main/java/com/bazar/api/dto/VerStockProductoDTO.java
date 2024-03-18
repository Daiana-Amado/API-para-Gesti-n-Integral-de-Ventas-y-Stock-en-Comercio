package com.bazar.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerStockProductoDTO {
    private Long codigoProducto;
    private String nombre;
    private int cantidadDisponible;

    public VerStockProductoDTO() {
    }

    public VerStockProductoDTO(Long codigoProducto, String nombre, int cantidadDisponible) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
    }
    
}
