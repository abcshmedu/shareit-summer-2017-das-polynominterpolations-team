# Starter Code für 2. Pratkikumsaufgabe Software-Architektur Sommer 2017 

https://sa17.herokuapp.com/


# Beschreibung der REST-API:

# /media/books 			POST 	Neues Medium Buch anlegen
Beim anlegen eines Exemplares wird ein Object aus dem übertragenem JSON-File erzeugt.Die ISBN wird auf den ISBN-13 Standard geprüft. Dabei wird die Prüfsumme im letzten Teil der ISBN nicht auf Gültigkeit geprüft, sondern nur auf die Länge 1.
Buch:
{
    "title"  : "Titel",
    "author" : "Author",
    "isbn"   : "978-3-86680-192-9"
}


# /media/books{isbn}	GET		Liefer eine JSON-Repraesentation eines gespeicherten Buches oder null falls nicht vorhanden
Diese URL liefert ein JSON-Objekt des Buches mit der angeforderten ISBN zurück. Falls kein Buch vorhanden ist wird ein HTTP-Fehlercode zurückgeliefert.
{
    "title"  : "Titel",
    "author" : "Author",
    "isbn"   : "978-3-86680-192-9"
}

# /media/books 			GET 	Alle Buecher auflisten



# /media/books/{isbn} 	PUT 	Daten zu vorhandenem Buch modifizieren (JSON-Daten enthalten nur die zu modifizierenden Attribute)
# /media/discs 			POST 	Neues Medium Disc anlegen
# /media/discs 			GET 	Alle Discs auflisten
# /media/discs/{barcode} 	GET 	Eine JSON-Repraesentation einer gespeicherten Disc liefern, falls vorhanden
# /media/discs/{barcode} 	PUT 	Daten zur vorhandenen Disc modifizieren (JSON-Daten enthalten nur die zu modifizierenden Attribute)
# /copy/{user}/books 		POST 	Neues Exemplar Buch dem Konto anlegen
# /copy/{user}/books 		GET 	Alle Exemplare Buch auflisten von dem Konto
# /copy/{user}/discs 		POST 	Neues Exemplar Disc dem Konto anlegen
# /copy/{user}/discs 		GET 	Alle Exemplare Disc auflisten von dem Konto




Das JSON object für die Disc hat diesen Aufbau:

Disc:

{
    "title"    : "Titel",
    "barcode"  : "Barcode",
    "director" : "Director",
    "fsk"      : "18"
}


