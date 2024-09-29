import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Třída DomaciAsistent spravuje seznam chytrých zařízení a streamovacích služeb.
 * Umožňuje přidávat zařízení a služby, zapínat a vypínat všechna zařízení,
 * a přehrávat obsah na všech streamovacích službách.
 */
public class DomaciAsistent {
    private List<ISmartDevice> zarizeni = new ArrayList<>();
    private List<IStreamingService> sluzby = new ArrayList<>();
    private Scanner scanner;

    public DomaciAsistent(Scanner scanner){
        this.scanner = scanner;
        sluzby.add(new Netflix());
        sluzby.add(new Spotify());
        sluzby.add(new Disney());
    }

    /**
     * Přidá chytré zařízení do seznamu spravovaných zařízení.
     *
     */
    public void pridejZarizeni() {
        System.out.println("Vyberte typ zařízení:");
        System.out.println("1. SmartLight");
        System.out.println("2. SmartThermostat");
        int typ = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Zadejte název zařízení: ");
        String nazev = scanner.nextLine();

        switch (typ) {
            case 1:
                zarizeni.add(new SmartLight(nazev));
                break;
            case 2:
                System.out.print("Zadejte počáteční teplotu: ");
                double teplota = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                zarizeni.add(new SmartThermostat(nazev, teplota));
                break;
            default:
                System.out.println("Neplatný typ zařízení.");
        }
    }

    /**
     * Odebere chytré zařízení ze seznamu spravovaných zařízení.
     */
    public void odeberZarizeni() {
        System.out.println("Aktualne mate tyto zarizeni: ");
        for (ISmartDevice device: zarizeni) {
            System.out.print(device.toString() + ", ");
        }
        System.out.println();

        System.out.println("Zadejte název zařízení, které chcete odebrat:");
        String nazev = scanner.nextLine();
        for (int i = 0; i < zarizeni.size(); i++) {
            if (zarizeni.get(i).stav().equals(nazev)) {
                zarizeni.remove(i);
                System.out.println("Zařízení " + nazev + " bylo odebráno.");
                return;
            }
        }
        System.out.println("Zařízení s názvem " + nazev + " nebylo nalezeno.");
    }

    /**
     * Vypíše všechna spravovaná chytrá zařízení.
     */
    public void vypisZarizeni() {
        System.out.println("Seznam spravovaných zařízení:");
        for (ISmartDevice z : zarizeni) {
            System.out.println(z.toString());
        }
    }

    /**
     * Přidá streamovací službu do seznamu spravovaných služeb.
     *
     * @param sluzba Streamovací služba, která má být přidána.
     */
    public void pridejSluzbu(IStreamingService sluzba) {
        this.sluzby.add(sluzba);
    }

    /**
     * Zapne všechna spravovaná chytrá zařízení.
     */
    public void zapniVse() {
        for (ISmartDevice z : zarizeni) {
            z.zapni();
        }
    }

    /**
     * Vypne všechna spravovaná chytrá zařízení.
     */
    public void vypniVse() {
        for (ISmartDevice z : zarizeni) {
            z.vypni();
        }
    }

    /**
     * Přehrává zadaný obsah na všech spravovaných streamovacích službách.
     */
    public void prehratNaVsechSluzbach() {
        System.out.print("Zadejte název obsahu, který chcete přehrát: ");
        String nazev = scanner.nextLine();
        for (IStreamingService s : sluzby) {
            s.prehrat(nazev);
        }
    }

    /**
     * Ovládá chytrý termostat podle zadaného názvu a umožňuje nastavit novou teplotu.
     * Pokud termostat s daným názvem není nalezen, zobrazí chybovou zprávu.
     */
    public void ovladaniTermostatu() {
        System.out.println("Aktualne mate tyto termostaty: ");
        for (ISmartDevice device: zarizeni) {
            if (device instanceof SmartThermostat){
                System.out.print(device.toString() + ", ");
            }
            System.out.println();
        }

        System.out.println("Zadejte název termostatu, který chcete ovládat:");
        String nazev = scanner.nextLine();
        for (ISmartDevice z : zarizeni) {
            if ((z instanceof SmartThermostat) && (z.stav().equals(nazev))) {
                System.out.print("Zadejte novou teplotu: ");
                double teplota = scanner.nextDouble();
                scanner.nextLine();
                ((SmartThermostat) z).nastavTeplotu(teplota);
                return;
            }
        }
        System.out.println("Termostat s názvem " + nazev + " nebyl nalezen.");
    }

    /**
     * Vypíše všechna zapnutá chytrá zařízení a všechny streamovací služby, které nejsou stopnute
     */
    public void vypisZapnutaZarizeniASluzby() {
        System.out.println("Zapnutá chytrá zařízení:");
        for (ISmartDevice z : zarizeni) {
            if (z.stav().equals("Zapnuto")) {
                System.out.println(z);
            }
        }

        System.out.println("Streamovací služby, které nejsou stopnuté:");
        for (IStreamingService s : sluzby) {
            if (!s.prehrava()) {
                System.out.println(s);
            }
        }
    }

    /**
     * Umožní uživateli změnit název existujícího chytrého zařízení.
     */
    public void zmenNazevZarizeni() {
        System.out.println("Zadejte aktuální název zařízení, které chcete přejmenovat:");
        String staryNazev = scanner.nextLine();
        for (ISmartDevice z : zarizeni) {
            if (z.stav().equals(staryNazev)) {
                System.out.print("Zadejte nový název zařízení: ");
                String novyNazev = scanner.nextLine();
                if (z instanceof SmartLight) {
                    ((SmartLight) z).setNazev(novyNazev);
                } else if (z instanceof SmartThermostat) {
                    ((SmartThermostat) z).setNazev(novyNazev);
                }
                System.out.println("Zařízení bylo přejmenováno na " + novyNazev);
                return;
            }
        }
        System.out.println("Zařízení s názvem " + staryNazev + " nebylo nalezeno.");
    }

    /**
     * Vypíše statistiky používání zařízení a služeb.
     */
    public void vypisStatistiky() {
        ISmartDevice nejvicePouzivaneZarizeni = null;
        IStreamingService nejvicePouzivanaSluzba = null;
        int soucetSpusteniZarizeni = 0;
        int soucetSpusteniSluzeb = 0;

        for (ISmartDevice z : zarizeni) {
            if (nejvicePouzivaneZarizeni == null || z.getPocetSpusteni() > nejvicePouzivaneZarizeni.getPocetSpusteni()) {
                nejvicePouzivaneZarizeni = z;
            }
            soucetSpusteniZarizeni += z.getPocetSpusteni();
        }

        for (IStreamingService s : sluzby) {
            if (nejvicePouzivanaSluzba == null || s.getPocetSpusteni() > nejvicePouzivanaSluzba.getPocetSpusteni()) {
                nejvicePouzivanaSluzba = s;
            }
            soucetSpusteniSluzeb += s.getPocetSpusteni();
        }


        System.out.print("Nejvíce používané zařízení: ");
        if (nejvicePouzivaneZarizeni != null) {
            System.out.println(nejvicePouzivaneZarizeni);
        } else {
            System.out.println("Žádné");
        }

        System.out.print("Nejvíce používaná služba: ");
        if (nejvicePouzivanaSluzba != null) {
            System.out.println(nejvicePouzivanaSluzba);
        } else {
            System.out.println("Žádná");
        }

        System.out.println("Celkový počet spuštění všech zařízení: " + soucetSpusteniZarizeni);
        System.out.println("Celkový počet spuštění všech služeb: " + soucetSpusteniSluzeb);
    }
}

