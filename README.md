# TDAT2004Task6
Oppgave 1: src/main/java/OptimistiskLåsing.txt:

Optimistisk låsing er å ha en annotasjon @Version over en variabel som holder styr på versjonen for databaseraden.
Om to transaksjoner skjer samtidig, vil den som blir sist ferdig se at sin lokale versjon ikke stemmer med den "offisielle" versjonen i database, og at den ikke har riktige data. Da kaster den en feil, og operasjonen bør skje på nytt.

Oppgave 2: Kjør filen src/main/java/Tasks/Client1.java, og få følgende resultat:

    All accounts: 
    Account in Gunnar's name with account number 1 has 50.0 money
    Account in Frank's name with account number 2 has 200.0 money
    All accounts with more than 75 money: 
    Account in Frank's name with account number 2 has 200.0 money
    Gunnar after update: 
    Account in Ole's name with account number 1 has 50.0 money
    
Oppgave 3: Løst ved å kjøre to tråder, hvor vi får feil data om systemet kjører flere ganger.

Oppgave 4: Kjører filen src/main/Tasks/Client2.java, og får OptimisticLockException og RollbackException om vi prøver å gjøre noe feil. Å sette inn feilbehandling av disse exceptionene gjør at vi ender opp med riktig resultat til slutt.
