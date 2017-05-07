# Starter Code für 2. Pratkikumsaufgabe Software-Architektur Sommer 2017 

I. Link zu Heroku:
https://sa17.herokuapp.com/

II. Beschreibung eines Buches und einer Disc als JSON-File.
Book:
{
    "title"  : "A Book Titel",
    "author" : "The Book Author",
    "isbn"   : "978-3-86680-192-9"
}

Disc
{
	"title": "A Disc Title",
	"director": "The Disc Director",
	"barcode": "123456789012",
	"fsk": "0"
}

III. Beschreibung der REST-API

1. /media/books		POST
Beim anlegen eines Exemplares wird ein Object aus dem übertragenem JSON-File erzeugt. Die ISBN wird auf den ISBN-13 Standard geprüft. Dabei wird die Prüfsumme im letzten Teil der ISBN nicht auf Gültigkeit geprüft, sondern nur auf die Länge 1.
Beispiel:
{
    "title"  : "Titel",
    "author" : "Author",
    "isbn"   : "978-3-86680-192-9"
}


2. /media/books/{isbn}	GET
Diese URL liefert ein JSON-Objekt des Buches mit der angeforderten ISBN zurück. Falls kein Buch vorhanden ist wird ein HTTP-Fehlercode zurückgeliefert.
Beispiel:
{
    "title"  : "Titel",
    "author" : "Author",
    "isbn"   : "978-3-86680-192-9"
}

3. /media/books		GET
Diese URL liefert eine Liste von allen vorhandenen Büchern als JSON-Ojbekte zurück. 
Beispiel:
[
  {
    "title": "A Title",
    "author": "The Author",
    "isbn": "978-3-86680-192-5"
  },
  {
    "title": "A Title",
    "author": "The Author",
    "isbn": "978-3-86680-142-5"
  }
]


4. /media/books/{isbn} 	PUT
Diese URL ermöglicht es ein vorhandenese Buch zu modifizieren. Es kann der Autor und der Titel modifiziert werden, jedoch nicht die ISBN.
Beispiel:
{
  "title": "A Title",
  "author": "The Author",
  "isbn": "978-3-86680-142-5"
}


5. /media/discs 			POST
Beim anlegen einer Disc wird ein Object aus dem übertragenem JSON-File erzeugt. Der Barcode wird auf korrekte Anzahl der Ziffern geprüft.
Beispiel:
{
	"title": "A Title",
	"director": "The Director",
	"barcode": "123456789012",
	"fsk": "0"
}

6. /media/discs/{barcode}	GET
Diese URL liefert ein JSON-Objekt der Disc mit dem angeforderten Barcode zurück. Falls keine Disc vorhanden ist wird ein HTTP-Fehlercode zurückgeliefert.
Beispiel:
{
	"title": "A Title",
	"director": "The Director",
	"barcode": "123456789012",
	"fsk": "0"
}

7. /media/discs/ 	GET
Diese URL liefert eine Liste von allen vorhandenen Discs als JSON-Objekte zurück. 
Beispiel:
[
  {
	"title": "A Title",
	"director": "The Director",
	"barcode": "123456789012",
	"fsk": "0"
  },
  {
	"title": "Another Title",
	"director": "The Director",
	"barcode": "123456789013",
	"fsk": "0"
  }
]

8. /media/discs/{barcode} 	GET
Diese URL ermöglicht es eine vorhandene Disc zu modifizieren. Es kann der Director, der Titel und die FSK modifiziert werden, jedoch nicht der Barcode.
Beispiel:
{
	"title": "A Title",
	"director": "The Director",
	"barcode": "123456789012",
	"fsk": "0"
}