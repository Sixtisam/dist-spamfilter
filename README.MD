
# Contributors
* Samuel Keusch
* Florian Sch�rer
* Stefan Land�s

# L�sung zur "Programmieraufgabe: Bayes-Spam-Filter"

## Zum Ausf�hren die Klasse "Main" starten.

## b)
Begr�ndung: In der Formel werden die jeweiligen Wahrscheinlichkeiten f�r alle W�rter mit einander multipliziert.
Wenn Ein Wort in keiner Spam-Mail vorkam, dann wird die Wahrscheinlichkeit dieses Wortes auf Null gesetzt.
Dadurch wird aber das gesammte Produkt der Wahrscheinlichkeiten Null und somit die gesammte Spam-Wahrscheinlichkeit der Mail.

##c)
Unsere Kalibrierung hat ergeben, dass die meissten Spam-Mails eine Spam-Wahrscheinlichkeit sehr nahe bei 1
erhalten und die meissten Ham-Mails eine sehr nahe bei 0. Es gibt jedoch ein paar ausreisser,
die komplett falsch eingestuft werden. In unserer Auswertung der Test-mails werden jeweils 18 von 204 Spam-Mails
und 33 von 1477 Ham-Mails falsch eingestuft und zwar f�r jeden Schwellenwert zwischen 0.1 und 0.9.
Wir haben uns f�r einen Schwellenwert von 0.7 entschieden, da es schlimmer ist, wenn eine Ham-Mail falsch eingestuft
wird, als wenn eine Spam-mail falsch eingestuft wird.
 