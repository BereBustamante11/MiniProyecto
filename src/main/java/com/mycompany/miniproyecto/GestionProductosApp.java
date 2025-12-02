package com.mycompany.miniproyecto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bere0
 */
import java.util.*;
import java.util.stream.*;

public class GestionProductosApp {
    private List<Producto> inventario = new ArrayList<>();

    public void inicializarInventario() {
        inventario.add(new Producto(1, "Televisor 50\"", "Electrónica", 8000.0, 5, true));
        inventario.add(new Producto(2, "Auriculares", "Electrónica", 350.0, 20, true));
        inventario.add(new Producto(3, "Cafetera", "Hogar", 1200.0, 3, true));
        inventario.add(new Producto(4, "Libro Java", "Libros", 450.0, 10, false));
        inventario.add(new Producto(5, "Smartphone", "Electrónica", 6000.0, 2, true));
       
    }

    // Función de orden superior que imprime título y ejecuta la operación
    public void ejecutarOperacion(String titulo, Runnable operacion) {
        System.out.println("\n=== " + titulo + " ===");
        operacion.run(); // la condición del enunciado exige ejecutar aquí
    }

    public static void main(String[] args) {
        GestionProductosApp app = new GestionProductosApp();
        app.inicializarInventario();

        // 1) mostrarCatalogo
        Runnable mostrarCatalogo = () -> app.inventario.forEach(System.out::println);

        // 2) calcularEstadisticas
        Runnable calcularEstadisticas = () -> {
            // Precio promedio de productos disponibles
            OptionalDouble avgPrecio = app.inventario.stream()
                    .filter(Producto::isDisponible)
                    .mapToDouble(Producto::getPrecio)
                    .average();

            int totalStock = app.inventario.stream()
                    .mapToInt(Producto::getStock)
                    .sum();

            Optional<Producto> masCaro = app.inventario.stream()
                    .max(Comparator.comparingDouble(Producto::getPrecio));

            Optional<Producto> masBarato = app.inventario.stream()
                    .min(Comparator.comparingDouble(Producto::getPrecio));

            System.out.println("Precio promedio (disponibles): " + (avgPrecio.isPresent() ? String.format("%.2f", avgPrecio.getAsDouble()) : "N/A"));
            System.out.println("Stock total: " + totalStock);
            System.out.println("Producto más caro: " + (masCaro.isPresent() ? masCaro.get() : "N/A"));
            System.out.println("Producto más barato: " + (masBarato.isPresent() ? masBarato.get() : "N/A"));
        };

        // 3) aplicarDescuento (por ejemplo 10% a categoría "Electrónica")
        Runnable aplicarDescuento = () -> {
            String categoriaObjetivo = "Electrónica";
            double descuento = 0.10; // 10%
            app.inventario.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoriaObjetivo))
                .forEach(p -> p.setPrecio(p.getPrecio() * (1 - descuento)));

            System.out.println("Productos de la categoría " + categoriaObjetivo + " con descuento aplicado:");
            app.inventario.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoriaObjetivo))
                .forEach(System.out::println);
        };

        // EJECUCIÓN — todas las operaciones deben pasarse a ejecutarOperacion
        app.ejecutarOperacion("Mostrar catálogo", mostrarCatalogo);
        app.ejecutarOperacion("Calcular estadísticas", calcularEstadisticas);
        app.ejecutarOperacion("Aplicar descuento a Electrónica", aplicarDescuento);

    }
}
