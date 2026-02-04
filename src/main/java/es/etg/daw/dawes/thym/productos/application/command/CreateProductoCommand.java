package es.etg.daw.dawes.thym.productos.application.command;

import es.etg.daw.dawes.thym.productos.domain.model.CategoriaId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class CreateProductoCommand {
    
    private final String nombre;
    private final double precio;
    private final CategoriaId categoriaId;
}