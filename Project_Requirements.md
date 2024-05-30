# Documentație Cerințe Proiect

## 1. Definirea Sistemului

### Acțiuni/Interogări
1. CREARE cont utilizator
2. ȘTERGERE cont utilizator
3. ACTUALIZARE cont utilizator
4. ADĂUGARE evenimente
5. ȘTERGERE evenimente
6. VIZUALIZARE evenimente disponibile
7. CUMPĂRARE bilete
8. ANULARE bilete
9. ADĂUGARE recenzii pentru evenimente
10. VIZUALIZARE locații
11. VIZUALIZARE recenzii
12. VIZUALIZARE rezervari
13. VIZUALIZARE reduceri
14. AUDIT al acțiunilor efectuate în baza de date

### Tipuri de Obiecte
1. Admin
2. Discount
3. Event
4. RegularUser
5. Reservation
6. Review
7. Ticket
8. TypeEvent
9. User
10. Venue

## 2. Implementare

### Clasa Main
- **ConsoleApp**: Clasa principală care inițiază aplicația și face apeluri către servicii.

### Clase simple cu atribute private/protected și metode de acces
- Exemple: `Admin`, `Discount`, `Event`, `RegularUser`, `Reservation`, `Review`, `Ticket`, `TypeEvent`, `User`, `Venue`.

### Colecții diferite pentru gestionarea obiectelor
- **List**: Utilizat pentru a stoca liste de obiecte, cum ar fi utilizatori și evenimente.
- **Map**: Utilizat pentru a stoca relații cheie-valoare, cum ar fi asocieri între ID-uri de utilizatori și obiecte de tip utilizator.
- **Set**: Utilizat pentru a stoca colecții de obiecte unice, cum ar fi rezervări și recenzii.

### Utilizare moștenire
- Clasa de bază `User` are două clase derivate: `Admin` și `RegularUser`.

### Clasa serviciu care expune operațiile sistemului
- **DatabaseService**: Clasa care expune operațiile sistemului (create, read, update, delete).

### Persistență folosind JDBC
- Clasele din pachetul `persistence` (`AdminRepository`, `DiscountRepository`, `EventRepository`, etc.) oferă operații de tip CRUD pentru cel puțin 4 clase definite.

### Serviciu de audit
- **AuditService**: Scrie într-un fișier CSV (`audit.csv`) fiecare acțiune executată, conform structurii: `nume_actiune, timestamp`.

