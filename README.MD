
# Contributors
* Samuel Keusch
* Florian Schärer
* Stefan Landös

# Lösung zur "Programmieraufgabe: Bayes-Spam-Filter"

## Zum Ausführen die Klasse "Main" starten.

## b)
Begründung: In der Formel werden die jeweiligen Wahrscheinlichkeiten für alle Wörter mit einander multipliziert.
Wenn Ein Wort in keiner Spam-Mail vorkam, dann wird die Wahrscheinlichkeit dieses Wortes auf Null gesetzt.
Dadurch wird aber das gesammte Produkt der Wahrscheinlichkeiten Null und somit die gesammte Spam-Wahrscheinlichkeit der Mail.

##c)
Unsere Kalibrierung hat ergeben, dass die meissten Spam-Mails eine Spam-Wahrscheinlichkeit sehr nahe bei 1
erhalten und die meissten Ham-Mails eine sehr nahe bei 0. Es gibt jedoch ein paar ausreisser,
die komplett falsch eingestuft werden. In unserer Auswertung der Test-mails werden jeweils 18 von 204 Spam-Mails
und 33 von 1477 Ham-Mails falsch eingestuft und zwar für jeden Schwellenwert zwischen 0.1 und 0.9.
Wir haben uns für einen Schwellenwert von 0.7 entschieden, da es schlimmer ist, wenn eine Ham-Mail falsch eingestuft
wird, als wenn eine Spam-mail falsch eingestuft wird.
 