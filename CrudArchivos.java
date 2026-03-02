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

        System.out.println("Cliente registrado correctamente.");
    }

    public static void listarClientes() throws IOException {

        File archivo = new File(ARCHIVO_CLIENTES);

        if (!archivo.exists()) {
            System.out.println("No existen clientes registrados.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        boolean hayRegistros = false;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (datos.length >= 5) {

                String estado;
                if (datos[4].trim().equals("1")) {
                    estado = "Activo";
                } else {
                    estado = "Inactivo";
                }

                System.out.println("ID: " + datos[0].trim() +
                        " | Nombre: " + datos[1].trim() + " " + datos[2].trim() +
                        " | Tel: " + datos[3].trim() +
                        " | Estado: " + estado);

                hayRegistros = true;

            } else {
                System.out.println("Linea con formato invalido detectada en clientes.csv.");
            }
        }

        br.close();

        if (!hayRegistros) {
            System.out.println("El archivo existe, pero no hay registros para mostrar.");
        }
    }

    public static void eliminarCliente(int id) throws IOException {

        File archivoClientes = new File(ARCHIVO_CLIENTES);

        if (!archivoClientes.exists()) {
            System.out.println("No existe el archivo de clientes.");
            return;
        }

        List<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivoClientes));
        String linea;
        boolean existe = false;
        boolean inactivadoAhora = false;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (datos.length >= 5) {

                boolean idValido = true;
                int idArchivo = 0;

                try {
                    idArchivo = Integer.parseInt(datos[0].trim());
                } catch (NumberFormatException e) {
                    idValido = false;
                    System.out.println("ID invalido en una linea de clientes.csv (se mantiene la linea).");
                }

                if (idValido) {

                    if (idArchivo == id) {
                        existe = true;

                        if (datos[4].trim().equals("1")) {
                            datos[4] = "0";
                            linea = String.join(",", datos);
                            inactivadoAhora = true;
                        }
                    }
                }

            } else {
                System.out.println("Linea con formato invalido detectada en clientes.csv (se mantiene la linea).");
            }

            lineas.add(linea);
        }

        br.close();

        if (!existe) {
            System.out.println("No existe un cliente con ese ID.");
            return;
        }

        if (!inactivadoAhora) {
            System.out.println("El cliente ya estaba inactivo.");
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoClientes));
        for (String l : lineas) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();

        desactivarPedidosCliente(id);

        System.out.println("Cliente y sus pedidos fueron desactivados correctamente.");
    }

    private static void desactivarPedidosCliente(int idCliente) throws IOException {

        File archivoPedidos = new File(ARCHIVO_PEDIDOS);

        if (!archivoPedidos.exists()) {
            return;
        }

        List<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivoPedidos));
        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (datos.length >= 6) {

                boolean idValido = true;
                int idClienteArchivo = 0;

                try {
                    idClienteArchivo = Integer.parseInt(datos[1].trim());
                } catch (NumberFormatException e) {
                    idValido = false;
                    System.out.println("ID de cliente invalido en una linea de pedidos.csv (se mantiene la linea).");
                }

                if (idValido) {

                    if (idClienteArchivo == idCliente) {

                        if (datos[5].trim().equals("1")) {
                            datos[5] = "0";
                            linea = String.join(",", datos);
                        }
                    }
                }

            } else {
                System.out.println("Linea con formato invalido detectada en pedidos.csv (se mantiene la linea).");
            }

            lineas.add(linea);
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPedidos));
        for (String l : lineas) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public static void registrarPedido(Usuario pedido) throws IOException {

        int idCliente = pedido.getIdCliente();

        String validacion = validarClienteParaPedido(idCliente);

        if (!validacion.equals("OK")) {
            System.out.println(validacion);
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PEDIDOS, true));
        bw.write(pedido.toCSVPedido());
        bw.newLine();
        bw.close();

        System.out.println("Pedido registrado correctamente.");
    }

    private static boolean clienteExisteYEstaActivo(int idCliente) throws IOException {

        File archivo = new File(ARCHIVO_CLIENTES);

        if (!archivo.exists()) {
            return false;
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        boolean encontradoActivo = false;
        boolean encontrado = false;

        while ((linea = br.readLine()) != null && !encontrado) {

            String[] datos = linea.split(",");

            if (datos.length >= 5) {

                boolean idValido = true;
                int idArchivo = 0;

                try {
                    idArchivo = Integer.parseInt(datos[0].trim());
                } catch (NumberFormatException e) {
                    idValido = false;
                }

                if (idValido) {

                    if (idArchivo == idCliente) {
                        encontrado = true;

                        if (datos[4].trim().equals("1")) {
                            encontradoActivo = true;
                        }
                    }
                }
            }
        }

        br.close();
        return encontradoActivo;
    }

    public static void listarPedidosPorCliente(int id_cliente) throws IOException {

        File archivo = new File(ARCHIVO_PEDIDOS);

        if (!archivo.exists()) {
            System.out.println("No existen pedidos registrados.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        boolean hayPedidosActivos = false;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (datos.length >= 6) {

                boolean idValido = true;
                int idClienteArchivo = 0;

                try {
                    idClienteArchivo = Integer.parseInt(datos[1].trim());
                } catch (NumberFormatException e) {
                    idValido = false;
                    System.out.println("ID de cliente invalido en una linea de pedidos.csv.");
                }

                if (idValido) {

                    if (idClienteArchivo == id_cliente && datos[5].trim().equals("1")) {

                        System.out.println("Pedido: " + datos[0].trim() +
                                " | Producto: " + datos[2].trim() +
                                " | Precio: " + datos[3].trim() +
                                " | Cantidad: " + datos[4].trim());

                        hayPedidosActivos = true;
                    }
                }

            } else {
                System.out.println("Linea con formato invalido detectada en pedidos.csv.");
            }
        }

        br.close();

        if (!hayPedidosActivos) {
            System.out.println("No hay pedidos activos para este cliente.");
        }
    }

    private static String validarClienteParaPedido(int idCliente) throws IOException {

        File archivo = new File(ARCHIVO_CLIENTES);

        if (!archivo.exists()) {
            return "No se puede registrar el pedido: no existe el archivo de clientes.";
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        boolean encontrado = false;
        boolean activo = false;

        while ((linea = br.readLine()) != null && !encontrado) {

            String[] datos = linea.split(",");

            if (datos.length >= 5) {

                boolean idValido = true;
                int idArchivo = 0;

                try {
                    idArchivo = Integer.parseInt(datos[0].trim());
                } catch (NumberFormatException e) {
                    idValido = false;
                }

                if (idValido) {

                    if (idArchivo == idCliente) {
                        encontrado = true;

                        if (datos[4].trim().equals("1")) {
                            activo = true;
                        }
                    }
                }
            }
        }

        br.close();

        if (!encontrado) {
            return "No se puede registrar el pedido: el cliente no existe.";
        }

        if (!activo) {
            return "No se puede registrar el pedido: el cliente esta inactivo.";
        }

        return "OK";
    }
}