import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 6) {

            System.out.println("\n====== SISTEMA DE CLIENTES Y PEDIDOS ======");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Eliminar cliente ");
            System.out.println("4. Registrar pedido");
            System.out.println("5. Listar pedidos de un cliente");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = 0;
            }

            try {

                if (opcion == 1) {

                    int id = leerEntero(sc, "Ingrese ID del cliente: ");

                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();

                    long telefono = leerLong(sc, "Ingrese telefono: ");

                    Usuario nuevoCliente = new Usuario(id, nombre, apellido, telefono, 1);
                    CrudArchivos.registrarCliente(nuevoCliente);
                }

                else if (opcion == 2) {
                    CrudArchivos.listarClientes();
                }

                else if (opcion == 3) {
                    int id = leerEntero(sc, "Ingrese ID del cliente a eliminar: ");
                    CrudArchivos.eliminarCliente(id);
                }

                else if (opcion == 4) {

                    int id_pedido = leerEntero(sc, "Ingrese ID del pedido: ");
                    int id_cliente = leerEntero(sc, "Ingrese ID del cliente: ");

                    System.out.print("Ingrese nombre del producto: ");
                    String producto = sc.nextLine();

                    double precio = leerDouble(sc, "Ingrese precio: ");
                    int cantidad = leerEntero(sc, "Ingrese cantidad: ");

                    Usuario nuevoPedido = new Usuario(id_pedido, id_cliente, producto, precio, cantidad, 1);
                    CrudArchivos.registrarPedido(nuevoPedido);
                }

                else if (opcion == 5) {
                    int id_cliente = leerEntero(sc, "Ingrese ID del cliente: ");
                    CrudArchivos.listarPedidosPorCliente(id_cliente);
                }

                else if (opcion == 6) {
                    System.out.println("Programa finalizado correctamente.");
                }

                else {
                    System.out.println("Opcion no valida.");
                }

            } catch (IOException e) {
                System.out.println("Error de archivo: " + e.getMessage());
            }
        }

        sc.close();
    }

    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero valido.");
            }
        }
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero decimal valido.");
            }
        }
    }

    public static long leerLong(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }
}