import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudArchivos {

    public static void crearUsuario(Usuario usuario) throws IOException {
    FileWriter fw = new FileWriter("usuarios.csv", true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(usuario.toString());
    bw.newLine();
    bw.close();
    }

    public static void registrarPedido(Pedido pedido) throws IOException{
        FileWriter fw = new FileWriter("pedidos.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(pedido.toString());
        bw.newLine();
        bw.close();
    }

    public static List<Usuario> leerUsuarios() throws IOException {
    List<Usuario> lista = new ArrayList<>();
    Scanner sc = new Scanner(new File("usuarios.csv"));

    while (sc.hasNextLine()) {
        String[] datos = sc.nextLine().split(",");
        lista.add(new Usuario(
        Integer.parseInt(datos[0]),
        datos[1],                    
        datos[2],                    
        Integer.parseInt(datos[3]),  
        Integer.parseInt(datos[4])   
    ));
    }
    sc.close();
    return lista;
    }

    public static List<Pedido> leerPedidos() throws IOException {
    List<Pedido> lista = new ArrayList<>();
    Scanner sc = new Scanner(new File("pedidos.csv"));

    while (sc.hasNextLine()) {

    String[] datos = sc.nextLine().split(",");

    lista.add(new Pedido(
        Integer.parseInt(datos[0]),  
        Integer.parseInt(datos[1]),  
        datos[2],                    
        Float.parseFloat(datos[3]),   
        Integer.parseInt(datos[4]),   
        Integer.parseInt(datos[5])   
    ));
}
    sc.close();
    return lista;
    }

    public static void eliminarUsuario(int id) throws IOException {
    List<Usuario> lista = leerUsuarios();
    BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv"));
    for (Usuario u : lista) {
        if (u.getId() == id) {
            u.setEstado(0);
        }
        bw.write(u.toString());
        bw.newLine();

    }
    bw.close();
}
    public static void listarPedido(int id) throws IOException {
    List<Pedido> lista = leerPedidos();
    for (Pedido p : lista) {
        if (p.getId_cliente() == id) {
            System.out.println(p);
        }

    }
}
}