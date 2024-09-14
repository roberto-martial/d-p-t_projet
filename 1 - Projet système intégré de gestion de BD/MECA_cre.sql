/*
-- =========================================================================== A
Activité : MCEBD.SQL.A
Trimestre : 2021-3
Composant : MECA_vre.sql
Encodage : UTF-8, sans BOM; fin de ligne Unix (LF)
Plateforme : PostgreSQL 9.4 à 14
Responsables : Luc.Lavoie@USherbrooke.ca; Christina.Khnaisser@USherbrooke.ca
Version : 0.1.0a
Statut : solution préliminaire
-- =========================================================================== A
*/

/*
-- =========================================================================== B
   Modélisation des effectifs cliniques autonomes.
-- =========================================================================== B
*/

--
-- =================== Unité organisationnelle
--
create domain Unite_Code
  VARCHAR(8)
  constraint unite_code_dom check (value similar to '[A-Z]{5}[0-9]{3}');
comment on domain Unite_code is $$
Code unique d’une unité organisationnelle.
$$;
--
create domain Unite_Nom
  VARCHAR(240);
comment on domain Unite_Nom is $$
Nom d’une unité organisationnelle.
$$;
--
create table Unite
(
  unite_code Unite_Code not null,
  unite_nom  Unite_Nom not null,
  constraint unite_cc0 primary key (unite_code),
  constraint unite_cc1 unique (unite_nom)
);
comment on table Unite is $$
Une unité organisationnelle est identifiée par "id" et nommée par "nom".
$$;
--
-- =================== Structure organisationnelle
--
create table SOrg
(
  unite Unite_Code not null,
  super_unite Unite_Code not null,
  constraint sorg_cc0 primary key (unite, super_unite),
  constraint sorg_cr0 foreign key (unite) references unite(unite_code),
  constraint sorg_cr1 foreign key (super_unite) references unite(unite_code)
);
comment on table SOrg is $$
Une unité "unite" dépend administrativement de l’unité "super_unite".
La structure doit strictement respecter une topologie hiérarchique
(un graphe connexe sans cycle).
$$;
--
-- =================== Effectif clinique autonome
--
create domain Eff_ID
  INTEGER
  constraint eff_id_dom check (value > 0);
comment on domain Unite_nom is $$
Identifiant artificiel d’un effectif médical.
$$;
--
create table Effectif
(
  eff_id INTEGER not null generated always as identity (minvalue 1),
  nom text not null,
  prenom text not null,
  dateNaissance Date not null,
  constraint effectif_cc0 primary key (eff_id)
);
comment on table Effectif is $$
Un effectif est identifié par "id" et nommé par "nom" et "prenom" est né le "dateNaissance".
$$;
--
-- =================== Type d’activité
--
create domain Type_activite_Code
  VARCHAR(7)
  constraint type_activite_code_dom check (value similar to '[a-zA-Z][a-zA-Z0-9]{2,7}');
comment on domain Type_activite_Code is $$
Code unique d’un type d’activité.
$$;
--
create domain Type_activite_Nom
  VARCHAR(40);
comment on domain Type_activite_Nom is $$
Nom de référence d’un type d’activité.
$$;
--
create table Type_activite
(
  type Type_activite_Code not null,
  nom Type_activite_Nom not null,
  description TEXT not null,
  actif BOOLEAN not null,
  constraint Type_activite_cc0 primary key (type),
  constraint Type_activite_cc1 unique (nom)
);
comment on table Type_activite is $$
Un type d’activités.
$$;
--
-- =================== Permis
--
create domain Permis_ID
  INTEGER;
comment on domain Permis_ID is $$
Identifiant artificiel unique d’un permis d’exercice.
$$;
--
create domain Permis_CODE
  VARCHAR(7)
  constraint permis_code_dom check (value similar to '[a-zA-Z]{4}[0-9]{3}');
