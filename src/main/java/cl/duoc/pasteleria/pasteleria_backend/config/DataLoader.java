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
            System.out.println("=== DataLoader: insertando productos de ejemplo ===");

            Producto p1 = new Producto();
            p1.setNombre("Torta Mil Hojas");
            p1.setDescripcion("Torta tradicional chilena con manjar");
            p1.setPrecio(15000);
            p1.setCategoria("Tortas");
            p1.setImagen("https://example.com/milhojas.jpg");

            Producto p2 = new Producto();
            p2.setNombre("Cheesecake Frutilla");
            p2.setDescripcion("Cheesecake con cobertura de frutillas");
            p2.setPrecio(18000);
            p2.setCategoria("Tortas");
            p2.setImagen("https://example.com/cheesecake.jpg");

            Producto p3 = new Producto();
            p3.setNombre("Pie de Limón");
            p3.setDescripcion("Clásico pie de limón con merengue");
            p3.setPrecio(12000);
            p3.setCategoria("Postres");
            p3.setImagen("https://example.com/piedelimon.jpg");

            productoRepository.save(p1);
            productoRepository.save(p2);
            productoRepository.save(p3);

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
