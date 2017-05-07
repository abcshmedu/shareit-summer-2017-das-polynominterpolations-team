package edu.hm.data;

/** Dies ist unsere Implementierung der Medium-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class Medium {
    /** Diese Variable enthält den Titel diese Mediums. */
    private String title;

    /** Ctor für ein Medium.
     * 
     * @param title
     *        Der Titel des Mediums */
    public Medium(String title) {
        this.title = title;
    }

    /** Getter für den Titel.
     * 
     * @return Liefert den Titel zurück */
    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Medium other = (Medium) obj;
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }
}
