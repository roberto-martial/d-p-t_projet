# **Projet : Frigo Intelligent**

---

## **Contexte**

Ce projet vise à concevoir un **frigo intelligent** capable de surveiller et contrôler son contenu grâce à une interaction entre **Java** et **Arduino**.  
L'application permet la manipulation du frigo en utilisant un microcontrôleur **Arduino** connecté à une interface développée en **Java**.

Le projet a été réalisé dans le cadre d'une initiative académique visant à mettre en pratique les notions de **programmation embarquée**, de **liaison logicielle** et d'**interaction matérielle**.

---

## **Objectif du Projet**

L'objectif principal est de développer un **système intelligent** pour :  
1. **Surveiller** le contenu d'un frigo en temps réel (température, état des stocks).  
2. **Contrôler** les fonctionnalités principales du frigo (réglage de température, ouverture/fermeture).  
3. **Automatiser** certaines actions grâce à la communication entre **Java** (logiciel) et **Arduino** (matériel).  

---

## **Fonctionnalités Principales**

### **1. Surveillance en Temps Réel**  
- **Température du frigo** : Affichage en direct grâce aux capteurs connectés à Arduino.  
- **Niveau de stockage** : Visualisation des stocks via des capteurs de poids ou des capteurs IR.

### **2. Contrôle des Fonctionnalités**  
- Ajustement de la **température** depuis l'interface Java.  
- Envoi de commandes pour **ouvrir/fermer** le frigo à distance (simulateur d'ouverture).

### **3. Alerte et Notifications**  
- Alerte lorsque la température dépasse un seuil critique.  
- Notifications pour signaler un niveau de stock faible.  

---

## **Architecture du Projet**

### **1. Vue Globale**  
Le projet repose sur une **communication série** entre un logiciel Java et un microcontrôleur Arduino :  
```plaintext
[Interface Java] <----> [Port Série] <----> [Arduino] <----> [Capteurs/Actionneurs]
