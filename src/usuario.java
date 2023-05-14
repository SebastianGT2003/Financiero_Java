import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class usuario {
    public final String nombre;
    public final String cedula;
    public int saldo;
    public List<transaccion> transaciones;
    public List<deuda> deudas;

    public List<servicio> servicios;


    public usuario(String nombre, String cedula, int saldo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.saldo = saldo;


    }

    public void verSaldo() {
        System.out.println("\n Su saldo actual es " + this.saldo + " pesos \n");
    }

    public void agregarSaldo() throws financieraException{
        System.out.println("\n");
        Scanner entrada = new Scanner(System.in);
        try{
            System.out.println("Ingrese saldo a agregar: ");

            int saldo=entrada.nextInt();

            if (saldo<0){
                throw new financieraException("El número ingresado debe ser mayor o igual a cero");
            }
            else{
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                LocalDate fechaActual = fechaHoraActual.toLocalDate();
                this.saldo+=saldo;
                transaccion new_transaccion= new transaccion((this.transaciones.size() + 1), fechaActual, saldo, "Ingresos");
                this.transaciones.add(new_transaccion);
                System.out.println("Saldo ingresado correctamente");
                System.out.println("\n");
                System.out.println(saldo);

            }

        }catch(NumberFormatException e){
            System.out.println("Error no se pueden ingresar letras!");

        }

    }

    /*public void verServicios(){
        System.out.println("\n");
        if (this.servicios.isEmpty()){
                System.out.println("No tienes ningun servicio");
        }
        else{
            for (servicio servicio:this.servicios) {
                if (servicio!= 0){
                    System.out.println(servicio);
                }
            }
        }

    }

     */

}

