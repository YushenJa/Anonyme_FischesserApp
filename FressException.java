/**
 * Die Klasse AppetitlosException repräsentiert eine Ausnahme, die geworfen wird,
 * wenn ein Fisch keinen Appetit hat.
 * Sie erbt von der Klasse Exception.
 */
class AppetitlosException extends Exception {
    /**
     * Erzeugt eine neue AppetitlosException mit der angegebenen Fehlermeldung.
     *
     * @param message die Fehlermeldung, die die Ausnahme beschreibt
     */
    public AppetitlosException(String message) {
        super(message);
    }
}