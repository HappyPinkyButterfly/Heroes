# Heroes Manager

Full-stack učni projekt, navdihnjen z uradnim Angular tutorialom **Tour of Heroes**, razširjen z PostgreSQL bazo in Spring Boot backendom.

> **Nivo:** Beginner (ponovitev in utrjevanje full-stack znanja)  
> **Predviden čas:** 5 tednov, 2–3 uri na dan  
> **Pravilo:** Ne nadaljuj na naslednjo stopnjo, dokler trenutna ne dela in ne razumeš, zakaj dela.

Ločen učni repozitorij — ne mešaj z CMS ali produkcijskim ERP (Neostrat).

---

## Kaj gradiš?

**Heroes Manager** je aplikacija za upravljanje junakov, misij in supermoči. Namesto mock podatkov v Angular servisu uporabljaš pravo PostgreSQL bazo in REST API.

### Končni cilj

| Funkcionalnost | Opis |
|----------------|------|
| Registracija in prijava | Uporabniki se ustvarijo račun in se varno prijavijo |
| Junaki (CRUD) | Ustvarjanje, branje, urejanje in brisanje junakov |
| Misije | Dodajanje misij posameznemu junaku |
| Moči (powers) | Junak lahko ima več supermoči (Many-to-Many) |
| Dashboard | Pregled top junakov |
| Iskanje | Iskanje junakov po imenu |
| Vloge | USER in ADMIN |
| Paginacija | Seznam junakov po straneh, sortiranje |
| Profil | Nalaganje profilne slike (avatar) |
| Varnost | JWT avtentikacija, validacija, zaščiteni endpointi |
| Baza | Relacije, indeksi, pogledi (VIEW), triggerji |
| DevOps | Docker, lokalni zagon celotnega stacka, deployment v oblak |

### MVP (teden 1)

Po prvem tednu mora delovati:

- Baza `heroes_db` s testnimi junaki (ročno v pgAdmin/DBeaver)
- JDBC povezava + backend API za junake
- Angular frontend: seznam junakov, podrobnosti, dashboard, dodajanje junaka

---

## Tech stack

| Sloj | Tehnologija | Namen |
|------|-------------|-------|
| **Baza** | PostgreSQL | Shranjevanje uporabnikov, junakov, misij, moči |
| **Backend** | Spring Boot (Java 21) | REST API, poslovna logika, varnost |
| **Frontend** | Angular (LTS) | Tour of Heroes UI |
| **ORM** | JPA / Hibernate | Preslikava Java razredov v tabele baze |
| **Varnost** | Spring Security + JWT | Avtentikacija in avtorizacija |
| **Migracije baze** | Flyway | Verzioniranje sheme baze |
| **Testiranje** | JUnit, Mockito, Angular Test | Preverjanje pravilnosti kode |
| **API dokumentacija** | Swagger (OpenAPI) | Interaktivna dokumentacija endpointov |
| **Kontainerizacija** | Docker + Docker Compose | Zagon celotnega stacka |
| **Deployment** | Render / Railway / Fly.io ali AWS EC2 | Javna dostopnost aplikacije |

---

## Struktura repozitorija

```
heroes-learning/
├── heroes-backend/    ← Spring Boot aplikacija (IntelliJ)
├── heroes-frontend/   ← Angular aplikacija (Tour of Heroes UI)
├── db/
│   ├── manual/        ← SQL skripte iz pgAdmin (stopnja 1)
│   └── flyway/        ← Flyway migracije (od stopnje 14)
├── docker/            ← Dockerfile, docker-compose
└── README.md
```

---

## Potrebna orodja

Pred začetkom namesti in preveri:

| Orodje | Namen | Preverjanje |
|--------|-------|-------------|
| **Java 21 (JDK)** | Spring Boot backend | `java -version` |
| **Node.js LTS** | Angular CLI in frontend | `node -v` in `npm -v` |
| **PostgreSQL** | Relacijska baza | `psql --version` |
| **pgAdmin ali DBeaver** | Ročno upravljanje baze | Povezava na `localhost:5432` |
| **IntelliJ IDEA** | Backend razvoj | — |
| **VS Code ali Cursor** | Frontend razvoj | — |
| **Git + GitHub** | Verzioniranje kode | `git --version` |
| **Postman** | Testiranje REST API-ja | — |

### Lokalni porti

| Storitev | Port |
|----------|------|
| PostgreSQL | `5432` |
| Spring Boot backend | `8080` |
| Angular dev server | `4200` |

---

## Pristop k bazi — database-first

Baza je **glavni vir resnice**. Shemo ustvariš ročno v pgAdmin/DBeaver; backend se nanjo poveže — ne obratno.

