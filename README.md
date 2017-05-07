# Starter Code für 2. Pratkikumsaufgabe Software-Architektur Sommer 2017 

I. Link to heroku
https://sa17.herokuapp.com/



II. Beschreibung der REST-API




1. /media/books		POST	Neues Medium Buch anlegen
Beim anlegen eines Exemplares wird ein Object aus dem übertragenem JSON-File erzeugt. Die ISBN wird auf den ISBN-13 Standard geprüft. Dabei wird die Prüfsumme im letzten Teil der ISBN nicht auf Gültigkeit geprüft, sondern nur auf die Länge 1.
Beispiel:
{
    "title"  : "Titel",
    "author" : "Author",
    "isbn"   : "978-3-86680-192-9"
}


2. /media/books{isbn}	GET		Liefer eine JSON-Repraesentation eines gespeicherten Buches oder null falls nicht vorhanden
Diese URL liefert ein JSON-Objekt des Buches mit der angeforderten ISBN zurück. Falls kein Buch vorhanden ist wird ein HTTP-Fehlercode zurückgeliefert.
Beispiel:
{
    "title"  : "Titel",
    "author" : "Author",
    "isbn"   : "978-3-86680-192-9"
}

3. /media/books 			GET 	Alle Buecher auflisten
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


4. /media/books/{isbn} 	PUT 	Daten zu vorhandenem Buch modifizieren (JSON-Daten enthalten nur die zu modifizierenden Attribute)
Diese URL ermöglicht es ein vorhandenese Buch zu modifizieren. Es kann der Autor und der Titel modifiziert werden, jedoch nicht die ISBN.


5. /media/discs 			POST 	Neues Medium Disc anlegen


6. /media/discs 			GET 	Alle Discs auflisten
7. /media/discs/{barcode} 	GET 	Eine JSON-Repraesentation einer gespeicherten Disc liefern, falls vorhanden
8. /media/discs/{barcode} 	PUT 	Daten zur vorhandenen Disc modifizieren (JSON-Daten enthalten nur die zu modifizierenden Attribute)
9. /copy/{user}/books 		POST 	Neues Exemplar Buch dem Konto anlegen
10. /copy/{user}/books 		GET 	Alle Exemplare Buch auflisten von dem Konto
11. /copy/{user}/discs 		POST 	Neues Exemplar Disc dem Konto anlegen
12. /copy/{user}/discs 		GET 	Alle Exemplare Disc auflisten von dem Konto




Das JSON object für die Disc hat diesen Aufbau:

Disc:

{
    "title"    : "Titel",
    "barcode"  : "Barcode",
    "director" : "Director",
    "fsk"      : "18"
}


