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

    public static List<Usuario> leerUsuarios() throws IOException {
    List<Usuario> lista = new ArrayList<>();
    Scanner sc = new Scanner(new File("clientes.csv"));

    while (sc.hasNextLine()) {
        String[] datos = sc.nextLine().split(",");
        lista.add(new Usuario(
            Integer.parseInt(datos[0]),
            datos[1],
            datos[2]));
    }
    sc.close();
    return lista;
    }

    public static void actualizarUsuario(int id, String nuevoNombre, String nuevoEmail) throws IOException {

    List<Usuario> lista = leerUsuarios();
    BufferedWriter bw = 
        new BufferedWriter(new FileWriter("clientes.csv"));

    for (Usuario u : lista) {
        if (u.getId() == id) {
            u.setNombre(nuevoNombre);
            u.setEmail(nuevoEmail);
        }
        bw.write(u.toString());
        bw.newLine();
    }
    bw.close();
    }

    public static void eliminarUsuario(int id) 
throws IOException {

    List<Usuario> lista = leerUsuarios();
    BufferedWriter bw = 
        new BufferedWriter(new FileWriter("clientes.csv"));

    for (Usuario u : lista) {
        if (u.getId() != id) {
            bw.write(u.toString());
            bw.newLine();
        }
    }
    bw.close();
}
}
