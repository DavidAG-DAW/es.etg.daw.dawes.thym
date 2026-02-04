package es.etg.daw.dawes.thym.productos.infraestructure.mapper;

import java.util.ArrayList;
import java.util.List;

import es.etg.daw.dawes.thym.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.domain.model.ProductoId;
import es.etg.daw.dawes.thym.productos.infraestructure.api.dto.ProductoRequest;
import es.etg.daw.dawes.thym.productos.infraestructure.api.dto.ProductoResponse;

public class ProductoMapper {
    
    public static List<Producto> toDomain(List<ProductoResponse> lista){
        List<Producto> lp = new ArrayList<>();
        for(ProductoResponse pe: lista){
            lp.add(toDomain(pe));
        }
        return lp;
    }

    public static ProductoRequest toRequest(Producto producto){
        return new ProductoRequest(producto.getNombre(), producto.getPrecio(), producto.getCategoriaId().getValue());
    
    }

    public static Producto toDomain(ProductoResponse p){
        return new Producto(new ProductoId(p.getId()), p.getNombre(), p.getPrecio(), new CategoriaId(p.getCategoria()));
    }
}
