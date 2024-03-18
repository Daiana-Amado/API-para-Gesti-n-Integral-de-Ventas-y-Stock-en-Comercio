package com.bazar.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long codigoProducto;
    private String nombre;
    private String marca;
    private double costo;
    private int cantidadDisponible;  
    @JsonIgnoreProperties("productos")
    @ManyToMany(mappedBy = "listaProductos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Venta> ventas;
    
    public Producto() {
    }

    public Producto(Long codigoProducto, String nombre, String marca, double costo, int cantidadDisponible, List<Venta> ventas) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
        this.ventas = ventas;
    }
  
}
