package es.etg.daw.dawes.thym.productos.infraestructure.web;

import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import es.etg.daw.dawes.thym.productos.application.command.CreateProductoCommand;
import es.etg.daw.dawes.thym.productos.application.service.CreateProductoService;
import es.etg.daw.dawes.thym.productos.application.service.FindProductoService;
import es.etg.daw.dawes.thym.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.thym.productos.domain.model.Producto;
import es.etg.daw.dawes.thym.productos.infraestructure.web.constants.WebRoutes;
import es.etg.daw.dawes.thym.productos.infraestructure.web.enums.ModelAttribute;
import es.etg.daw.dawes.thym.productos.infraestructure.web.enums.ThymView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// @RequestMapping(WebRoutes.PRODUCTOS_BASE)
public class ProductoViewController {

    private final FindProductoService findProductoService;
    private final CreateProductoService createProductoService;
    private final TemplateEngine templateEngine; // Motor de Thymeleaf
    @Autowired
    private LocaleResolver localeResolver;

    // Listado de Productos http://localhost:8082/web/productos
    @GetMapping(WebRoutes.PRODUCTOS_BASE)
    public String listar(Model model) {

        model.addAttribute(ModelAttribute.PRODUCT_LIST.getName(), findProductoService.findAll());
        return ThymView.PRODUCT_LIST.getPath();
    }

    // Carga la vista del formulario http://localhost:8082/web/productos/nuevo
    @GetMapping(WebRoutes.PRODUCTOS_NUEVO)
    public String formulario(Model model) {

        model.addAttribute(ModelAttribute.SINGLE_PRODUCT.getName(), new Producto());

        return ThymView.PRODUCT_FORM.getPath(); // Devuelvo la vista que carga el formulario
    }

    // Este método crea el producto y devuelve la vista del mensaje de creado
    @PostMapping(WebRoutes.PRODUCTOS_NUEVO)
    public String crearProducto(@RequestParam String nombre,
            @RequestParam double precio,
            @RequestParam(defaultValue = "1") int categoria,
            Model model) {

        createProductoService.createProducto(new CreateProductoCommand(nombre, precio, new CategoriaId(categoria)));

        return ThymView.PRODUCT_CREATED.getPath();
    }

    // Listado de Productos http://localhost:8082/web/productos/pdf
    @GetMapping(WebRoutes.PRODUCTOS_PDF)
    public void exportarPDF(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // Obtengo los datos
        List<Producto> productos = findProductoService.findAll();

        // Obtengo el locale actual de la sesión
        Locale locale = localeResolver.resolveLocale(request);

        // Preparar el contexto de Thymeleaf con el locale
        Context context = new Context(locale);
        context.setVariable("productos", productos);

        String htmlContent = templateEngine.process(ThymView.PRODUCT_LIST_PDF.getPath(), context);

        // Preparo la respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=productos.pdf");

        // Generar PDF
        OutputStream outputStream = response.getOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(htmlContent, null);
        builder.toStream(outputStream);
        builder.run();
    }

}