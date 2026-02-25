import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menú de opciones\n1) Crear usuario\n2) Leer usuarios\n3) Actualizar usuarios\n4) Eliminar Usuarios\n5)Salir\nIntroduzca la opción");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                System.out.println("Id:");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Email: ");
                String email = sc.nextLine();
                try{
                    CrudArchivos.crearUsuario(new Usuario(id, nombre, email));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
               
                break;
            case 2:
                try{
                    CrudArchivos.leerUsuarios();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.println("Id del archivo a actualizar:"); 
                id = sc.nextInt();
                System.out.println("Nombre actualizar: ");
                nombre = sc.nextLine();
                System.out.println("Email actualizar: ");
                email = sc.nextLine();
                try{
                    CrudArchivos.actualizarUsuario(id, nombre, email);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.println("Id del archivo a eliminar: "); 
                id = sc.nextInt();
                try{
                    CrudArchivos.eliminarUsuario(id);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case 5:

                break;
            default:
                break;
        }
        sc.close();
    }    
}
