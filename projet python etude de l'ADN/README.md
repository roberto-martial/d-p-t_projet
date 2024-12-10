# **Projet de l'UdeM**  
### Étude et Analyse d'un Algorithme Génétique pour l'Alignement de Séquences ADN  

---

## **Membres**  
- [**Samnick Tong Roberto Martial**](https://github.com/roberto-martial) *(Chef de projet)*  

---

## **Objectif**  
Ce projet vise à implémenter un **algorithme génétique** pour résoudre le problème de **l'alignement de séquences ADN**, un enjeu majeur en bioinformatique.  
L'étude comprend la conception de l'algorithme, l'analyse de ses performances et son optimisation.

---

## **Tâches**  

| **Rôle**      | **Tâches**                                     | **Détails**                                   |
|---------------|-----------------------------------------------|----------------------------------------------|
| **Chef**      | `Configuration du projet`                     | Mise en place de l'environnement et des outils nécessaires. |
|               | `Définition des structures de données ADN`    | Représentation des séquences ADN en structures adaptées. |
|               | `Analyse des paramètres de l'algorithme`      | Ajustement des paramètres : mutation, croisement, sélection. |
|               | `Implémentation des structures de voisinage`  | Création des relations pour comparer les alignements locaux. |
|               | `Calcul des statistiques comportementales`    | Évaluation : taux de convergence, précision d'alignement.   |
| **Scribe**    | `Documentation`                               | Définition des structures et explication des étapes de l'algorithme. |

---

## **Livrables**  

1. **Code source** : Implémentation complète de l'algorithme génétique.  
2. **Rapport d'analyse** :  
   - Statistiques sur les performances de l'algorithme.  
   - Visualisation des résultats obtenus.  
3. **Documentation** : Explication des structures de données, paramètres et tests réalisés.

---

## **Technologies Utilisées**  

- **Langage** : Python  
- **Bibliothèques** : NumPy, Matplotlib, Biopython (si nécessaire)  
- **Outils de versionnement** : Git & GitHub  

---

## **Structure du Projet**  

```plaintext
├── src/                   # Code source principal  
│   ├── main.py            # Script principal  
│   ├── dna_data.py        # Gestion des structures de données ADN  
│   ├── genetic_algorithm.py  # Implémentation de l'algorithme génétique  
│   └── utils.py           # Fonctions utilitaires  
│  
├── data/                  # Données d'exemple (séquences ADN)  
│  
├── results/               # Résultats et visualisations  
│  
└── README.md              # Documentation du projet  
