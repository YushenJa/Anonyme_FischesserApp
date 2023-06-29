/**
 * Die Klasse Taucher repräsentiert einen Leckerbissen vom Typ Taucher.
 * Sie erbt von der abstrakten Klasse LeckerbissenImpl.
 */
public class Taucher extends LeckerbissenImpl {
    /**
     * Erzeugt ein neues Taucher-Objekt mit dem angegebenen Namen, Nahrungstyp, Anzahl und Gewicht.
     *
     * @param name        der Name des Tauchers
     * @param nahrungstyp der Nahrungstyp des Tauchers
     * @param anzahl      die Anzahl des Tauchers
     * @param gewicht     das Gewicht des Tauchers
     */
    public Taucher(String name, Nahrungstyp nahrungstyp, int anzahl, int gewicht) {
        super(name, nahrungstyp, anzahl, gewicht);
    }
}