/**
 * Třída SmartLight implementuje rozhraní ISmartDevice a poskytuje
 * konkrétní implementace metod pro zapnutí, vypnutí a zjištění stavu chytrého světla.
 */
class SmartLight implements ISmartDevice {
    private String nazev;
    private boolean zapnuto;

    private int pocetSpusteni;

    /**
     * Vytvoří nové chytré světlo s daným názvem.
     *
     * @param nazev Název chytrého světla.
     */
    public SmartLight(String nazev) {
        this.nazev = nazev;
        this.zapnuto = false;
    }

    public void setNazev(String novyNazev) {
        this.nazev = nazev;
    }

    /**
     * Zapne chytré světlo.
     */
    @Override
    public void zapni() {
        zapnuto = true;
        pocetSpusteni++;
        System.out.println(nazev + " je zapnuto.");
    }

    /**
     * Vypne chytré světlo.
     */
    @Override
    public void vypni() {
        zapnuto = false;
        System.out.println(nazev + " je vypnuto.");
    }

    /**
     * Vrátí aktuální stav chytrého světla.
     *
     * @return Řetězec reprezentující aktuální stav světla.
     */
    @Override
    public String stav() {
        if (zapnuto) {
            return "zapnuto";
        } else {
            return "vypnuto";
        }
    }

    public int getPocetSpusteni() {
        return pocetSpusteni;
    }

    @Override
    public String toString(){
        return "Zarizeni SmartLight s nazvem "  + nazev + " je " + this.stav();
    }


}
