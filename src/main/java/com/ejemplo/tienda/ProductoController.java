package com.ejemplo.tienda;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>(List.of(
            new Producto(1, "Botas Hombre Negras", 580000, 15),
            new Producto(2, "Botas Niño Negras", 480000, 20),
            new Producto(3, "Botas Hombre Clasic", 315000, 10)
    ));

    private int siguienteId = 4;

    @GetMapping("/productos")
    public List<Producto> listar() {
        return productos;
    }

    /*@GetMapping("/productos/{id}")
    public Producto obtener(@PathVariable int id) {
        return productos.get(id - 1);
    }*/

    @GetMapping("/productos/{id}")
    public Producto buscar(@PathVariable int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el producto con el id " + id);
    }

    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody NuevoProducto nuevo) {
        if (nuevo.precio() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio debe ser mayor a 0");
        }
        Producto producto = new Producto(siguienteId++, nuevo.nombre(), nuevo.precio(), nuevo.cantidad());
        productos.add(producto);
        return producto;
    }
}
