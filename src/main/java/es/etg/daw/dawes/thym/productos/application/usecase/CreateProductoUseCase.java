package es.etg.daw.dawes.thym.productos.application.usecase;


import org.springframework.stereotype.Service;

import es.etg.daw.dawes.thym.productos.application.command.CreateProductoCommand;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.domain.repository.ProductoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateProductoUseCase {
    
    private final ProductoRepository productoRepository;

    public Producto create( CreateProductoCommand comando){

        Producto producto = Producto.builder() // Se puede usar comando.id y no getId por usar @Accessors(fluent = true) la clase CreateProductoCommand 
                                    .nombre(comando.nombre())
                                    .precio(comando.precio())
                                    .categoriaId(comando.categoriaId())
                                    .build();
        return productoRepository.save(producto);

    }
}
