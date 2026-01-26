package es.etg.daw.dawes.thym.productos.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.etg.daw.dawes.thym.productos.application.usecase.FindProductoUseCase;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindProductoService {
    
    private final FindProductoUseCase findProductoUseCase;

   public List<Producto> findAll() {
      return findProductoUseCase.findAll();
   }
}

