-- Initialisation des tables

-- Insertion des personnes
INSERT INTO Personne(matricule, nom, prenom, poste, superieur_id) VALUES (1, 'Juan', 'Carlos', 'Développeur', NULL),
                                                                         (2, 'Dupuis', 'Michel', 'Chef de projet', NULL),
                                                                         (3, 'Estbleue', 'Rouge', 'Chercheur', 2),
                                                                         (4, 'Petit', 'Jean', 'Développeur', 1);

INSERT INTO Projet(code, nom, debut, fin) VALUES (1, 'Développement Appli Web', '2025-01-01', NULL),
                                                (2, 'TrucV2', '2024-12-31', '2025-02-12');

INSERT INTO Participation(id, contributeur_matricule, projet_code, role, pourcentage) VALUES (1, 1, 1, 'Manager', 50),
                                                                                             (2, 2, 1, 'Développeur', 35),
                                                                                             (3, 3, 1, 'Recherche', 15),
                                                                                             (4, 1, 2, 'Dev', 99.9);
