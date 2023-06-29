
/**
 * Die Klasse Seetang repräsentiert einen Leckerbissen vom Typ Seetang.
 * Seetang ist eine Unterklasse von LeckerbissenImpl.
 */
public class Seetang extends LeckerbissenImpl {
    /**
     * Erzeugt ein neues Seetang-Objekt mit den angegebenen Eigenschaften.
     *
     * @param name        der Name des Seetangs
     * @param nahrungstyp der Nahrungstyp des Seetangs
     * @param anzahl      die Anzahl des Seetangs
     * @param gewicht     das Gewicht des Seetangs
     */
    public Seetang(String name, Nahrungstyp nahrungstyp, int anzahl, int gewicht) {
        super(name, nahrungstyp, anzahl, gewicht);
    }
}