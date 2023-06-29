import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Meer repräsentiert eine Simulation eines Meeres, in dem Fische und Leckerbissen existieren.
 * Sie liest Akteure und Szenarien aus Dateien und führt die Simulation entsprechend aus.
 */
public class Meer {
    private List<Fisch> fische;
    private List<Leckerbissen> leckerbissen;
    private int ausnahmeZaehler;
    private int erfolgreichGefressenZaehler;

    /**
     * Erzeugt eine neue Instanz von Meer.
     * Initialisiert die Listen für Fische und Leckerbissen sowie die Zähler für Ausnahmen und erfolgreich gefressene Leckerbissen.
     */
    public Meer() {
        fische = new ArrayList<>();
        leckerbissen = new ArrayList<>();
        ausnahmeZaehler = 0;
        erfolgreichGefressenZaehler = 0;
    }

    /**
     * Liest die Akteure aus einer Datei und fügt sie dem Meer hinzu.
     *
     * @param dateiName der Name der Datei, aus der die Akteure gelesen werden sollen
     */
    public void leseAkteure(String dateiName) {
        try {
            BufferedReader akteureReader = new BufferedReader(new FileReader(dateiName));
            String akteurLine;
            while ((akteurLine = akteureReader.readLine()) != null) {
                String[] split = akteurLine.split(", ");
                if (split.length == 6 && split[0].equals("<Fisch>")) {
                    String name = split[1];
                    Nahrungstyp nahrungstyp = Nahrungstyp.valueOf(split[2].trim());
                    Esstyp esstyp = Esstyp.valueOf(split[3].trim());
                    int gewicht = Integer.parseInt(split[4].trim());
                    int appetitGrenze = Integer.parseInt(split[5].trim());

                    Fisch fisch = new Fisch(name, nahrungstyp, esstyp, gewicht, appetitGrenze);
                    fische.add(fisch);
                } else if (split[0].equals("<Leckerbissen>")) {
                    int menge = Integer.parseInt(split[3].trim());
                    String name = split[1];
                    Nahrungstyp nahrungstyp = Nahrungstyp.valueOf(split[2].trim());
                    int gewicht = Integer.parseInt(split[4].trim());

                    Leckerbissen leckerbissen = erstelleLeckerbissen(name, nahrungstyp, menge, gewicht);
                    if (leckerbissen != null) {
                        this.leckerbissen.add(leckerbissen);
                    }
                }
            }
            akteureReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Erstellt einen Leckerbissen anhand der gegebenen Parameter.
     *
     * @param name        der Name des Leckerbissens
     * @param nahrungstyp der Nahrungstyp des Leckerbissens
     * @param anzahl      die Anzahl des Leckerbissens
     * @param gewicht     das Gewicht des Leckerbissens
     * @return eine Instanz von Leckerbissen entsprechend der gegebenen Parameter oder null, wenn der Name ungültig ist
     */
    private Leckerbissen erstelleLeckerbissen(String name, Nahrungstyp nahrungstyp, int anzahl, int gewicht) {
        if (name.equals("Seetang")) {
            return new Seetang(name, nahrungstyp, anzahl, gewicht);
        } else if (name.equals("Plankton")) {
            return new Plankton(name, nahrungstyp, anzahl, gewicht);
        } else if (name.equals("Korallen")) {
            return new Korallen(name, nahrungstyp, anzahl, gewicht);
        } else if (name.equals("Taucher")) {
            return new Taucher(name, nahrungstyp, anzahl, gewicht);
        }
        return null;
    }

    /**
     * Liest das Szenario aus einer Datei und führt die entsprechenden Aktionen aus.
     *
     * @param dateiName der Name der Datei, aus der das Szenario gelesen werden soll
     */
    public void leseSzene(String dateiName) {
        try (BufferedReader szeneReader = new BufferedReader(new FileReader(dateiName))) {
            String szeneLine;
            while ((szeneLine = szeneReader.readLine()) != null) {
                if (szeneLine.startsWith("<Text>")) {
                    System.out.println(szeneLine.substring(6));
                } else if (szeneLine.startsWith("[")) {
                    String[] split = szeneLine.substring(1, szeneLine.length() - 1).split(":");
                    String name = split[0];
                    String zustand = split[1];
                    Fisch fisch = findeFischNachName(name);
                    
                    if (zustand.equals("START_ZUSTAND") || zustand.equals("AKTUELE_ZUSTAND")) {
                    	zustand = fisch.getAktuellerZustand();
                    } 
                    
                    if (fisch != null) {
                        System.out.println("[" + fisch.getName() + ": " + zustand + " (" + fisch.getGewicht() + "/" + fisch.getMaximalesGewicht() + ")]");
                    } else {
                        Leckerbissen beute = findeLeckerbissenNachName(name);
                        if (beute != null) {
                            System.out.println("[" + beute.getName() + ": " + beute.getAnzahl() + ", " + beute.getGewicht() + "]");
                        }
                    }
                } else if (szeneLine.startsWith("<AKTION>")) {
                    String aktion = szeneLine.substring(8);
                    String[] split = aktion.split(" ");
                    String fischName = split[1];
                    String leckerbissenName = split[3];

                    Fisch fisch = findeFischNachName(fischName);
                    Leckerbissen beute = findeLeckerbissenNachName(leckerbissenName);

                    if (fisch != null && beute != null) {
                        try {
                            boolean erfolgreich = fisch.fuettern(beute);
                            if (erfolgreich) {
                                erfolgreichGefressenZaehler++;
                                System.out.println("[" + fisch.getName() + ": " + fisch.getAktuellerZustand() + " (" + fisch.getGewicht() + "/" + fisch.getMaximalesGewicht() + ")]");
                                System.out.println("[" + beute.getName() + ": " + beute.getAnzahl() + ", " + beute.getGewicht() + "]");
                            } else {
                                ausnahmeZaehler++;
                                System.out.println("Fehler: " + aktion);
                            }
                        } catch (AppetitlosException e) {
                            ausnahmeZaehler++;
                            System.out.println("Fehler: " + fisch.getName() + e.getMessage());
                        }
                    } else {
                        System.out.println("Fehler: " + aktion);
                    }
                } else if (szeneLine.startsWith("")) {
                	System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sucht einen Fisch in der Liste der Fische anhand seines Namens.
     *
     * @param name der Name des gesuchten Fisches
     * @return eine Instanz von Fisch, die dem gesuchten Namen entspricht, oder null, wenn kein solcher Fisch gefunden wurde
     */
    private Fisch findeFischNachName(String name) {
        for (Fisch fisch : fische) {
            if (fisch.getName().equals(name)) {
                return fisch;
            }
        }
        return null;
    }

    /**
     * Sucht einen Leckerbissen in der Liste der Leckerbissen anhand seines Namens.
     *
     * @param name der Name des gesuchten Leckerbissens
     * @return eine Instanz von Leckerbissen, die dem gesuchten Namen entspricht, oder null, wenn kein solcher Leckerbissen gefunden wurde
     */
    private Leckerbissen findeLeckerbissenNachName(String name) {
        for (Leckerbissen leckerbissen : leckerbissen) {
            if (leckerbissen.getName().equals(name)) {
                return leckerbissen;
            }
        }
        return null;
    }

    /**
     * Zeigt die Statistik der Simulation an, einschließlich der Anzahl der Ausnahmen und der erfolgreich gefressenen Leckerbissen.
     */
    public void zeigeStatistik() {
        System.out.println("Anzahl der Ausnahmen: " + ausnahmeZaehler);
        System.out.println("Erfolgreich gefressene Leckerbissen: " + erfolgreichGefressenZaehler);
    }

    /**
     * Die Hauptmethode der Simulation.
     * Erzeugt eine Instanz von Meer, liest die Akteure und das Szenario aus den angegebenen Dateien und zeigt anschließend die Statistik an.
     *
     * @param args die Kommandozeilenargumente
     */
    public static void main(String[] args) {
        Meer meer = new Meer();
        meer.leseAkteure("Akteuren.txt");
        meer.leseSzene("Szenario.txt");
        meer.zeigeStatistik();
    }
}