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

            opcion = sc.nextInt();
            sc.nextLine();

            try {

                // 1. Registrar cliente
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

                    Cliente nuevo = new Cliente(id, nombre, apellido, telefono, 1);
                    CRUDClientes.registrarCliente(nuevo);

                }

                // 2. Listar clientes
                else if (opcion == 2) {

                    CRUDClientes.listarClientes();

                }

                // 3. Eliminar cliente (lógico)
                else if (opcion == 3) {

                    System.out.print("Ingrese ID del cliente a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();

                    CRUDClientes.eliminarCliente(idEliminar);

                }

                // 4. Registrar pedido
                else if (opcion == 4) {

                    System.out.print("Ingrese ID del pedido: ");
                    int idPedido = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese ID del cliente: ");
                    int idCliente = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese nombre del producto: ");
                    String producto = sc.nextLine();

                    System.out.print("Ingrese precio (opcional, 0 si no aplica): ");
                    double precio = sc.nextDouble();

                    System.out.print("Ingrese cantidad (opcional, 0 si no aplica): ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    Pedido nuevoPedido = new Pedido(idPedido, idCliente, producto, precio, cantidad, 1);
                    CRUDPedidos.registrarPedido(nuevoPedido);

                }

                // 5. Listar pedidos de un cliente
                else if (opcion == 5) {

                    System.out.print("Ingrese ID del cliente: ");
                    int idCliente = sc.nextInt();
                    sc.nextLine();

                    CRUDPedidos.listarPedidosPorCliente(idCliente);

                }

                // 6. Salir
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