comment on domain Permis_CODE is $$
Code unique d’un permis d’exercice.
$$;
--
create table Permis
(
  permis_id INTEGER not null generated always as identity (minvalue 1),
  permis_code Permis_CODE not null,
  effectif Eff_ID not null,
  valide_debut DATE not null,
  valide_fin DATE not null,
  constraint permis_cc0 primary key (permis_id),
  constraint permis_cr0 foreign key (effectif) references effectif(eff_id)
);
comment on table Permis is $$
Le permis d’exercice d’un effectif pour une durée de validité spécifique.
Une effectif peut détenir plus d’un permis, mais leurs périodes de validité ne peuvent se chevaucher.
$$;
--
create table Permis_activite
(
  permis Permis_ID not null,
  type_activite Type_activite_CODE not null,
  constraint permis_activite_c00 primary key (permis, type_activite),
  constraint permis_activite_cr0 foreign key (permis) references Permis(permis_id),
  constraint permis_activite_cr1 foreign key (type_activite) references Type_activite(type)
);
comment on table Permis_activite is $$
Le permis permet d’exercer pour un ou plusieurs types d’acitivtés.
$$;
--
-- =================== Prévision d’activité
--
create domain Prevision_ID
  INTEGER
  constraint Prevision_ID_dom check (value > 0);
comment on domain Prevision_ID is $$
Identifiant unique artificiel d’une prévision.
$$;
--
create domain Prevision_quantite
  NUMERIC(12,2)
  constraint Prevision_quantite_dom check (value > 0);
comment on domain Prevision_quantite is $$
La quantité de travail d’une prévision est exprimée en heures-personnes
(qui est plutôt une mesure de l’effort, mais les us et coutumes du réseau sont ainsi).
$$;
--
create table Prevision
(
  prevision_id INTEGER not null generated always as identity (minvalue 1),
  prevision_date DATE,
  effectif Eff_ID not null,
  unite Unite_Code not null,
  type_activite Type_activite_CODE not null,
  quantite Prevision_quantite not null,
  periode_debut DATE not null,
  periode_fin DATE not null,
  constraint prevision_cc0 primary key (prevision_id),
  constraint prevision_cr0 foreign key (effectif) references Effectif(eff_id),
  constraint prevision_cr1 foreign key (unite) references Unite(unite_code),
  constraint prevision_cr2 foreign key (type_activite) references Type_activite(type)
);
comment on table Prevision is $$
Une prévision d’activité estime la quantité de travail d’un type d’activités à être réalisée
par un effectif médical au sein d’une unité organisationnelle pendant une période donnée.
Une prévision ne peut être saisie que si l’effectif possède un permis valide permettant
d’accomplir le type d’activité durant toute la période prévue.
$$;

/*
-- =========================================================================== Z
Contributeurs :
  (CK) Christina.Khnaisser@USherbrooke.ca,
  (LL) Luc.Lavoie@USherbrooke.ca

Adresse, droits d’auteur et copyright :
  Groupe Groupe Μῆτις (Métis)
  Département d’informatique
  Faculté des sciences
  Université de Sherbrooke
  Sherbrooke (Québec)  J1K 2R1
  Canada
  http://info.usherbrooke.ca/llavoie/
  [CC-BY-NC-SA-4.0 (http://creativecommons.org/licenses/by-nc-sa/4.0)]

Tâches projetées :
S.O.

Tâches réalisées :
2021-11-14 (LL) : Revue.
2021-11-04 (CK) : Création initiale.

Références :
[ddv] http://info.usherbrooke.ca/llavoie/enseignement/Exemples/MECA/
      MECA_DDV.pdf
[edv] http://info.usherbrooke.ca/llavoie/enseignement/Exemples/MECA/
      MECA_EDV.pdf (à venir)
[pre] http://info.usherbrooke.ca/llavoie/enseignement/Exemples/MECA/
      MECA_PRE.pdf (à venir)
[scl] http://info.usherbrooke.ca/llavoie/enseignement/Exemples/MECA/
      MECA_SCL.pdf (à venir)
[std] http://info.usherbrooke.ca/llavoie/enseignement/Modules/
      BD190-STD-SQL-01_NDC.pdf

-- -----------------------------------------------------------------------------
-- fin de MECA_cre.sql
-- =========================================================================== Z
*/
