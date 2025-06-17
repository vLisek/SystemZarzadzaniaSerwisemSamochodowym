Wszystkie ikony użyte w aplikacji pochodzą z serwisu <a target="_blank" href="https://icons8.com">Icons8</a>

# Instrukcja uruchomienia aplikacji REPAIRO

## Wymagania środowiskowe

Do poprawnego uruchomienia aplikacji REPAIRO wymagane jest przygotowanie środowiska zgodnie z poniższymi specyfikacjami programowymi:

- **System operacyjny:** Windows 10 lub nowszy  
- **Środowisko JDK:** OpenJDK 23.0.2  
- **IDE:** IntelliJ IDEA Community Edition 2024.3.3  
- **Sterownik JDBC:** `mysql-connector-java-8.0.33` dołączony do repozytorium w katalogu `mysql-connector-j-9.3.0`  
- **Baza danych:** MariaDB (środowisko `phpMyAdmin 5.2.1`)  
- **Panel sterowania:** XAMPP Control Panel  

**Uwaga:** Domyślnym użytkownikiem serwera bazy danych jest `root`, a hasło puste (`""`), zgodnie z domyślną konfiguracją XAMPP.

## Baza danych

- Do repozytorium dołączony jest plik `warsztat.sql`, który zawiera strukturę i przykładowe dane bazy danych.
- Po uruchomieniu serwera MySQL należy zaimportować bazę danych do środowiska `phpMyAdmin`.
- Domyślny użytkownik: `root`, Hasło: _(puste lub zgodne z konfiguracją lokalną)_
- Nazwa bazy danych: `warsztat`

## Konfiguracja połączenia z bazą danych

W katalogu głównym projektu znajduje się plik:
```
config-template.properties
```

Zawiera on domyślną konfigurację połączenia z bazą danych i **musi zostać skopiowany oraz dostosowany** przed pierwszym uruchomieniem aplikacji.

**Kroki:**

1. Skopiuj plik:

```
config-template.properties → config.properties
```


2. Otwórz `config.properties` w edytorze tekstu.

3. Uzupełnij dane zgodnie z lokalną konfiguracją użytkownika bazy danych:

```
db.url=jdbc:mysql://localhost:3306/warsztat
db.user=root
db.password=
```


**Uwaga:** Klasa `DatabaseConnector` pobiera dane z tego pliku w celu nawiązania połączenia z bazą. Dzięki temu nie trzeba edytować kodu źródłowego przy każdej zmianie konfiguracji.

## Kroki uruchomienia aplikacji REPAIRO

1. **Klonowanie projektu z GitHub**

- Przejdź do repozytorium projektu:  
  [https://github.com/vLisek/SystemZarzadzaniaSerwisemSamochodowym.git](https://github.com/vLisek/SystemZarzadzaniaSerwisemSamochodowym.git)

- Pobierz repozytorium lokalnie:

  ```bash
  git clone https://github.com/vLisek/SystemZarzadzaniaSerwisemSamochodowym.git
  ```

- Otwórz folder projektu w IntelliJ IDEA Community Edition.

2. **Dodanie sterownika JDBC**

- Umieść folder `mysql-connector-j-9.3.0` w katalogu projektu
- Dodaj go jako bibliotekę w:  
  `File > Project Structure > Modules > Dependencies`

3. **Import bazy danych**

- Uruchom `XAMPP Control Panel` i włącz moduł MySQL
- Otwórz `phpMyAdmin` w przeglądarce:  
  [http://localhost/phpmyadmin](http://localhost/phpmyadmin)
- Utwórz nową bazę danych: `warsztat`
- Zaimportuj plik `warsztat.sql`

4. **Uruchomienie aplikacji**

- Kliknij przycisk `Run` w klasie `Main.java`
- Aplikacja otworzy ekran logowania

## Dane testowe (logowanie)

- **Administrator:**
- Nazwa użytkownika: `admin`
- Hasło: `admin`

- **Mechanik:**
- Nazwa użytkownika: `kszymanski`
- Hasło: `1234`

---

Po wykonaniu powyższych kroków środowisko jest gotowe do pracy z aplikacją REPAIRO.
