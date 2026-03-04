public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private long telefono;
    private int activo;


    private int id_pedido;
    private int id_cliente;
    private String producto;
    private double precio;
    private int cantidad;

    public Usuario(int id, String nombre, String apellido, long telefono, int activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.activo = activo;
    }

    public Usuario(int id_pedido, int id_cliente, String producto, double precio, int cantidad, int activo) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdCliente() {
        return id_cliente;
    }

    public String toCSVCliente() {
        return id + "," + nombre + "," + apellido + "," + telefono + "," + activo;
    }

    public String toCSVPedido() {
        return id_pedido + "," + id_cliente + "," + producto + "," + precio + "," + cantidad + "," + activo;
    }
}