package es.etg.daw.dawes.thym.productos.domain.repository;

import es.etg.daw.dawes.thym.common.domain.model.repository.CRUDRepository;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.domain.model.ProductoId;

public interface  ProductoRepository extends CRUDRepository<Producto, ProductoId>{
    
}
