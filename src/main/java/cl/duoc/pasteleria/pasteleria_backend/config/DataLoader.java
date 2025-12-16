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

        // ✅ IMPORTANTE: siempre reiniciamos para que se vean TUS productos
        System.out.println("=== DataLoader: borrando productos anteriores ===");
        productoRepository.deleteAll();

        System.out.println("=== DataLoader: insertando productos Pastelería Mil Sabores ===");

        productoRepository.save(new Producto(
            "TC001 - Torta Cuadrada de Chocolate",
            "Torta cuadrada rellena y cubierta con chocolate",
            45000,
            "Tortas Cuadradas",
            "https://images.unsplash.com/photo-1601972599722-c6b6a6cde7a5"
        ));

        productoRepository.save(new Producto(
            "TC002 - Torta Cuadrada de Frutas",
            "Torta cuadrada con frutas frescas de estación",
            50000,
            "Tortas Cuadradas",
            "https://images.unsplash.com/photo-1542826438-8b64f8c4b4f6"
        ));

        productoRepository.save(new Producto(
            "TT001 - Torta Circular de Vainilla",
            "Torta circular sabor vainilla con crema",
            40000,
            "Tortas Circulares",
            "https://images.unsplash.com/photo-1605478528913-1f2dcd89c1cc"
        ));

        productoRepository.save(new Producto(
            "TT002 - Torta Circular de Manjar",
            "Torta circular rellena con manjar tradicional",
            42000,
            "Tortas Circulares",
            "https://images.unsplash.com/photo-1599785209798-0b40a45d5e21"
        ));

        productoRepository.save(new Producto(
            "PI001 - Mousse de Chocolate",
            "Postre individual de mousse de chocolate",
            5000,
            "Postres Individuales",
            "https://images.unsplash.com/photo-1606313564200-e75d5e30476c"
        ));

        productoRepository.save(new Producto(
            "PI002 - Tiramisú Clásico",
            "Postre individual tiramisú tradicional",
            5500,
            "Postres Individuales",
            "https://images.unsplash.com/photo-1612197527772-8c4e7c2d9f8c"
        ));

        productoRepository.save(new Producto(
            "PSA001 - Torta Sin Azúcar de Naranja",
            "Torta especial sin azúcar sabor naranja",
            48000,
            "Productos Sin Azúcar",
            "https://images.unsplash.com/photo-1608219959301-8d98b6a4e3f5"
        ));

        productoRepository.save(new Producto(
            "PSA002 - Cheesecake Sin Azúcar",
            "Cheesecake sin azúcar añadida",
            47000,
            "Productos Sin Azúcar",
            "https://images.unsplash.com/photo-1606890737304-57a1ca8a5e22"
        ));

        productoRepository.save(new Producto(
            "PT001 - Empanada de Manzana",
            "Empanada dulce rellena de manzana",
            3000,
            "Pastelería Tradicional",
            "https://images.unsplash.com/photo-1608198093002-ad4e005484ec"
        ));

        productoRepository.save(new Producto(
            "PT002 - Tarta de Santiago",
            "Tarta tradicional de almendras",
            6000,
            "Pastelería Tradicional",
            "https://images.unsplash.com/photo-1605475128202-7d92c2e5e1c3"
        ));

        productoRepository.save(new Producto(
            "PG001 - Brownie Sin Gluten",
            "Brownie especial sin gluten",
            4000,
            "Productos Sin Gluten",
            "https://images.unsplash.com/photo-1599785209798-0b40a45d5e21"
        ));

        productoRepository.save(new Producto(
            "PG002 - Pan Sin Gluten",
            "Pan artesanal sin gluten",
            3500,
            "Productos Sin Gluten",
            "https://images.unsplash.com/photo-1546549039-ef7f6a0f6c6d"
        ));

        productoRepository.save(new Producto(
            "PV001 - Torta Vegana de Chocolate",
            "Torta vegana sin ingredientes de origen animal",
            50000,
            "Productos Veganos",
            "https://images.unsplash.com/photo-1601972599722-c6b6a6cde7a5"
        ));

        productoRepository.save(new Producto(
            "PV002 - Galletas Veganas de Avena",
            "Galletas veganas artesanales de avena",
            4500,
            "Productos Veganos",
            "https://images.unsplash.com/photo-1599785209798-0b40a45d5e21"
        ));

        productoRepository.save(new Producto(
            "TE001 - Torta Especial de Cumpleaños",
            "Torta especial personalizada para cumpleaños",
            55000,
            "Tortas Especiales",
            "https://images.unsplash.com/photo-1606313564200-e75d5e30476c"
        ));

        productoRepository.save(new Producto(
            "TE002 - Torta Especial de Boda",
            "Torta especial de boda personalizada",
            60000,
            "Tortas Especiales",
            "https://images.unsplash.com/photo-1542826438-8b64f8c4b4f6"
        ));

        System.out.println("=== DataLoader: productos insertados, total = "
                + productoRepository.count() + " ===");
    }

    private void cargarUsuarios() {
        long countUsuarios = usuarioRepository.count();
        System.out.println("=== DataLoader: usuarios en BD = " + countUsuarios + " ===");

        if (countUsuarios == 0) {
            System.out.println("=== DataLoader: creando usuarios de prueba ===");

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol("ADMIN");

            Usuario cliente = new Usuario();
            cliente.setUsername("cliente");
            cliente.setPassword(passwordEncoder.encode("cliente123"));
            cliente.setRol("CLIENTE");

            usuarioRepository.save(admin);
            usuarioRepository.save(cliente);

            System.out.println("=== Usuarios de prueba creados: " +
                    "admin/admin123 (ADMIN), cliente/cliente123 (CLIENTE) ===");
        }
    }
}
