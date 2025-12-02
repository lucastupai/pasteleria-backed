package cl.duoc.pasteleria.pasteleria_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.pasteleria.pasteleria_backend.model.Producto;
import cl.duoc.pasteleria.pasteleria_backend.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Producto crear(Producto producto) {
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        return repository.findById(id)
                .map(p -> {
                    p.setNombre(productoActualizado.getNombre());
                    p.setCategoria(productoActualizado.getCategoria());
                    p.setPrecio(productoActualizado.getPrecio());
                    p.setImagen(productoActualizado.getImagen());
                    p.setDescripcion(productoActualizado.getDescripcion());
                    return repository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
