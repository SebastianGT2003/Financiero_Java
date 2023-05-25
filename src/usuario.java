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
    public List<deuda> deudas = new ArrayList<>();
    public List<servicio> servicios = new ArrayList<>();
    public int total_servicios;
    public int total_deudas;
    public int total_transacciones;
    boolean valorAdecuado = false;

    public usuario(String nombre, String cedula, int saldo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.saldo = saldo;
    }

    public void verSaldo(){
            System.out.println("\nSu saldo actual es " + this.saldo + " pesos \n");
    }

    public void agregarSaldo() {
        System.out.println();
        int saldo = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese saldo a agregar: ");
        saldo = Evaluar(valorAdecuado, saldo, scanner);
        while (saldo <= 0) {
            System.out.print("El valor debe ser mayor a cero, ingrese nuevamente el valor: ");
            saldo = Evaluar(valorAdecuado, saldo, scanner);
        }
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDate fechaActual = fechaHoraActual.toLocalDate();
        this.saldo += saldo;
        total_transacciones += 1;
        transaccion new_transaccion = new transaccion(total_transacciones, fechaActual, saldo, "Ingresos");
        this.transacciones.add(new_transaccion);
        System.out.println("\nSaldo ingresado correctamente!!!");
        verSaldo();
    }


    public void agregarDeuda() {
        int valor = 5;
        int interes = 5;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el valor de la deuda: ");
        valor = Evaluar(valorAdecuado, valor, scanner);
        scanner.nextLine();
        System.out.print("Ingrese descripcion de la deuda: ");
        String descripcion = scanner.nextLine();
        LocalDate fechaInicio = LocalDate.now();
        System.out.print("Ingrese el porcentaje de interés mensual: ");
        interes = Evaluar(valorAdecuado, interes, scanner);
        while (interes < 0) {
            System.out.print("El valor debe ser positivo, ingrese nuevamente el valor: ");
            interes = Evaluar(valorAdecuado, interes, scanner);
        }
        total_deudas += 1;
        deuda newDeuda = new deuda(total_deudas, valor, fechaInicio, descripcion, interes);
        this.deudas.add(newDeuda);
        System.out.println("\nDeuda agregada con éxito!!!!");
        System.out.println();
    }

    static int Evaluar(boolean valorAdecuado, int valor, Scanner scanner) {
        while(!valorAdecuado) {
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                valorAdecuado = true; // Valor adecuado, salir del bucle
            } else {
                System.out.print("El valor debe ser un numero, ingrese nuevamente la opcion:");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
        return valor;
    }

    public void agregarServicio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int valor = 0;
        System.out.print("Ingrese el valor del servicio: ");
        valor = Evaluar(valorAdecuado, valor, scanner);
        while (valor <= 0) {
            System.out.print("El valor debe ser positivo, ingrese nuevamente el valor: ");
            valor = Evaluar(valorAdecuado, valor, scanner);
        }
        scanner.nextLine();
        System.out.print("Ingrese el nombre del servicio: ");
        String nombre = scanner.nextLine();
        total_servicios += 1;
        servicio newServicio = new servicio(valor, nombre, total_servicios);
        servicios.add(newServicio);
        System.out.println("\nServicio agregado con éxito!!!");
        System.out.println();
    }

    public void pagarDeuda(){
        verDeudas();
        int id = 0;
        if (deudas.isEmpty()) {
            return;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el id de la deuda: ");
            id = Evaluar(valorAdecuado, id, scanner);
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
        int id = 0;
        if (servicios.isEmpty()) {
            return;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el id del servicio: ");
            id = Evaluar(valorAdecuado, id, scanner);
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
    public void verServicios(){
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

    public void verTransacciones(){
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

