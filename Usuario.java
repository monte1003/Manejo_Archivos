public class Usuario {
    private int id;
    private String nombre;
    private String email;

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {return id;}
    
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setEmail(String email){this.email = email;}

    @Override
    public String toString() {
        return id + "," + nombre + "," + email;
    }
}