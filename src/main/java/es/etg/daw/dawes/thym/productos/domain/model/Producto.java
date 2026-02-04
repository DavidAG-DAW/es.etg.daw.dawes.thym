package es.etg.daw.dawes.thym.productos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    private ProductoId id;
    private String nombre;
    private Double precio;
    private CategoriaId categoriaId;
}

