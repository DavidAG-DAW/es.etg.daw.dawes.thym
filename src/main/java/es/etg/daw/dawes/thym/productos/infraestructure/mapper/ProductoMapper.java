package es.etg.daw.dawes.thym.productos.infraestructure.mapper;

import java.util.ArrayList;
import java.util.List;

import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.domain.model.ProductoId;
import es.etg.daw.dawes.thym.productos.infraestructure.api.dto.ProductoResponse;

public class ProductoMapper {
    
    public static List<Producto> toDomain(List<ProductoResponse> lista){
        List<Producto> lp = new ArrayList<>();
        for(ProductoResponse pe: lista){
            lp.add(toDomain(pe));
        }
        return lp;
    }

    public static Producto toDomain(ProductoResponse p){
        return new Producto(new ProductoId(p.getId()), p.getNombre(), p.getPrecio());
    }
}
