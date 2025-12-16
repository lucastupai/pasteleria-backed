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
    }private void cargarProductos() {
    long count = productoRepository.count();
    System.out.println("=== DataLoader: productos en BD = " + count + " ===");

    if (count == 0) {
        System.out.println("=== DataLoader: insertando productos Pastelería Mil Sabores ===");

        // helper local
        java.util.function.Function<Object[], Producto> crear = (arr) -> {
            Producto p = new Producto();
            p.setNombre((String) arr[0]);
            p.setCategoria((String) arr[1]);
            p.setPrecio((Integer) arr[2]);
            p.setDescripcion((String) arr[3]);
            p.setImagen((String) arr[4]);
            return p;
        };

        productoRepository.save(crear.apply(new Object[]{
                "TC001 - Torta Cuadrada de Chocolate",
                "Tortas Cuadradas",
                45000,
                "Torta cuadrada de chocolate.",
                "https://images.unsplash.com/photo-1601972599722-c6b6a6cde7a5?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "TC002 - Torta Cuadrada de Frutas",
                "Tortas Cuadradas",
                50000,
                "Torta cuadrada con frutas.",
                "https://images.unsplash.com/photo-1542826438-8b64f8c4b4f6?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "TT001 - Torta Circular de Vainilla",
                "Tortas Circulares",
                40000,
                "Torta circular de vainilla.",
                "https://images.unsplash.com/photo-1605478528913-1f2dcd89c1cc?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "TT002 - Torta Circular de Manjar",
                "Tortas Circulares",
                42000,
                "Torta circular de manjar.",
                "https://images.unsplash.com/photo-1599785209798-0b40a45d5e21?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PI001 - Mousse de Chocolate",
                "Postres Individuales",
                5000,
                "Mousse de chocolate.",
                "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PI002 - Tiramisú Clásico",
                "Postres Individuales",
                5500,
                "Tiramisú clásico.",
                "https://images.unsplash.com/photo-1519676867240-f03562e64548?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PSA001 - Torta Sin Azúcar de Naranja",
                "Productos Sin Azúcar",
                48000,
                "Torta sin azúcar sabor naranja.",
                "https://images.unsplash.com/photo-1541783245831-57d6fb0926d3?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PSA002 - Cheesecake Sin Azúcar",
                "Productos Sin Azúcar",
                47000,
                "Cheesecake sin azúcar.",
                "https://images.unsplash.com/photo-1541592106381-b31e9677c0e5?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PT001 - Empanada de Manzana",
                "Pastelería Tradicional",
                3000,
                "Empanada dulce de manzana.",
                "https://images.unsplash.com/photo-1546549039-ef7f6a0f6c6d?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PT002 - Tarta de Santiago",
                "Pastelería Tradicional",
                6000,
                "Tarta de almendras.",
                "https://images.unsplash.com/photo-1541783245831-57d6fb0926d3?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PG001 - Brownie Sin Gluten",
                "Productos Sin Gluten",
                4000,
                "Brownie sin gluten.",
                "https://images.unsplash.com/photo-1601972599722-c6b6a6cde7a5?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PG002 - Pan Sin Gluten",
                "Productos Sin Gluten",
                3500,
                "Pan sin gluten.",
                "https://images.unsplash.com/photo-1549931319-a545dcf3bc73?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PV001 - Torta Vegana de Chocolate",
                "Productos Veganos",
                50000,
                "Torta vegana de chocolate.",
                "https://images.unsplash.com/photo-1601972599722-c6b6a6cde7a5?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "PV002 - Galletas Veganas de Avena",
                "Productos Veganos",
                4500,
                "Galletas veganas de avena.",
                "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "TE001 - Torta Especial de Cumpleaños",
                "Tortas Especiales",
                55000,
                "Torta especial de cumpleaños.",
                "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=800&q=80"
        }));

        productoRepository.save(crear.apply(new Object[]{
                "TE002 - Torta Especial de Boda",
                "Tortas Especiales",
                60000,
                "Torta especial de boda.",
                "https://images.unsplash.com/photo-1542826438-8b64f8c4b4f6?auto=format&fit=crop&w=800&q=80"
        }));

        System.out.println("=== DataLoader: productos insertados, total = "
                + productoRepository.count() + " ===");
    }
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
