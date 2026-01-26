package es.etg.daw.dawes.thym.productos.infraestructure.web.enums;

/**
 * Representa los posibles atributos que podemos usar
 * en los modelos de la vista
 */
public enum ModelAttribute {
    PRODUCT_LIST("productos"),
    SINGLE_PRODUCT("producto"),
    ERROR_MESSAGE("errorMsg"),
    SUCCESS_MESSAGE("successMsg");

    private final String name;

    ModelAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