### Veriga povezave

```
PostgreSQL (pgAdmin)
    ↕  JDBC URL + uporabnik + geslo  (application.properties)
DataSource (connection pool)
    ↕
JdbcTemplate  ←  ročno SQL v DAO razredu  (stopnja 2)
    ↕
JPA / Hibernate  ←  entitete preslikajo obstoječe tabele  (stopnja 3)
    ↕
Repository  ←  abstrakcija nad JPA
    ↕
Service → Controller → Angular HeroService → UI
```

### Pravila

1. Tabele **ustvariš ročno** v pgAdmin/DBeaver.
2. Backend se **poveže ročno** prek JDBC nastavitev.
3. Najprej bereš podatke z **JdbcTemplate in ročnim SQL**.
4. Šele nato dodaš **JPA entitete** — Hibernate **ne sme** ustvarjati sheme (`ddl-auto=none`, kasneje `validate`).
5. **Flyway** kasneje nadomesti ročne spremembe v pgAdmin.

---

## Zagon projekta

> Navodila spodaj veljajo, ko implementiraš posamezne stopnje učnega načrta.

### 1. Baza podatkov (stopnja 1)

1. Odpri pgAdmin ali DBeaver in se poveži na lokalni PostgreSQL.
2. Ustvari bazo `heroes_db`.
3. Zaženi SQL skripte iz `db/manual/` (npr. `V1__init_schema.sql`).
4. Preveri, da so v bazi testni junaki (Iron Man, Spider-Man, Wonder Woman, Batman, Black Widow).

### 2. Backend (stopnja 2+)

```bash
cd heroes-backend
./mvnw spring-boot:run
```

V `application.properties` nastavi:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/heroes_db
spring.datasource.username=<tvoj_uporabnik>
spring.datasource.password=<tvoje_geslo>
spring.jpa.hibernate.ddl-auto=validate
```

Preveri API v Postmanu: `GET http://localhost:8080/api/heroes`

### 3. Frontend (stopnja 5+)

```bash
cd heroes-frontend
npm install
ng serve
```

Odpri brskalnik: [http://localhost:4200/dashboard](http://localhost:4200/dashboard)

Preveri, da `environment.ts` kaže na backend (`apiUrl: 'http://localhost:8080/api'`).

### 4. Docker (stopnja 19+)

```bash
cd docker
docker-compose up
```

---

## Učni načrt — faze

| Faza | Teden | Stopnje | Rezultat |
|------|-------|---------|----------|
| **0 — Priprava** | Pred začetkom | 0 | Orodja, Git repozitorij |
| **1 — MVP** | 1 | 1–6 | Baza + JDBC + JPA + API + Tour of Heroes UI |
| **2 — Varnost** | 2 | 7–9 | DTO-ji, JWT, paginacija, iskanje |
| **3 — Napredno** | 3–4 | 10–16 | Vloge, auditing, Flyway, SQL, testi |
| **4 — Produkcija** | 5 | 17–21 | Upload, Swagger, Docker, deploy, portfolio |

Po vsaki stopnji naredi vsaj en Git commit (npr. `Stopnja 5: Tour of Heroes UI`).

---

## Pomembna pravila

1. **Ne preskakuj stopnje** — brez delujočega MVP (1–6) ne dodajaj JWT ali paginacije.
2. **En koncept na stopnjo** — ne mešaj JDBC in JWT v istem koraku.
3. **Commitaj pogosto** — majhni, smiselni commiti.
4. **Razumej, ne kopiraj** — Angular strukturo vzemi iz Tour of Heroes tutoriala, backend gradi po database-first principu.
5. **Hibernate ne ustvarja sheme** — tabele vedno najprej v pgAdmin/DBeaver.

---

## Povezava s CMS in Neostrat ERP

Isti backend principi, druga domena:

| Heroes | CMS | Koncept |
|--------|-----|---------|
| Hero | Post | Glavna entiteta |
| Mission | Comment | Podrejen zapis |
| Power | Category | Many-to-Many |
| HeroDao (JDBC) | PostDao (JDBC) | Ročni SQL |
| HeroRepository | PostRepository | JPA |
| DashboardComponent | PostListComponent | Angular seznam |
| `heroes_db` | `cms_db` | PostgreSQL baza |

Ko končaš Heroes in CMS projekt, iste vzorce prepoznaš v produkcijskih projektih (npr. Neostrat ERP).

---

## Licenca

MIT (dodaj `LICENSE` datoteko ob zaključku projekta — stopnja 21).

---

## Avtor

Učni projekt za utrjevanje full-stack razvoja.
