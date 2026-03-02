import java.io.*;

public class CRUD {

    private static final String FILE_NAME = "usuarios.txt";

    // CREATE
    public static void crearUsuario(Usuario usuario) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(usuario.toString());
        bw.newLine();
        bw.close();
        System.out.println("Usuario creado exitosamente.");
    }

    // READ
    public static void leerUsuario(int id) throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No existen usuarios registrados.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        boolean encontrado = false;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (Integer.parseInt(datos[0]) == id) {
                System.out.println("Usuario encontrado: " + linea);
                encontrado = true;
                break;
            }
        }

        br.close();

        if (!encontrado) {
            System.out.println("Usuario no encontrado.");
        }
    }

    // UPDATE
    public static void actualizarUsuario(Usuario usuarioActualizado) throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No existen usuarios registrados.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        StringBuilder sb = new StringBuilder();
        boolean actualizado = false;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (Integer.parseInt(datos[0]) == usuarioActualizado.getId()) {
                sb.append(usuarioActualizado.toString()).append("\n");
                actualizado = true;
            } else {
                sb.append(linea).append("\n");
            }
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(sb.toString());
        bw.close();

        if (actualizado) {
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    // DELETE
    public static void eliminarUsuario(int id) throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No existen usuarios registrados.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        StringBuilder sb = new StringBuilder();
        boolean eliminado = false;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (Integer.parseInt(datos[0]) == id) {
                eliminado = true;
            } else {
                sb.append(linea).append("\n");
            }
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(sb.toString());
        bw.close();

        if (eliminado) {
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}