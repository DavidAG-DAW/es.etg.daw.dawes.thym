package es.etg.daw.dawes.thym.productos.domain.model;

import es.etg.daw.dawes.thym.common.domain.model.Identificador;

public class ProductoId extends Identificador{

    public ProductoId(Integer value) {
        super(value);
    }

    @Override
    public String toString(){
        return this.getValue().toString();
    }
}

