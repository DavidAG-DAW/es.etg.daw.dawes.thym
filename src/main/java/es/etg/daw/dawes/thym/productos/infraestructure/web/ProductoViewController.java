package es.etg.daw.dawes.thym.productos.infraestructure.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.etg.daw.dawes.thym.productos.application.command.CreateProductoCommand;
import es.etg.daw.dawes.thym.productos.application.service.CreateProductoService;
import es.etg.daw.dawes.thym.productos.application.service.FindProductoService;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.infraestructure.web.constants.WebRoutes;
import es.etg.daw.dawes.thym.productos.infraestructure.web.enums.ModelAttribute;
import es.etg.daw.dawes.thym.productos.infraestructure.web.enums.ThymView;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@RequestMapping(WebRoutes.PRODUCTOS_BASE)
public class ProductoViewController {
    
    private final FindProductoService findProductoService;
    private final CreateProductoService createProductoService;

    //Listado de Productos http://localhost:8082/web/productos
    @GetMapping(WebRoutes.PRODUCTOS_BASE)
    public String listar(Model model) {
        // Consulto todos los productos y los meto en un atributo del modelo para poder acceder a ellos en la JSP.
        model.addAttribute(ModelAttribute.PRODUCT_LIST.getName(), findProductoService.findAll());
        return ThymView.PRODUCT_LIST.getPath(); // Busca productos-lista.jsp
    }

    // Carga la vista del formulario http://localhost:8082/web/productos/nuevo
    @GetMapping(WebRoutes.PRODUCTOS_NUEVO)
    public String formulario(Model model) {

        // Agrego un atributo con el nombre "producto" y con los datos vacíos, este producto se rellenará con los datos de la vista.
        // es necesario que producto tenga el constructor vacío: @NoArgsConstructor
        model.addAttribute(ModelAttribute.SINGLE_PRODUCT.getName(), new Producto());
        
        return ThymView.PRODUCT_FORM.getPath(); //Devuelvo la vista que carga el formulario
    }

    // Este método crea el producto y devuelve la vista del mensaje de creado
    @PostMapping(WebRoutes.PRODUCTOS_NUEVO)
    public String crearProducto(@RequestParam String nombre,
            @RequestParam double precio,
            Model model){
            
            createProductoService.createProducto(new CreateProductoCommand(nombre, precio));
        
        return ThymView.PRODUCT_CREATED.getPath();
    }
}
