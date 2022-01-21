# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Dima Cristian 323CA
## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

[Puteti sa folositi si alte biblioteci si sa editati aceasta sectiune]

## Implementare

### Entitati

Clasele folosite, nu este nevoie sa enumerati proprietati si metode, 
doar sa ziceti rolul lor si de ce le folositi]

Producers - implementeaza obiectele de tip producator
ProducerMonthlyStats - retine datele producatorului din luna respectiva
InitialProducerData si ProducerChanges - pentru a citi datele despre producatori cu jackson
OutputProducerData - pentru a scrie cu jackson
GreenStrategy, PriceStrategy, QuantityStrategy - implementeaza strategiile pentru care opteaza distribuitorii

restul claselor au acelasi scop ca la etapa anterioara

### Flow

Ce se intampla in fiecare runda (luna), cum comunica entitatile intre ele, ce clasa controleaza flowul etc

Clasa care controleaza flow-ul este Processing, avand o 3 ArrayList-uri, unul pentru fiecare entitate.
In fiecare luna aceasta verifica daca exista consumatori noi si schimbari in infrastructura distribuitorilor,
apoi se seteaza contractele consumatorilor carora li s-au terminat, consumatorii si distribuitorii
isi primesc venitul si isi platesc cheltuielile, apoi, daca exista schimbari in privinta producatorilor
li se actualizeaza caracteristicile si distribuitorii afectati isi cauta cei mai potriviti producatori,
iar la final se salveaza statistica lunara a producatorilor.

Comunicarea dintre Producers si Distributors se bazeaza pe design pattern-ul Observer, acestea reprezentand
Observable, respectiv, Observator (metodele getNeedsUpdate, setNeedsUpdate si updateDistributors)

### Elemente de design OOP

e.g Am folosit incapsulare pentru ... Am folosit abstractizare pentru ...

Am folosit StrategyPattern la metodele de selectare de producatori
Fiecare dintre cele 3 clase Strategy prezinta cate o clasa interna care implementeaza Comparator
Am folosit ObserverPattern pentru a notifica distribuitorii

Restul elementelor de design au ramas neschimbate fata de etapa anterioara


### Dificultati intalnite, limitari, probleme

e.g. pentru a rezolva problema X de checkstyle am facut ...

Warning unchecked cast in cazul ArrayList-urilor datorita ambitiei mele de a face un factory universal
rezolvata cu SuppressWarnings
Mult cod duplicat in cazul claselor Strategy
Clasele Sorter puteau fi singleton 

[optional ## Feedback, comments

