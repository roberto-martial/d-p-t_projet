# **Projet : Système Intégré de Gestion des Affectations des Effectifs Cliniques Autonomes (ECA)**  

---

## **Contexte**  

Ce projet constitue l'achèvement du module **Bases de Données** et se divise en deux étapes majeures :  
1. **Modélisation de la base de données** : basée sur un document de modélisation préalablement établi.  
2. **Implémentation de l'application** : réalisée selon un **cahier des charges** sous la supervision des tuteurs en salle.  

Le travail a été effectué en **groupe de 4 à 5 étudiants** sur une durée totale de **7 jours**, incluant la présentation finale devant un jury.  

L'énoncé du problème **[MECA]** ainsi que les documents relatifs au projet sont disponibles dans le répertoire public associé.

---

## **Objectifs du Projet**  

L'objectif principal est de **développer un système intégré de gestion** répondant aux besoins organisationnels des établissements de santé.  
Ce système repose sur :  
- La **modélisation** et l'**implémentation** d'une base de données relationnelle complète.  
- Une application **3-Tiers** permettant d'interagir avec cette base de données.  

---

## **Problématique**  

**Comment assurer une gestion efficiente et efficace de la planification des affectations des effectifs cliniques autonomes (ECA) pour les mois, voire les années, à venir, dans les établissements d'un réseau de santé ?**  

Le système proposé vise à répondre à cette problématique en intégrant des fonctionnalités de gestion, de planification et d'organisation.  

---

## **Architecture Logicielle**  

Le projet suit une **architecture logicielle 3-Tiers** :  

1. **Tier 1 : Présentation**  
   - Interface utilisateur permettant une interaction intuitive avec le système.  

2. **Tier 2 : Logique Métier**  
   - Cœur de l'application où sont implémentées les fonctionnalités et règles métier.  

3. **Tier 3 : Données**  
   - Base de données relationnelle modélisée pour stocker et gérer les informations de manière optimale.  

---

## **Exigences Fonctionnelles**  

L'application doit répondre aux exigences fonctionnelles suivantes :  

1. **Gestion des métiers cliniques** :  
   - Médecins, sages-femmes, infirmières spécialisées, etc.  

2. **Gestion de la hiérarchie des unités organisationnelles** :  
   - Régions, établissements, départements, services, etc.  

3. **Gestion des unités organisationnelles (UO)** :  
   - Suivi et organisation des différentes unités de travail.  

4. **Gestion des activités des Effectifs Cliniques Autonomes (ECA)** :  
   - Organisation des tâches et activités pour le personnel clinique.  

5. **Gestion du personnel** :  
   - Identification et organisation des membres du personnel (médecins, infirmiers, etc.).  

6. **Planification du personnel** :  
   - Sur différentes périodes :  
     - **Semaine**  
     - **Mois**  
     - **Année**  

---

## **Exigences Non Fonctionnelles**  

Pour perfectionner la qualité logicielle, l'application doit :  
- Être **performante** et répondre aux requêtes de manière rapide.  
- Assurer une **sécurité** des données stockées et des accès utilisateurs.  
- Être **scalable** pour s'adapter à un volume croissant de données.  
- Offrir une **interface utilisateur intuitive** et ergonomique.  
- Garantir une **maintenabilité** facilitée pour les futures évolutions du projet.  

---

## **Structure du Projet**  

```plaintext
├── modelisation/              # Documents et schémas de modélisation de la base de données  
│   ├── mcd.pdf                # Modèle Conceptuel de Données  
│   ├── mld.pdf                # Modèle Logique de Données  
│  
├── implementation/            # Code source de l'application  
│   ├── backend/               # Logique métier et base de données  
│   ├── frontend/              # Interface utilisateur  
│   └── database/              # Scripts SQL pour la création de la base  
│  
├── documentation/             # Cahier des charges et guides  
│   └── cahier_des_charges.pdf  
│  
└── README.md                  # Documentation principale du projet  

