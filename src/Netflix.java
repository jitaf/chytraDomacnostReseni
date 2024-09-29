public class Netflix implements IStreamingService {
    private boolean prehravani;
    private int pocetSpusteni;
    @Override
    public void prehrat(String nazevTitulu) {
        prehravani = true;
        pocetSpusteni++;
        System.out.println("Přehrávání na Netflixu: " + nazevTitulu);
    }

      @Override
    public void stop() {
        prehravani = false;
        System.out.println("Netflix přehrávání ukončeno.");
    }

    @Override
    public boolean prehrava() {
        return prehravani;
    }

    public int getPocetSpusteni() {
        return pocetSpusteni;
    }

    @Override
    public String toString(){
        return "Sluzba Netflix.";
    }
}

