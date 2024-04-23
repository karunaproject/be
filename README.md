# Karuna Backend

## Polski:

## Aby uruchomić ten projekt, wcześniej musisz pobrać repozytorium DevOps i uruchomić Docker

## Struktura Projektu
Config: Klasy konfiguracyjne, które ustawiają parametry naszej aplikacji.
Controllers: Kontrolery, które obsługują przychodzące żądania HTTP i kierują je odpowiednio.
DTO: Obiekty transferu danych, które przekazują dane między procesami. Zazwyczaj są wykorzystywane do przesyłania danych do FrontEndu. Obiekty DTO są tworzone na podstawie Hibernate entities.
Models: Modele encji, które odzwierciedlają struktury danych aplikacji.
Repositories: Interfejsy do interakcji z bazą danych.
Services: Klasy serwisowe, gdzie implementowana jest logika biznesowa działania na modelach z bazy danych.
KarunaBackendApplication: Główna klasa aplikacji.

## Migracje Bazy Danych z Liquibase
### Aby uruchomić migrację, wystarczy postępować zgodnie z następującymi krokami:
- Sprawdź bieżącą wersję pliku migracyjnego w liquibase.properties pod kluczem "diffChangeLogFile".
- Zmień wartość wspomnianego klucza na inną nazwę i określ nową wersję, na przykład jeśli poprzedni ciąg był równy db.changelog.v1.5, nowa wersja powinna być czymś w rodzaju db.changelog.v1.6 itp.
- Po zmianie wersji wykonaj polecenie mvn liquibase:diff lub wybierz polecenie z Maven -> wtyczki -> liquibase -> diff. To powinno utworzyć nowy plik changelog w określonym folderze.
### ZANIM URUCHOMISZ MIGRACJĘ - Sprawdź utworzony plik .xml, jeśli plik ten wykonuje nieodwracalne zmiany, takie jak usuwanie tabeli, musisz ręcznie określić opcję wycofania. Więcej w dokumentacji Liquibase.
- Aby zaktualizować samą bazę danych, uruchom: mvn liquibase:update, lub Maven -> wtyczki -> liquibase -> update.
- Upewnij się, że nie zmieniasz istniejącego pliku .xml changelog, ponieważ Liquibase opiera się na hashach, więc zmiana pliku może skutkować nieoczekiwanym rezultatem.
- Po zaktualizowaniu bazy danych -> Każda migracja powinna być przetestowana, czy działa i może być cofnięta. Uruchom mvn liquibase:rollback -D"liquibase.rollbackCount"=1 -> To polecenie powinno cofnąć ostatni update.


Projekt aktualizuje bazę danych z modeli encji Hibernate I changelogów Liquibase. Dlatego model Hibernate powinien również aktualizować bazę danych, abyś mógł przetestować swoje modele PRZED utworzeniem nowego changelogu.

### Changelogi bazy danych powinny być tworzone TYLKO JEŚLI model bazy danych został przetestowany wcześniej za pomocą Hibernate.

--------------------------------------------------------------------------------------------------------------------------------------------------------------

## English:

## In order to run this project, previously you have to pull DevOps repository and run the docker

## Project Structure
Config: Configuration classes that set up our application parameters.
Controllers: Controllers that handle incoming HTTP requests and direct them accordingly.
DTO: Data Transfer Objects that shuttle data between processes. Usually those objects represent data that should be sent back to FrontEnd, those models are based on Hibernate entities.
Models: Entity models that reflect the application's data structures.
Repositories: Interfaces for database interactions.
Services: Service classes where the business logic is implemented.
KarunaBackendApplication: The main application class.

## Database Migrations with Liquibase
### In order to run migration all you have to follow those steps:
- Check the current version of migration file in liquibase.properties under "diffChangeLogFile" key.
- Change the value of mentioned key to another name and specify new version, for example if previous string was equal to db.changelog.v1.5 new version should be something like for example if previous string was equal to db.changelog.v1.6 ect.
- After you've changed the version execute mvn liquibase:diff command or choose the command from Maven -> plugins -> liquibase -> diff. That should create new changelog file in specified folder.
### BEFORE YOU RUN THE MIGRATION - Check the created .xml file, if this file does some irreversible changes like removing table, you have to manually specify rollback option. More in liquibase's documentation.
- In order to update database itself run: mvn liquibase:diff, or Maven -> plugins -> liquibase -> update
- Make sure that you do not change existing .xml changelog file since liquibase is based on hash so changing the file might create unexpected behaviour.
- After you update the database -> Every migration should be tested if it works and could be rollbacked. Run  mvn liquibase:rollback  -D"liquibase.rollbackCount"=1 -> This command should rollback last update.



Project updates database from Hibernate entity models AND liquibase changelogs. Therefore Hibernate model should update the database as well, so you could test your models BEFORE creating new changelog.

### Database changelogs should be created ONLY IF database model was tested before using Hibernate.




