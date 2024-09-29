public class Netflix implements IStreamingService {
    private boolean prehravani;

    @Override
    public void prehrat(String nazevTitulu) {
        prehravani = true;
        System.out.println("Přehrávání na Netflixu: " + nazevTitulu);
    }

    @Override
    public void pauza() {
        prehravani = false;
        System.out.println("Netflix přehrávání pozastaveno.");
    }

    @Override
    public void stop() {
        prehravani = false;
        System.out.println("Netflix přehrávání ukončeno.");
    }
}

