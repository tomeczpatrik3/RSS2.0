Az api végpontok tesztelésének menete:

1 -- Teszt adatbázis elõállítása, TEST_DB.sql fájl segítségével (fontos az id tesztelés miatt, hogy clean adatbázisba kerüljenek az értékek)
2 -- Admin felhasználó létrehozása, token frissítése a tesztkörnyezetben
3 -- Ha szükséges: 
	--> newman telepítése: 					npm install newman --global;
	--> newman html reporter telepítése: 	npm install -g newman-reporter-html
4 -- Tesztek futtatása:
	a) szöveges tesztek: text_tests.sh futtatásával
	b) html riport készítése: html_tests.sh futtatásával

