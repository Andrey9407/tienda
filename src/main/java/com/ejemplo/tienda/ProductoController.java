package com.ejemplo.tienda;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    private final List<Producto> productos = List.of(
            new Producto(1, "Botas Hombre Negras", 580000, 15),
            new Producto(2, "Botas Niño Negras", 480000, 20),
            new Producto(3, "Botas Hombre Clasic", 315000, 10)
    );

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
}
