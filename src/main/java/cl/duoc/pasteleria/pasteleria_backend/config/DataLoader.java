package cl.duoc.pasteleria.pasteleria_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.duoc.pasteleria.pasteleria_backend.model.Producto;
import cl.duoc.pasteleria.pasteleria_backend.model.Usuario;
import cl.duoc.pasteleria.pasteleria_backend.repository.ProductoRepository;
import cl.duoc.pasteleria.pasteleria_backend.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(ProductoRepository productoRepository,
                      UsuarioRepository usuarioRepository,
                      PasswordEncoder passwordEncoder) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        cargarProductos();
        cargarUsuarios();
    }

    private void cargarProductos() {
        long count = productoRepository.count();
        System.out.println("=== DataLoader: productos en BD = " + count + " ===");

        if (count == 0) {
            System.out.println("=== DataLoader: insertando productos Pastelería Mil Sabores (16) ===");

            productoRepository.save(crear("Torta Cuadrada de Chocolate", "Tortas Cuadradas", 45000,
                    "Torta Cuadrada de Chocolate", "TC001"));
            productoRepository.save(crear("Torta Cuadrada de Frutas", "Tortas Cuadradas", 50000,
                    "Torta Cuadrada de Frutas", "TC002"));

            productoRepository.save(crear("Torta Circular de Vainilla", "Tortas Circulares", 40000,
                    "Torta Circular de Vainilla", "TT001"));
            productoRepository.save(crear("Torta Circular de Manjar", "Tortas Circulares", 42000,
                    "Torta Circular de Manjar", "TT002"));

            productoRepository.save(crear("Mousse de Chocolate", "Postres Individuales", 5000,
                    "Mousse de Chocolate", "PI001"));
            productoRepository.save(crear("Tiramisú Clásico", "Postres Individuales", 5500,
                    "Tiramisú Clásico", "PI002"));

            productoRepository.save(crear("Torta Sin Azúcar de Naranja", "Productos Sin Azúcar", 48000,
                    "Torta sin azúcar sabor naranja", "PSA001"));
            productoRepository.save(crear("Cheesecake Sin Azúcar", "Productos Sin Azúcar", 47000,
                    "Cheesecake sin azúcar añadida", "PSA002"));

            productoRepository.save(crear("Empanada de Manzana", "Pastelería Tradicional", 3000,
                    "Empanada dulce rellena de manzana", "PT001"));
            productoRepository.save(crear("Tarta de Santiago", "Pastelería Tradicional", 6000,
                    "Tarta tradicional de almendras", "PT002"));

            productoRepository.save(crear("Brownie Sin Gluten", "Productos Sin Gluten", 4000,
                    "Brownie especial sin gluten", "PG001"));
            productoRepository.save(crear("Pan Sin Gluten", "Productos Sin Gluten", 3500,
                    "Pan artesanal sin gluten", "PG002"));

            productoRepository.save(crear("Torta Vegana de Chocolate", "Productos Veganos", 50000,
                    "Torta vegana sin ingredientes de origen animal", "PV001"));
            productoRepository.save(crear("Galletas Veganas de Avena", "Productos Veganos", 4500,
                    "Galletas veganas artesanales de avena", "PV002"));

            productoRepository.save(crear("Torta Especial de Cumpleaños", "Tortas Especiales", 55000,
                    "Torta especial personalizada para cumpleaños", "TE001"));
            productoRepository.save(crear("Torta Especial de Boda", "Tortas Especiales", 60000,
                    "Torta especial de boda personalizada", "TE002"));

            System.out.println("=== DataLoader: productos insertados, total = " + productoRepository.count() + " ===");
        }
    }

    // Nota: Como tu modelo Producto NO tiene "codigo", lo metemos en el nombre (ej: "TC001 - ...")
    private Producto crear(String nombre, String categoria, int precio, String descripcion, String codigo) {
        Producto p = new Producto();
        p.setNombre(codigo + " - " + nombre);
        p.setCategoria(categoria);
        p.setPrecio(precio);
        p.setDescripcion(descripcion);
        // imagen opcional (puedes cambiar por URLs reales después)
        p.setImagen("https://via.placeholder.com/400x250.png?text=" + codigo);
        return p;
    }

    private void cargarUsuarios() {
        long countUsuarios = usuarioRepository.count();
        System.out.println("=== DataLoader: usuarios en BD = " + countUsuarios + " ===");

        if (countUsuarios == 0) {
            System.out.println("=== DataLoader: creando usuarios de prueba ===");

            Usuario admin = new Usuario();
            admin.setUsername("admin@pasteleria.cl");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol("ADMIN");

            Usuario cliente = new Usuario();
            cliente.setUsername("cliente@pasteleria.cl");
            cliente.setPassword(passwordEncoder.encode("cliente123"));
            cliente.setRol("CLIENTE");

            usuarioRepository.save(admin);
            usuarioRepository.save(cliente);

            System.out.println(System.out.println("=== Usuarios de prueba creados: admin@pasteleria.cl/admin123 (ADMIN), cliente@pasteleria.cl/cliente123 (CLIENTE) ==="););
        }
    }
}
