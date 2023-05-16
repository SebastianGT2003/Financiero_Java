// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws financieraException {
        menu m = new menu();
        usuario u = new usuario("Sebastian", "123", 2000000);
        m.Menu(u);
    }
}