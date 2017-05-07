package edu.hm.data;

/** Dies ist unsere Implementierung der Disc-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class Disc extends Medium {
    /** Diese Variable enthält den Barcode dieser Disc. */
    private String barcode;

    /** Diese Variable enthält den Director dieser Disc. */
    private String director;

    /** Diese Variable enthält das Alter, ab welchem diese Disc freigegeben
     * ist. */
    private int fsk;

    /** Default Ctor für eine Disc. */
    public Disc() {
        this("", "", "", 0);
    }

    /** Ctor für eine Disc.
     * 
     * @param title
     *        Der Titel der Disc
     * @param director
     *        Der Director der Disc
     * @param barcode
     *        Der Barcode der Disc
     * @param fsk
     *        Das Alter, ab dem diese Disc freigegeben ist */
    public Disc(String title, String director, String barcode, int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    /** Getter für den Barcode.
     * 
     * @return Liefert den Barcode zurück. */
    public String getBarcode() {
        return barcode;
    }

    /** Getter für den Director.
     * 
     * @return Liefert den Director zurück */
    public String getDirector() {
        return director;
    }

    /** Getter für das FSK-Alter.
     * 
     * @return Liefert das FSK-Alter zurück */
    public int getFsk() {
        return fsk;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        result = prime * result + fsk;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Disc other = (Disc) obj;
        if (barcode == null) {
            if (other.barcode != null) {
                return false;
            }
        } else if (!barcode.equals(other.barcode)) {
            return false;
        }
        if (director == null) {
            if (other.director != null) {
                return false;
            }
        } else if (!director.equals(other.director)) {
            return false;
        }
        if (fsk != other.fsk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return director + barcode + fsk;
    }
}