
package com.bazar.api.dto;

import com.bazar.api.model.Producto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerProductosSinStockDTO {
    public List<Producto> listaProductosSinStock;

    public VerProductosSinStockDTO() {
    }

    public VerProductosSinStockDTO(List<Producto> listaProductosSinStock) {
        this.listaProductosSinStock = listaProductosSinStock;
    }
   
}
