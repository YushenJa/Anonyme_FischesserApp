/**
 * Die abstrakte Klasse LeckerbissenImpl implementiert das Leckerbissen-Interface
 * und stellt eine grundlegende Implementierung gemeinsamer Methoden bereit.
 */
public abstract class LeckerbissenImpl implements Leckerbissen {
    private String name;
    private Nahrungstyp nahrungstyp;
    private int anzahl;
    private int gewicht;

    /**
     * Konstruktor für die Klasse LeckerbissenImpl.
     *
     * @param name       der Name des Leckerbissens
     * @param nahrungstyp der Nahrungstyp des Leckerbissens
     * @param anzahl     die Anzahl des Leckerbissens
     * @param gewicht    das Gewicht des Leckerbissens
     */
    public LeckerbissenImpl(String name, Nahrungstyp nahrungstyp, int anzahl, int gewicht) {
        this.name = name;
        this.nahrungstyp = nahrungstyp;
        this.anzahl = anzahl;
        this.gewicht = gewicht;
    }

	@Override
    public String getName() {
        return name;
    }

    @Override
    public Nahrungstyp getNahrungstyp() {
        return nahrungstyp;
    }

    @Override
    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public int getGewicht() {
        return gewicht;
    }

    @Override
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
