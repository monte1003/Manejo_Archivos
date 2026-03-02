public class Usuario{
    private int id;
    private String nombre;
    private String apellido;
    private int telefono;
    private int activo;

    public Usuario(int id, String nombre, String apellido, int telefono, int activo){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.activo = activo;
    }

    public void setEstado(int activo){this.activo = activo;}
    public int getId(){return id;}
    @Override
    public String toString(){
        return id + "," + nombre + "," + apellido + "," + telefono + "," + activo;
    }
}