# **Projet Personnel : Apprentissage des Bases des MLOps**

---

## **Contexte**

Ce projet personnel a pour objectif d'explorer et de maîtriser les concepts fondamentaux des **MLOps** (Machine Learning Operations).  
L'apprentissage s'appuie principalement sur le livre *Hands-On Machine Learning with Scikit-Learn, Keras, and TensorFlow* d'Aurélien Géron.  

Le projet vise à appliquer les bonnes pratiques pour le déploiement, le monitoring et l'automatisation des modèles d'apprentissage automatique.  

---

## **Objectifs du Projet**

1. **Comprendre les concepts clés des MLOps** :  
   - Automatisation du workflow d'entraînement et de déploiement.  
   - Gestion des données et versioning des modèles.  
   - Monitoring des performances des modèles en production.  

2. **Expérimenter les pratiques essentielles** :  
   - Construire des pipelines de Machine Learning.  
   - Déployer des modèles avec des outils comme **Docker** et **TensorFlow Serving**.  
   - Utiliser des plateformes comme **MLflow** pour le suivi d'expériences.

3. **Mettre en pratique le contenu du livre** à travers des exercices concrets et reproductibles.  

---

## **Structure du Projet**

```plaintext
├── mlops-learning/
│   ├── notebooks/
│   │   ├── chapter1_intro.ipynb        # Introduction aux modèles de ML et pipeline simple  
│   │   ├── chapter2_regression.ipynb   # Régression linéaire et arbres de décision  
│   │   ├── chapter3_neural_networks.ipynb  # Réseaux de neurones avec Keras et TensorFlow  
│   │   ├── chapter4_pipeline.ipynb     # Création de pipelines de ML  
│   │   └── chapter5_deployment.ipynb   # Déploiement des modèles avec Docker et TF Serving  
│   │
│   ├── scripts/
│   │   ├── train_model.py              # Script Python pour l'entraînement du modèle  
│   │   ├── evaluate_model.py           # Évaluation des performances du modèle  
│   │   └── deploy_model.py             # Déploiement avec TensorFlow Serving  

