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
                int opc= entrada.nextInt();

                if (opc == 1){
                    u.agregarSaldo();
                }


                if (opc == 2) {
                    u.verDeudas();
                }

                if (opc == 3){
                    u.agregarDeuda();
                }

                if (opc == 4) {
                    u.pagarDeuda();
                }

                if (opc == 5) {
                    u.verServicios();
                }

                if (opc == 6) {
                    u.agregarServicio();
                }

                if (opc == 7){
                    u.pagar_servicio();
                }

                if (opc == 8) {
                    u.verTransacciones();
                }
                if (opc == 9) {
                    u.verSaldo();
                }
                if (opc == 10) {
                    System.out.println("Fue un placer atenderte espero que vuelvas pronto!");
                    break;
                }

                if (opc > 10 || opc <1){
                    throw new financieraException("\nEsta opcion no es valida en este menu\n");
                }


            }catch(NumberFormatException e){
                System.out.println("Error no se pueden ingresar letras!");

            }

            }



    }




}
