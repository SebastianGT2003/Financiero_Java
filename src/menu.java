import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

    public void Menu(usuario u)throws financieraException{

        while (true) {

            Scanner entrada = new Scanner(System.in);
            System.out.println("--------------  Gestor financiero  ----------------");
            System.out.println("1. Agregar saldo");
            System.out.println("2. Ver deudas");
            System.out.println("3. Agregar deuda");
            System.out.println("4. Pagar deuda");
            System.out.println("5. Ver servicios");
            System.out.println("6. Agregar servicio");
            System.out.println("7. Pagar servicio");
            System.out.println("8. Ver transacciones");
            System.out.println("9. Ver saldo");
            System.out.println("10. Salir");

            try {
                System.out.print("Ingrese la accion que desea realizar: ");
                String opc= entrada.nextLine();

                if (opc.equals("1")){
                    u.agregarSaldo();
                }


                if (opc.equals("2")) {
                    u.verDeudas();
                }

                if (opc.equals("3")){
                    u.agregarDeuda();
                }

                if (opc.equals("4")) {
                    u.pagarDeuda();
                }

                if (opc.equals("5")) {
                    u.verServicios();
                }

                if (opc.equals("6")) {
                    u.agregarServicio();
                }

                if (opc.equals("7")){
                    u.pagar_servicio();
                }

                if (opc.equals("8")) {
                    u.verTransacciones();
                }
                if (opc.equals("9")) {
                    u.verSaldo();
                }
                if (opc.equals("10")) {
                    System.out.println("Fue un placer atenderte espero que vuelvas pronto!");
                    break;
                }

                if (Integer.parseInt(opc) > 10 || Integer.parseInt(opc) < 1){
                    throw new financieraException("\nEsta opcion no es valida en este menu\n");
                }


            }catch(InputMismatchException e){
                System.out.println("\nError no se pueden ingresar letras!\n");
            }

            }



    }




}
