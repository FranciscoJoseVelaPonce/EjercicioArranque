import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Tarea> listaTareas = new ArrayList<>();
    private static final Scanner escaneo = new Scanner(System.in);

    public static void main(String[] argumentos) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            String opcion = escaneo.nextLine();

            switch (opcion) {
                case "1" -> añadirTarea();
                case "2" -> verTodasLasTareas();
                case "3" -> verTareasPendientes();
                case "4" -> marcarTareaCompletada();
                case "5" -> eliminarTarea();
                case "6" -> {
                    salir = true;
                    System.out.println("Saliendo de la aplicación...");
                }
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.\n");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== GESTOR DE TAREAS EjercicioArranque ===");
        System.out.println("1. Añadir tareas");
        System.out.println("2. Ver todas las tareas");
        System.out.println("3. Ver tareas pendientes");
        System.out.println("4. Marcar tarea como completada");
        System.out.println("5. Eliminar tarea");
        System.out.println("6. Salir");
    }

    private static void añadirTarea() {
        System.out.println("\n--- Añadir Tareas (escribe 'fin' o deja en blanco para salir) ---");

        while (true) {
            System.out.print("Introduce la descripción de la tarea: ");
            String descripcion = escaneo.nextLine().trim();

            if (descripcion.isEmpty() || descripcion.equalsIgnoreCase("fin")) {
                System.out.println("Volviendo al menú principal...\n");
                break;
            }

            listaTareas.add(new Tarea(descripcion));
            System.out.println("Tarea añadida con éxito.");
        }
    }

    private static void verTodasLasTareas() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas en la lista.\n");
            return;
        }
        System.out.println("\n--- Todas las Tareas ---");
        for (int i = 0; i < listaTareas.size(); i++) {
            System.out.println((i + 1) + ". " + listaTareas.get(i));
        }
        System.out.println();
    }

    private static void verTareasPendientes() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas en la lista.\n");
            return;
        }
        System.out.println("\n--- Tareas Pendientes ---");
        boolean hayPendientes = false;
        for (int i = 0; i < listaTareas.size(); i++) {
            Tarea t = listaTareas.get(i);
            if (!t.estaCompletada()) {
                System.out.println((i + 1) + ". " + t);
                hayPendientes = true;
            }
        }
        if (!hayPendientes) {
            System.out.println("¡No tienes tareas pendientes!");
        }
        System.out.println();
    }

    private static void marcarTareaCompletada() {
        verTodasLasTareas();
        if (listaTareas.isEmpty()) return;

        System.out.print("Introduce el número de la tarea a marcar como completada: ");
        try {
            int indice = Integer.parseInt(escaneo.nextLine()) - 1;
            if (indice >= 0 && indice < listaTareas.size()) {
                listaTareas.get(indice).marcarCompletada();
                System.out.println("Tarea marcada como completada.\n");
            } else {
                System.out.println("Número de tarea no válido.\n");
            }
        } catch (NumberFormatException error) {
            System.out.println("Por favor, introduce un número válido.\n");
        }
    }

    private static void eliminarTarea() {
        verTodasLasTareas();
        if (listaTareas.isEmpty()) return;

        System.out.print("Introduce el número de la tarea que deseas eliminar: ");
        try {
            int indice = Integer.parseInt(escaneo.nextLine()) - 1;
            if (indice >= 0 && indice < listaTareas.size()) {
                Tarea eliminada = listaTareas.remove(indice);
                System.out.println("Tarea '" + eliminada.obtenerDescripcion() + "' eliminada con éxito.\n");
            } else {
                System.out.println("Número de tarea no válido.\n");
            }
        } catch (NumberFormatException error) {
            System.out.println("Por favor, introduce un número válido.\n");
        }
    }
}