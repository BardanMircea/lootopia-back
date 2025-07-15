# ⚙️ Backend - Lootopia (MVP/POC)

Bienvenue dans le backend de **Lootopia**, un jeu de chasses au trésor cartographiques.  
Ce dépôt contient l'implémentation serveur construite pour un **MVP fonctionnel** dans le cadre du projet Lootopia.

---

## 📦 Technologies utilisées

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA (avec MySQL)
- Architecture hexagonale (ports & adapters)
- Jakarta Validation
- Lombok
- Docker (local dev)

---

## 🧱 Architecture

Le projet suit une architecture **hexagonale** claire :

src
├── domain ← Logique métier (entités, enums, ports)
├── application ← Services métier (cas d’usage)
├── infrastructure ← Adapters techniques : JPA, sécurité, emails...
├── web ← Contrôleurs REST + DTOs


Ce découpage facilite :
- l’évolution du code (testabilité, modularité)
- le remplacement d’adapters (ex : passage à MongoDB, autre système de mail)
- la séparation stricte des responsabilités

---

## ✅ Fonctionnalités du MVP

### 🔐 Authentification / Sécurité
- Création de compte avec activation par email
- Connexion avec JWT
- Rôles : `USER` (par défaut) & `ADMIN`
- Sécurisation des endpoints selon les rôles

### 🎯 Création de chasses (mode cartographique uniquement pour le MVP)
- POST `/chasses` : création d’une chasse (titre, description, coordonnées de cache, visibilité, récompense…)
- GET `/mes-chasses` : liste des chasses créées par l'utilisateur connecté
- GET `/chasses/public` : liste de toutes les chasses visibles publiquement et ACTIVES

### 🧩 Étapes
- Ajout d’étapes à une chasse
    - Enigme obligatoire
    - Validation possible par : passphrase, coordonnées, ou repère RA (non utilisé pour le MVP)

### 👤 Participation
- POST `/participations` : un joueur rejoint une chasse
- GET `/participations/moi` : voir ses participations actives
- PUT `/participations/{id}/annuler` : se désinscrire
- POST `/creusages` : creuser à une coordonnée → si la cache est trouvée :
    - Statut = TERMINÉ
    - Transaction de couronnes
    - Cache découverte horodatée + hash enregistré

### 💰 Monnaie virtuelle & transactions
- Transactions de gain (récompenses)
- Transactions de dépense (creusages payants)
- GET `/transactions` : consultation de l’historique

### 🗃️ Archivage interne
- Lorsqu’un joueur découvre une cache :
    - Un **hash SHA-256** est généré à partir de son ID, de la chasse et de l’horodatage
    - Cela constitue une preuve simple d’archivage local, stockée dans `Participation.empreinteHash`
    - Objectif : montrer un mécanisme de traçabilité minimal (sans recours à un tiers sécurisé)

---
| Méthode | URI               | Description                          |
| ------- | ----------------- | ------------------------------------ |
| POST    | `/auth/register`  | Création de compte                   |
| GET     | `/auth/activate`  | Activation par token                 |
| POST    | `/auth/login`     | Connexion, obtention du JWT          |
| GET     | `/mes-chasses`    | Liste des chasses créées             |
| POST    | `/chasses`        | Création d’une chasse                |
| POST    | `/etapes`         | Ajout d’une étape à une chasse       |
| POST    | `/participations` | Inscription à une chasse             |
| POST    | `/creusages`      | Action de creuser (trouver la cache) |
| GET     | `/transactions`   | Liste des transactions couronnes     |

🎯 Fonctionnalités MVP implémentées:
✅ Création d’une chasse avec étapes
✅ Participation d’un joueur
✅ Découverte d’une cache → transaction + archivage
✅ Authentification avec JWT
✅ Traçabilité minimale des actions

📌 Limitations du MVP (à implémenter plus tard)
Chasses en monde réel (RA)

Artefacts & objets rares

Système de notifications / chat

Marketplace / gestion avancée

Archivage à valeur probante via tiers (coffre-fort numérique)

MFA / OTP

Interface web frontend (à venir)

🚀 Lancer en local (Docker + MySQL)
Créer une base lootopia_db

Lancer l’application Spring Boot (mvn spring-boot:run)

Utiliser Postman ou Swagger pour interagir avec les endpoints

📁 Organisation des packages
Package	Rôle
domain.model	Entités JPA
domain.ports	Interfaces métiers (ports)
application.service	Services métier (use cases)
infrastructure.repository	JPA, adapters
infrastructure.security	JWT, UserPrincipal
infrastructure.email	Envoi d’email d’activation
web.controller	REST controllers
web.dto	Objets de transfert pour les requêtes

## 🔐 Variables sensibles et fichier `.env`

Le projet utilise des **variables d’environnement** pour stocker les valeurs sensibles (comme les mots de passe DB, la clé JWT ou les identifiants email).

👉 Ces valeurs sont **extraites du fichier `application.properties`** grâce à Spring Boot, mais **les vraies valeurs sont stockées dans un fichier `.env`** à la racine du projet. Ce fichier n'est pas pushé sur Git.

✍️ 
Mircea BARDAN.