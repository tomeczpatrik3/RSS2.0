Az api v�gpontok tesztel�s�nek menete:

1 -- Teszt adatb�zis el��ll�t�sa, TEST_DB.sql f�jl seg�ts�g�vel (fontos az id tesztel�s miatt, hogy clean adatb�zisba ker�ljenek az �rt�kek)
2 -- Admin felhaszn�l� l�trehoz�sa, token friss�t�se a tesztk�rnyezetben
3 -- Ha sz�ks�ges: 
	--> newman telep�t�se: 					npm install newman --global;
	--> newman html reporter telep�t�se: 	npm install -g newman-reporter-html
4 -- Tesztek futtat�sa:
	a) sz�veges tesztek: text_tests.sh futtat�s�val
	b) html riport k�sz�t�se: html_tests.sh futtat�s�val

