import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main{
    public static void main(String [] args){
        int id;String nombre;String apellido;int telefono;int activo;
        int id_pedido; String producto; float precio; int cantidad; int activo_pedido;
        int n = 0;
        List<Usuario> lista = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Menú de opciones\n1) Registrar un cliente\n2) Listar clientes\n3) Eliminar un cliente\n4) Registrar un pedido\n5) Listar pedidos de un cliente\n6) Salir\nIntroduzca la opción");
            int op;
            op = sc.nextInt();
            switch (op) {
                case 1:
                        System.out.println("Ingrese id del usuario: ");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Ingrese nombre: ");
                        nombre = sc.nextLine();
                        System.out.println("Ingrese apellido: ");
                        apellido = sc.nextLine();
                        System.out.println("Ingrese teléfono:");
                        telefono = sc.nextInt();
                        System.out.println("Ingrese si esta activo o no: ");
                        activo = sc.nextInt();
                        try{
                            CrudArchivos.crearUsuario(new Usuario(id, nombre, apellido, telefono, activo));
                        }
                        catch (IOException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                    break;
                case 2:
                    try{
                        lista = CrudArchivos.leerUsuarios();
                        System.out.println(lista);
                    }
                    catch (IOException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Introduzca el id del usuario a eliminar");
                    id = sc.nextInt();
                    try{
                        CrudArchivos.eliminarUsuario(id);
                    }
                    catch (IOException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Ingrese id del pedido: ");
                    id_pedido = sc.nextInt();
                    System.out.println("Ingrese id del usuario: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese producto: ");
                    producto = sc.nextLine();
                    System.out.println("Ingrese precio: ");
                    precio = sc.nextFloat();
                    System.out.println("Ingrese cantidad:");
                    cantidad = sc.nextInt();
                    System.out.println("Ingrese si esta activo o no: ");
                    activo_pedido = sc.nextInt();
                    try{
                        CrudArchivos.registrarPedido(new Pedido(id_pedido, id, producto, precio, cantidad, activo_pedido));
                    }
                    catch (IOException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el id del cliente: ");
                    id = sc.nextInt();
                    try{
                        CrudArchivos.listarPedido(id);
                    }
                    catch (IOException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    n = 1;
                default:
                    break;
            }
        }
        while (n == 0);
        sc.close();
    }
}