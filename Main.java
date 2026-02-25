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
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Registrar pedido");
            System.out.println("5. Listar pedidos de un cliente");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            try {

                if (opcion == 1) {

                    System.out.print("Ingrese ID del cliente: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Ingrese telefono: ");
                    String telefono = sc.nextLine();

                    Usuario nuevoCliente = new Usuario(id, nombre, apellido, telefono, 1);
                    CrudArchivos.registrarCliente(nuevoCliente);
                }

                else if (opcion == 2) {

                    CrudArchivos.listarClientes();
                }

                else if (opcion == 3) {

                    System.out.print("Ingrese ID del cliente a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    CrudArchivos.eliminarCliente(id);
                }

                else if (opcion == 4) {

                    System.out.print("Ingrese ID del pedido: ");
                    int id_pedido = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese ID del cliente: ");
                    int id_cliente = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese nombre del producto: ");
                    String producto = sc.nextLine();

                    System.out.print("Ingrese precio (0 si no aplica): ");
                    double precio = sc.nextDouble();

                    System.out.print("Ingrese cantidad (0 si no aplica): ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    Usuario nuevoPedido = new Usuario(id_pedido, id_cliente, producto, precio, cantidad, 1);
                    CrudArchivos.registrarPedido(nuevoPedido);
                }

                else if (opcion == 5) {

                    System.out.print("Ingrese ID del cliente: ");
                    int id_cliente = sc.nextInt();
                    sc.nextLine();

                    CrudArchivos.listarPedidosPorCliente(id_cliente);
                }

                else if (opcion == 6) {

                    System.out.println("Programa finalizado correctamente.");
                }

                else {
                    System.out.println("Opcion no valida.");
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}