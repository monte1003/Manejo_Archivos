import java.io.*;
import java.util.*;

public class CrudArchivos {

    private static final String ARCHIVO_CLIENTES = "clientes.csv";
    private static final String ARCHIVO_PEDIDOS = "pedidos.csv";

    public static void registrarCliente(Usuario cliente) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES, true));
        bw.write(cliente.toCSVCliente());
        bw.newLine();
        bw.close();
    }

    public static void listarClientes() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            if (datos[4].equals("1")) {
                System.out.println("ID: " + datos[0] +
                        " | Nombre: " + datos[1] +
                        " " + datos[2] +
                        " | Tel: " + datos[3]);
            }
        }
        br.close();
    }

    public static void eliminarCliente(int id) throws IOException {

        List<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES));
        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[0]) == id) {
                datos[4] = "0";
                linea = String.join(",", datos);
            }

            lineas.add(linea);
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES));
        for (String l : lineas) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public static void registrarPedido(Usuario pedido) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PEDIDOS, true));
        bw.write(pedido.toCSVPedido());
        bw.newLine();
        bw.close();
    }

    public static void listarPedidosPorCliente(int id_cliente) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PEDIDOS));
        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[1]) == id_cliente && datos[5].equals("1")) {

                System.out.println("Pedido: " + datos[0] +
                        " | Producto: " + datos[2] +
                        " | Precio: " + datos[3] +
                        " | Cantidad: " + datos[4]);
            }
        }
        br.close();
    }
}