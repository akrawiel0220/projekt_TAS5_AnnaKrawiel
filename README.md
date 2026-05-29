# Projekt_TAS5_AnnaKrawiel


## Autor
* **Imię i Nazwisko:** Anna Krawiel
* **Uczelnia:** Akademia Leona Koźmińskiego
* **Grupa projektowa:** projekt_TAS5_AnnaKrawiel


## Wymagania systemowe i technologiczne

Przed uruchomieniem projektu upewnij się, że na Twoim komputerze zainstalowane są następujące narzędzia:

1. **Java Development Kit (JDK):** Wersja **25**
2. Apache Maven, można zaistalować według instrukcji: https://maven.apache.org/download.cgi
3. **Przeglądarka internetowa:** Google Chrome wersja **148** lub wyższa
4. Allure Commandline - stąd można zainstalować na komputer, dokładna instrukcja: https://allurereport.org/docs/v2/install-for-windows/
5. Program intelliJ IDEA Community Edition (wersja na czas pisania testów 2025.2.6.1) 
6. System Windows 11

### Kluczowe biblioteki użyte w projekcie:
* **Selenium Java (4.44.0):** Biblioteka do sterowania przeglądarką internetową.
* **TestNG (7.12.0):** Framework testowy zarządzający wykonywaniem asercji i strukturą testów.
* **OpenCSV (5.12.0):** Parser używany do odczytu danych testowych z zewnętrznych plików `.csv` (technika DDT).
* **Allure TestNG (2.34.0) & AspectJ Weaver (1.9.25.1):** Narzędzia odpowiedzialne za zbieranie metryk i generowanie zaawansowanych raportów testowych.


## Instrukcja uruchomienia projektu

Wszystkie komendy należy uruchamiać z poziomu terminala w głównym folderze projektu.

### 1. Czyszczenie i kompilacja projektu
Aby usunąć stare pliki budowania i pobrać wszystkie wymagane zależności z pliku `pom.xml`(jednorazowo), wpisać należy:

mvn clean compile

### 2. Uruchomienie testów
Testy są konfigurowane i uruchamiane za pomocą pliku `testng.xml` zintegrowanego z wtyczką `maven-surefire-plugin`.
Aby uruchomić wszytskie testy jednocześnie z usuwaniem starych plików budowania, w terminalu wpisujemy polecenie:

mvn clean test

## Generowanie raportów Allure

Po zakończeniu testów, wyniki w formacie surowym zostają zapisane automatycznie w katalogu `target/allure-results`. 
Przekształcenie w raport graficzny:

### Automatyczne wygenerowanie i otwarcie raportu 
Wtyczka `allure-maven` skonfigurowana w projekcie potrafi sama zbudować i otworzyć serwer z raportem. 
Używamy następującej komendy w termianlu:

mvn allure:serve

### Generowanie raportu do folderu
Możemy też wygenerować stały raport do katalogu `allure-report` bez uruchamiania lokalnego serwera, wtedy wpisujemy:

mvn allure:report
