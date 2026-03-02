public class Pedido{
    private int id_pedido;
    private int id_cliente;
    private String producto;
    private float precio;
    private int cantidad;
    private int activo;

    public Pedido(int id_pedido, int id_cliente, String producto, float precio, int cantidad, int activo){
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.activo = activo;
    }
    
    public int getId_cliente(){return id_cliente;}
    @Override
    public String toString(){
        return id_pedido + "," + id_cliente + "," + producto + "," + precio + "," + cantidad + "," + activo;
    }
}