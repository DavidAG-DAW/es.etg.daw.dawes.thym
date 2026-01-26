package es.etg.daw.dawes.thym.productos.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Producto {
    private ProductoId id;
    private String nombre;
    private Double precio;
    private LocalDateTime createdAt;
    private CategoriaId categoria;



}

