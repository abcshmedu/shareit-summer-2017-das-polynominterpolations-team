package edu.hm.persistance;

/** Dies ist unsere Implementierung der Copy-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class TCopy {
    /** Diese Variable enthält eine Referenz auf das konkrete Medium, auf
     * welches diese Copy verweist. */
    private TMedium medium;

    /** Diese Variable enthält den Namen des Besitzers dieser Copy. */
    private String owner;

    /** Ctor für eine Copy.
     * 
     * @param owner
     *        Der Besitzer dieser Copy
     * @param medium
     *        Das Medium, auf welches diese Copy verweist */
    public TCopy(String owner, TMedium medium) {
        this.medium = medium;
        this.owner = owner;
    }

    /** Getter für das Medium.
     * 
     * @return Liefert das Medium zurück */
    public TMedium getMedium() {
        return medium;
    }

    /** Getter für den Besitzer.
     * 
     * @return Liefert den Besitzer zurück */
    public String getOwner() {
        return owner;
    }
}
