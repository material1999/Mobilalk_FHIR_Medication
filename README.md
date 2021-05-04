# Mobilalk_FHIR_Medication

#### Készítette: Vass Máté

<br>

---

<br>

## Alkalmazás rövid leírása

Az általam választott projekt az FHIR szabvány Medication erőforrása, amelyet egy Android-os mobil alkalmazásban valósítok meg. Az alkalmazás egy orvosok körében használatos, gyógyszereket kezelő adatbázis lesz, platformja révén pedig az orvosok akár otthonról, vagy a számítógépüktől távol is dologzhatnak az adatbázissal sürgős helyzetekben, egyenesen a telefonjukról.

Az alábbi funkciók érhetőek el az alkalmazásban:

- az orvosoknak be kell jelentkezniük, vendégként nem lehet belépni a rendszerbe
- bejelentkezés után lehetőség van gyógyszerek listázására, különböző szűrési feltételek alapján, ezt bármely orvos, ápoló megteheti
- különböző statisztikák is lekérhetőek a gyógyszerekről (legnagyobb gyógyszergyártók, legerősebb gyógyszerek, leghamarabb lejáró gyógyszerek)
- amennyiben valamely kutatás új eredményeket hoz, a gyógyszerekről tárolt információk módosíthatóak, azonban ehhez külön jogosultság szükséges
- ha egy gyógyszer kifejlesztésre kerül, azt az orvosok fel is vehetik a rendszerbe, valamint a régebbi, már nem használatos gyógyszereket törölhetik is (szintén jogosultsághoz kötött)