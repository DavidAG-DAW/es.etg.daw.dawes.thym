package es.etg.daw.dawes.thym.productos.infraestructure.web;

import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import es.etg.daw.dawes.thym.productos.application.command.CreateProductoCommand;
import es.etg.daw.dawes.thym.productos.application.service.CreateProductoService;
import es.etg.daw.dawes.thym.productos.application.service.FindProductoService;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.infraestructure.web.constants.WebRoutes;
import es.etg.daw.dawes.thym.productos.infraestructure.web.enums.ModelAttribute;
import es.etg.daw.dawes.thym.productos.infraestructure.web.enums.ThymView;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@RequestMapping(WebRoutes.PRODUCTOS_BASE)
public class ProductoViewController {
    
    private final FindProductoService findProductoService;
    private final CreateProductoService createProductoService;
    private final TemplateEngine templateEngine;

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

    //Listado de Productos http://localhost:8082/web/productos/pdf
    @GetMapping(WebRoutes.PRODUCTOS_PDF)
    public void exportarPDF(HttpServletResponse response) throws Exception {

        //Obtengo los datos
        List<Producto> productos = findProductoService.findAll();

        //Preparar el contexto de Thymeleaf
        Context context = new Context();
        context.setVariable("productos", productos);

        //Ya tengo los datos en el contexto de Thymeleaf, ahora le doy la plantilla para que me devuelva
        //  la plantilla con los datos rellenos (el mismo html que estamos devolviendo al usuario pero ahora lo meto en un String).
        String htmlContent = templateEngine.process(ThymView.PRODUCT_LIST_PDF.getPath(), context);



        //Preparo la respuesta diciendole que voy a devolver un pdf
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=productos.pdf");

        //Código OpenHTML to PDF - CAMBIOS
        //******************************
        OutputStream outputStream = response.getOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(htmlContent, null); // El 'null' es la base URL
        builder.toStream(outputStream);

        builder.run();


    }
}
