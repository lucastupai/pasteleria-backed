package cl.duoc.pasteleria.pasteleria_backend.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duoc.pasteleria.pasteleria_backend.model.Producto;
import cl.duoc.pasteleria.pasteleria_backend.repository.ProductoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository repository;

    public DataLoader(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== DataLoader: productos en BD = " + repository.count() + " ===");

        if (repository.count() == 0) {
            System.out.println("=== DataLoader: insertando productos de ejemplo ===");

            Producto p1 = new Producto(
                    "Torta Tres Leches",
                    "Tortas",
                    15990,
                    "https://via.placeholder.com/300x200?text=Tres+Leches",
                    "Torta tres leches tradicional, 12 porciones."
            );

            Producto p2 = new Producto(
                    "Cheesecake Frutilla",
                    "Cheesecake",
                    13990,
                    "https://via.placeholder.com/300x200?text=Cheesecake+Frutilla",
                    "Cheesecake con base de galleta y topping de frutilla."
            );

            Producto p3 = new Producto(
                    "Cupcakes Vainilla",
                    "Cupcakes",
                    8990,
                    "https://via.placeholder.com/300x200?text=Cupcakes+Vainilla",
                    "Caja de 12 cupcakes de vainilla con frosting."
            );

            repository.saveAll(Arrays.asList(p1, p2, p3));

            System.out.println("=== DataLoader: productos insertados, total = " + repository.count() + " ===");
        } else {
            System.out.println("=== DataLoader: ya existen productos, no inserta ===");
        }
    }
}
