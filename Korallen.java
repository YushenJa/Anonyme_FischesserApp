/**
 * Die Klasse Korallen repräsentiert einen Leckerbissen vom Typ Korallen.
 * Sie erbt von der abstrakten Klasse LeckerbissenImpl.
 */
public class Korallen extends LeckerbissenImpl {
    /**
     * Erzeugt ein neues Korallen-Objekt mit dem angegebenen Namen, Nahrungstyp, Anzahl und Gewicht.
     *
     * @param name        der Name der Korallen
     * @param nahrungstyp der Nahrungstyp der Korallen
     * @param anzahl      die Anzahl der Korallen
     * @param gewicht     das Gewicht der Korallen
     */
    public Korallen(String name, Nahrungstyp nahrungstyp, int anzahl, int gewicht) {
        super(name, nahrungstyp, anzahl, gewicht);
    }
}