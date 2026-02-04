package es.etg.daw.dawes.thym.productos.infraestructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest{
    private String nombre;
    private double precio;
    private int categoriaId;
}