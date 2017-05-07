package edu.hm.data;

/** Dies ist unsere Implementierung des Buches.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class Book extends Medium {
    /** Diese Variable enthält den Autor des Buches. */
    private String author;

    /** Diese Variable enthält die ISBN des Buches. */
    private String isbn;

    /** Default Ctor für ein Book. */
    public Book() {
        this("", "", "");
    }

    /** Ctor für ein Buch-Objekt.
     * 
     * @param title
     *        Der Titel des Buches
     * @param author
     *        Der Autor des Buches
     * @param isbn
     *        Die ISBN des Buches */
    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    /** Getter für den Autor.
     * 
     * @return Liefert den Autor zurück */
    public String getAuthor() {
        return author;
    }

    /** Getter für die ISBN.
     * 
     * @return Liefert die ISBN zurück. */
    public String getIsbn() {
        return isbn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null) {
                return false;
            }
        } else if (!author.equals(other.author)) {
            return false;
        }
        if (isbn == null) {
            if (other.isbn != null) {
                return false;
            }
        } else if (!isbn.equals(other.isbn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author: " + author + " | ISBN: " + isbn + "\n";
    }


}
