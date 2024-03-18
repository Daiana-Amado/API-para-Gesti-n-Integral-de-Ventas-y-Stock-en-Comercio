package com.bazar.api.dto;

import com.bazar.api.model.Producto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerProductosPorVentaDTO {
    public Long codigoVenta;
    public double total;
    public List<Producto> listaProductosPorVenta;

    public VerProductosPorVentaDTO() {
    }

    public VerProductosPorVentaDTO(Long codigoVenta, double total, List<Producto> listaProductosPorVenta) {
        this.codigoVenta = codigoVenta;
        this.total = total;
        this.listaProductosPorVenta = listaProductosPorVenta;
    }
  
}
