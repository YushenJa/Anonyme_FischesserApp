/**
 * Die Klasse Fisch repräsentiert einen Fisch mit verschiedenen Eigenschaften und Verhaltensweisen.
 */
public class Fisch {
    private String name;
    private Nahrungstyp nahrungstyp;
    private Esstyp esstyp;
    private int gewicht;
    private int maximalesGewicht;
    private int appetitGrenze;
    private String aktuellerZustand;

    /**
     * Erzeugt ein neues Fisch-Objekt mit den angegebenen Eigenschaften.
     *
     * @param name           der Name des Fisches
     * @param nahrungstyp    der Nahrungstyp des Fisches
     * @param esstyp         der Esstyp des Fisches
     * @param gewicht        das Gewicht des Fisches
     * @param appetitGrenze  die Appetitgrenze des Fisches
     */
    public Fisch(String name, Nahrungstyp nahrungstyp, Esstyp esstyp, int gewicht, int appetitGrenze) {
        this.name = name;
        this.nahrungstyp = nahrungstyp;
        this.esstyp = esstyp;
        this.gewicht = gewicht;
        this.appetitGrenze = appetitGrenze;
        this.maximalesGewicht = gewicht + appetitGrenze;
        this.aktuellerZustand = "hungrig";
    }

    /**
     * Gibt den Namen des Fisches zurück.
     *
     * @return der Name des Fisches
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt das Gewicht des Fisches zurück.
     *
     * @return das Gewicht des Fisches
     */
    public int getGewicht() {
        return gewicht;
    }

    /**
     * Gibt das maximale Gewicht des Fisches zurück.
     *
     * @return das maximale Gewicht des Fisches
     */
    public int getMaximalesGewicht() {
        return maximalesGewicht;
    }

    /**
     * Gibt den aktuellen Zustand des Fisches zurück.
     *
     * @return der aktuelle Zustand des Fisches
     */
    public String getAktuellerZustand() {
        return aktuellerZustand;
    }

    /**
     * Füttert den Fisch mit einem Leckerbissen und aktualisiert seinen Zustand und Gewicht.
     *
     * @param leckerbissen der Leckerbissen, der dem Fisch angeboten wird
     * @return true, wenn der Fisch erfolgreich gefüttert wurde, andernfalls false
     * @throws AppetitlosException wenn der Fisch nicht gefüttert werden kann
     */
    public boolean fuettern(Leckerbissen leckerbissen) throws AppetitlosException {
        if (aktuellerZustand.equals("hungrig")) {
            if (leckerbissen.getNahrungstyp() == nahrungstyp && leckerbissen.getGewicht() <= maximalesGewicht - gewicht) {
                int anzahl = Math.min(leckerbissen.getAnzahl(), appetitGrenze / leckerbissen.getGewicht());
                gewicht += anzahl * leckerbissen.getGewicht();
                leckerbissen.setAnzahl(leckerbissen.getAnzahl() - anzahl);
                if (gewicht >= maximalesGewicht) {
                    aktuellerZustand = "satt";
                } else if (gewicht + 1 == maximalesGewicht) {
                    aktuellerZustand = "fast satt";
                }
                return true;
            } else {
                if (leckerbissen.getName().equals("Taucher")) {
                    throw new AppetitlosException("Der Fisch kann Menschen nicht fressen.");
                } else {
                    throw new AppetitlosException("Der Fisch kann diesen Leckerbissen nicht fressen.");
                }
            }
        }
        return false;
    }
}