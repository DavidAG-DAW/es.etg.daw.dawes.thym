package es.etg.daw.dawes.thym.productos.infraestructure.api;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.domain.model.ProductoId;
import es.etg.daw.dawes.thym.productos.domain.repository.ProductoRepository;
import es.etg.daw.dawes.thym.productos.infraestructure.api.dto.ProductoResponse;
import es.etg.daw.dawes.thym.productos.infraestructure.mapper.ProductoMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RestClientProductoAdapter implements ProductoRepository {

    private final RestClient restClient;

    @Override
    public List<Producto> getAll() {
        return ProductoMapper.toDomain(restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductoResponse>>() {
                }));
    }

    @Override
    public Producto save(Producto t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Producto> getById(ProductoId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void deleteById(ProductoId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deteteById'");
    }
}
