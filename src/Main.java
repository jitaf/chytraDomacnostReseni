import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DomaciAsistent asistent = new DomaciAsistent(scanner);

        while (true) {
            System.out.println("***************************************************************");
            System.out.println("\n--- Domácí Asistent Menu ---");
            System.out.println("1. Přidat nové zařízení");
            System.out.println("2. Odebrat zařízení");
            System.out.println("3. Vypisovat zařízení");
            System.out.println("4. Zapnout všechna zařízení");
            System.out.println("5. Vypnout všechna zařízení");
            System.out.println("6. Přehrát na všech službách");
            System.out.println("7. Ovládání termostatu");
            System.out.println("8. Vypisovat zapnutá zařízení a aktivní služby");
            System.out.println("9. Změnit název zařízení");
            System.out.println("10. Vypisovat statistiky");
            System.out.println("11. Konec");
            System.out.println("***************************************************************");
            System.out.print("Vyberte možnost: ");

            int volba = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (volba) {
                case 1:
                    asistent.pridejZarizeni();
                    break;
                case 2:
                    asistent.odeberZarizeni();
                    break;
                case 3:
                    asistent.vypisZarizeni();
                    break;
                case 4:
                    asistent.zapniVse();
                    break;
                case 5:
                    asistent.vypniVse();
                    break;
                case 6:
                    asistent.prehratNaVsechSluzbach();
                    break;
                case 7:
                    asistent.ovladaniTermostatu();
                    break;
                case 8:
                    asistent.vypisZapnutaZarizeniASluzby();
                    break;
                case 9:
                    asistent.zmenNazevZarizeni();
                    break;
                case 10:
                    asistent.vypisStatistiky();
                    break;
                case 11:
                    System.out.println("Konec programu.");
                    return;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
            }
            System.out.println("______________________________________________________________");
        }
    }
}