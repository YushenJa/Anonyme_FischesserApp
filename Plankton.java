
/**
 * Die Klasse Plankton repräsentiert einen Leckerbissen vom Typ Plankton.
 * Sie erbt von der abstrakten Klasse LeckerbissenImpl.
 */
public class Plankton extends LeckerbissenImpl {
    /**
     * Erzeugt ein neues Plankton-Objekt mit dem angegebenen Namen, Nahrungstyp, Anzahl und Gewicht.
     *
     * @param name        der Name des Planktons
     * @param nahrungstyp der Nahrungstyp des Planktons
     * @param anzahl      die Anzahl des Planktons
     * @param gewicht     das Gewicht des Planktons
     */
    public Plankton(String name, Nahrungstyp nahrungstyp, int anzahl, int gewicht) {
        super(name, nahrungstyp, anzahl, gewicht);
    }
}