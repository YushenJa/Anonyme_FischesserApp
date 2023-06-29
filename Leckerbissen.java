/**
 * Das Leckerbissen-Interface definiert Methoden, um Informationen über einen Leckerbissen abzurufen und zu setzen.
 */
public interface Leckerbissen {
    String getName();
    Nahrungstyp getNahrungstyp();
    int getAnzahl();
    int getGewicht();
    void setAnzahl(int anzahl);
}