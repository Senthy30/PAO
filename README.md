Tema aplicatiei va fi food delivary.
Scopul aplicatiei este de a ajuta utilizatorii a da comanda mancare.
Comanda de mancare poate fi trimisa atat printr-un livrator cat si poate fi ridicata de la unul dintre restaurantele valabile.
Fiecare restaurant va dispune de cate o persoana care se va ocupa de livrare.
Fiecare restaurant va dispune de acelasi tipuri de produse, iar fiecare restaurant va avea cantiatea necesara dintr-un produs astfel incat sa satisfaca fiecare comanda.
Fiecare restaurant va putea sa fixeze propriile preturi pentru produsele disponibile.
In clasa Restaurant se vor afla informatii precum:
	- id unic
	- adresa locatie
	- produsele disponibile sortate dupa pret descrescator
	- lista cu livratorii
	- istoric comenzi
Va exista o clasa Livrator in care se vor afla informatii precum:
	- id unic
	- id-ul restaurantului unde lucreaza
	- nume
	- prenume
	- telefon
	- salariu
	- calificativ sub forma unei note de la 0.00 la 10.00 oferit de utilizatori (cu 2 zecimale)
Fiecare tip de produs va mosteni o clasa numita Produs in care se vor afla informatii precum: 
	- id unic
	- id-ul restaurantului unde a fost facut
	- greutate 
	- pret 
	- data de expirare
 	- calorii
	- o scurta descriere a produsului
Vor exista mai multe tipuri de produse cum ar fi: 
	- Hamburger
	- Pizza
 	- Shaorma
	- Apa 
	- Suc
Clasa Hamburger va avea informatiile precum:
	- contine sare
	- contine seminte de susan
	- contine piper
	- contine cartofi
	- contine rosii
	- contine varza
	- contine castraveti murati
	- contine cascaval
	- contine ceapa
Clasa Pizza va avea informatii precum:
	- contine sare
	- contine mozzarela
	- contine masline
	- contine ceapa	
	- contine ciuperci
	- contine rosii
Clasa Shaorma va avea informatii precum:
	- contine sare
	- contine cartofi
	- contine varza
	- contine rosii
	- contine ceapa 
	- contine castraveti murati
	- contine patrunjel
	- contine ketchup
	- contine maioneza
Clasa Apa va contine informatii precum:
	- pH
Clasa Suc va contine informatii precum:
	- tipul de suc:
		- cola
		- pepsi	
		- fanta
	- contine zahar
Pentru fiecare tip de produs utilizatorul va putea personaliza produsul.
Va exista o clasa Comanda in care se vor afla informatii precum: 
	- id unic
	- id-ul restaurantului de unde a plecat comanda
	- toate produsele comandate de un utilizator
	- pretul total al produselor 
	- comanda este cu livrare sau se ridica din restaurant
	- daca suma nu depaseste o anumita valoare iar comanda este cu livrare atunci se adauga un adaos la pret
	- daca comanda este cu livrare, se vor retine informatiile cu privire la livrator
Va exista o clasa Utilizator, in care se vor afla informatii precum:
	- id unic
	- nume
	- prenume
	- telefon
	- adresa
	- numar card (daca solicita plata cu cardul)
	- comenzile in curs de livrare
	- istoric cu toate comenzile
Va exista o clasa Aplicatie, in care se vor afla informatii precum:
	- lista cu toate restaurantele disponibile pe aplicatie
	- lista cu toti utilizatorii disponibili pe aplicatie

Aplicatia va contine un meniu in principal in care se poate efectua una dintre actiunile:
  0. Exit
  1. Creeaza un nou restaurant
  2. Creeaza un nou user
  3. Navigheaza printre utilizatori
  4. Navigheaza printre restaurante
 Daca se selecteaza optiunea 1 sau 2 din meniul principal, atunci se va cere informatii cu privire la restaurant, respectiv user. In cazul in care informatii nu sunt valide, se arunca o exceptie si se termina de citit restaurantul, respectiv userul, fara sa se adauga in lista ca un nou obiect.
 Daca se selecteaza optiunea 3 din meniul principal, atunci se va cere un id corespunzator unui user dupa care se va afisa un meniu cu urmatoarele optiuni:
  0. Exit
  1. Creeaza o noua comanda
  2. Afiseaza istoriul de comenzi
  3. Afiseaza produsele dintr-o comanda dupa un index
  4. Afiseaza produsele in oridine crescatoare dupa pret dintr-o comanda dupa un index
  5. Afiseaza produsele in oridine descrescatoare dupa pret dintr-o comanda dupa un index
  6. Afiseaza cele mai scumpe produse cumparate
  7. Verifica daca ai comandat ceva intr-o anumita zi
  8. Schimba adresa de livrare
  9. Schimba numarul de telefon
  10. Schimba emailul
  11. Afiseaza toate informatiile despre acest user
 Daca se selecteaza optiunea 4 din meniul principal, atunci se va cere un id corespunzator unui restaurant dupa care se va afisa un meniu cu urmatoarele optiuni:
  1. Afiseaza produsele disponibile in restaurant
  2. Afiseaza produsele disponibile in restaurant in ordine crescatoare dupa pret
  3. Afiseaza produsele disponibile in restaurant in ordine descrescatoare dupa pret
  4. Afiseaza toate informatiile despre un livrator cu un anumit id
  5. Afiseaza toate informatiile despre toti livratorii din cadrul restaurantului
  6. Adauga un nou livrator
 
 
 
 
 
 
 
