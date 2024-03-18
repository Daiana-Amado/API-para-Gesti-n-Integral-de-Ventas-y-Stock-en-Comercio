package com.bazar.api.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecaudacionPorDiaDTO {
    private LocalDate fechaVenta;
    private double recaudacion;

    public RecaudacionPorDiaDTO() {
    }

    public RecaudacionPorDiaDTO(LocalDate fecha, double recaudacion) {
        this.fechaVenta = fecha;
        this.recaudacion = recaudacion;
    }
       
}
