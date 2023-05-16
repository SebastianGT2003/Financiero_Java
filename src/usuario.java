import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class usuario {
    public final String nombre;
    public final String cedula;
    public int saldo;
    public List<transaccion> transacciones = new ArrayList<>();
    ;
    public List<deuda> deudas = new ArrayList<>();;
    public List<servicio> servicios = new ArrayList<>();;
    public int total_servicios;
    public int total_deudas;
    public int total_transacciones;


    public usuario(String nombre, String cedula, int saldo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.saldo = saldo;
    }

    public void verSaldo(){
            System.out.println("\n Su saldo actual es " + this.saldo + " pesos \n");
    }

    public void agregarSaldo(){
        System.out.println();
        Scanner entrada = new Scanner(System.in);
        try{
            System.out.print("Ingrese saldo a agregar: ");
            int saldo=entrada.nextInt();
            while (saldo<0){
                System.out.println("El número ingresado debe ser mayor a cero");
                System.out.print("Ingrese saldo a agregar: ");
                saldo = entrada.nextInt();}
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            LocalDate fechaActual = fechaHoraActual.toLocalDate();
            this.saldo+=saldo;
            total_transacciones += 1;
            transaccion new_transaccion= new transaccion(total_transacciones, fechaActual, saldo, "Ingresos");
            this.transacciones.add(new_transaccion);
            System.out.println("\nSaldo ingresado correctamente!!!");
            System.out.println();
            verSaldo();
            }

        catch(InputMismatchException e){
            System.out.println("Error: no se pueden ingresar letras \n");
        }
    }

    public void agregarDeuda(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        try {
            System.out.print("Ingrese el valor de la deuda: ");
            int valor = scanner.nextInt();
            while (valor<0){
                System.out.println("El número ingresado debe ser mayor a cero");
                System.out.print("Ingrese nuevamente el valor de la deuda: ");
                valor = scanner.nextInt();}
            scanner.nextLine();
            System.out.print("Ingrese descripcion de la deuda: ");
            String descripcion = scanner.nextLine();
            LocalDate fechaInicio = LocalDate.now();
            total_deudas += 1;
            System.out.print("Ingrese el porcentaje de interés mensual: ");
            int interes = scanner.nextInt();
            if (interes<0){
                System.out.println("El valor del interes ingresado debe ser igual o mayor a cero");
                System.out.print("Ingrese nuevamente el porcentaje: ");
                interes = scanner.nextInt();}
            deuda newDeuda = new deuda(total_deudas, valor, fechaInicio, descripcion, interes);
            this.deudas.add(newDeuda);
            System.out.println("\nDeuda agregada con éxito!!!!");
            System.out.println();
        }
         catch (InputMismatchException e) {
            System.out.println("Error: no se pueden ingresar letras \n");
        }
    }


    public void agregarServicio() throws financieraException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        try {
            System.out.print("Ingrese el valor del servicio: ");
            int valor = scanner.nextInt();
            while (valor<0){
                System.out.println("El número ingresado debe ser mayor a cero");
                System.out.print("Ingrese nuevamente el valor del servicio: ");
                valor = scanner.nextInt();}
            scanner.nextLine();
            System.out.print("Ingrese el nombre del servicio: ");
            String nombre = scanner.nextLine();
            total_servicios += 1;
            servicio newServicio = new servicio(valor, nombre, total_servicios);
            servicios.add(newServicio);
            System.out.println("\nServicio agregado con éxito!!!!");
            System.out.println();

        } catch (InputMismatchException e) {
            System.out.println("Error: no se pueden ingresar letras \n");
        }
    }



    public void pagarDeuda(){
        verDeudas();
        if (deudas.isEmpty()) {
            return;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el id de la deuda: ");
            int id = scanner.nextInt();
            for (deuda deuda : deudas) {
                if (deuda.id == id) {
                    if (deuda.valor > saldo) {
                        System.out.println("Saldo insuficiente");
                        break;
                    }
                    saldo -= deuda.valor;
                    transaccion newTransaccion = new transaccion(transacciones.size() + 1, LocalDate.now(), -deuda.valor, deuda.descripcion);
                    transacciones.add(newTransaccion);
                    deudas.remove(id - 1);
                    System.out.println("\nDeuda pagada con exito\n");
                    break;
                }
            }
            System.out.println("\nNo existe una deuda con ese ID\n");
        }
    }

    public void pagar_servicio() throws financieraException{
        verServicios();
        if (servicios.isEmpty()) {
            return;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el id del servicio: ");
            int id = scanner.nextInt();
            for (servicio servicio : servicios) {
                if (servicio.id == id) {
                    if (servicio.valor > saldo) {
                        System.out.println("Saldo insuficiente");
                        break;
                    }
                    saldo -= servicio.valor;
                    transaccion newTransaccion = new transaccion(transacciones.size() + 1, LocalDate.now(), -servicio.valor, servicio.nombre);
                    transacciones.add(newTransaccion);
                    servicios.remove(id - 1);
                    System.out.println("\nServicio pagado con exito!!!\n");
                    return;
                }
            }
            System.out.println("\nNo existe un servicio con ese ID\n");
        }
    }

    public void verDeudas(){
        System.out.println();
        if (deudas.isEmpty()) {
            System.out.println("No tienes deudas");
        } else {
            for (deuda deuda : deudas) {
                if (deuda != null) {
                    System.out.println(deuda);
                }
            }
        }
        System.out.println();
    }
    public void verServicios() throws financieraException{
        System.out.println();
        if (this.servicios.isEmpty()) {
            System.out.println("No tienes servicios por pagar");
        } else {
            for (servicio servicio : servicios) {
                if (servicio != null) {
                    System.out.println(servicio);
                }
            }
        }
        System.out.println();
    }

    public void verTransacciones() throws financieraException{
        System.out.println();
        if (transacciones.isEmpty()) {
            System.out.println("No has hecho transacciones ");
        } else {
            for (transaccion transaccion : transacciones) {
                if (transaccion != null) {
                    System.out.println(transaccion);
                }
            }
        }
        System.out.println();
    }

}

