package es.etg.daw.dawes.thym.productos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    private Integer id;
    private String nombre;
    private Double precio;
}

