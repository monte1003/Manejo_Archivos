import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudArchivos {

    public static void crearUsuario(Usuario usuario) throws IOException {
    FileWriter fw = new FileWriter("usuarios.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(usuario.toString());
    bw.newLine();
    bw.close();
    }

    public static void listarClientes() throws IOException {
        File file = new File(ARCHIVO_CLIENTES);
        if (!file.exists()) return;

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] datos = linea.split(",");

            if (datos[4].equals("1")) { 
                System.out.println(linea);
            }
        }
        sc.close();
    }
    
    public static void eliminarCliente(int id) throws IOException {

        List<String> lineas = new ArrayList<>();
        Scanner sc = new Scanner(new File(ARCHIVO_CLIENTES));

        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[0]) == id) {
                datos[4] = "0"; // inactivo
                linea = String.join(",", datos);
            }

            lineas.add(linea);
        }
        sc.close();

        BufferedWriter bw = new BufferedWriter(
                new FileWriter(ARCHIVO_CLIENTES));

        for (String l : lineas) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public static void registrarPedido(Pedido p) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(ARCHIVO_PEDIDOS, true));
        bw.write(p.toString());
        bw.newLine();
        bw.close();
    }

    public static void listarPedidosCliente(int idCliente) throws IOException {

        File file = new File(ARCHIVO_PEDIDOS);
        if (!file.exists()) return;

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[1]) == idCliente
                    && datos[5].equals("1")) { 
                System.out.println(linea);
            }
        }
        sc.close();
    }
}