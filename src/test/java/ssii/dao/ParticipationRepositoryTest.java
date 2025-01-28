package ssii.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ssii.dao.ParticipationRepository;
import ssii.dao.PersonneRepository;
import ssii.dao.ProjetRepository;
import ssii.entity.Participation;
import ssii.entity.Personne;
import ssii.entity.Projet;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Ce test est basé sur le jeu de données dans "test_data.sql"
class ParticipationRepositoryTest {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @BeforeEach
    void setUp() {

        var personne = new Personne("Dupont", "Michel");
        personne.setPoste("Developpeur");
        personne.setSubordonnes(new ArrayList<>());
        personne.setSuperieur(null);
        personneRepository.save(personne);

        var personne2 = new Personne("Juan", "Carlos");
        personne2.setPoste("Chef de proj");
        personne2.setSubordonnes(new ArrayList<>());
        personne2.setSuperieur(null);
        personneRepository.save(personne2);

        var personne3 = new Personne("EstBleue", "Rouge");
        personne3.setPoste("Chef de proj");
        personne3.setSubordonnes(new ArrayList<>());
        personne3.setSuperieur(null);
        personneRepository.save(personne3);

        var personne4 = new Personne("Martel", "Charles");
        personne4.setPoste("Directeur");
        personne4.setSubordonnes(new ArrayList<>());
        personne4.setSuperieur(null);
        personne4.getSubordonnes().add(personne3);
        personneRepository.save(personne4);

        personne3.setSuperieur(personne4);
        personneRepository.save(personne3);

        // Création des projets

        var projet1 = new Projet();
        projet1.setNom("Projet A");
        projet1.setDebut(LocalDate.now());
        projet1 = projetRepository.save(projet1);

        var projet2 = new Projet();
        projet2.setNom("Projet B");
        projet2.setDebut(LocalDate.now());
        projet2 = projetRepository.save(projet2);

        var projet3 = new Projet();
        projet3.setNom("Projet C");
        projet3.setDebut(LocalDate.now());
        projet3 = projetRepository.save(projet3);

        var projet4 = new Projet();
        projet4.setNom("Projet D");
        projet4.setDebut(LocalDate.now());
        projet4 = projetRepository.save(projet4);

        // Participation à trois projets
        participationRepository.save(new Participation(personne, projet1, "Role1", 30f));
        participationRepository.save(new Participation(personne, projet2, "Role2", 30f));
        participationRepository.save(new Participation(personne, projet3, "Role3", 30f));


        // Participation au projet
        var participation1 = new Participation();
        participation1.setContributeur(personne);
        participation1.setProjet(projet1);
        participation1.setRole("Développeur");
        participation1.setPourcentage(50f);
        participationRepository.save(participation1);

        // Tentative d'ajouter une deuxième participation pour le même projet
        var participation2 = new Participation();
        participation2.setContributeur(personne);
        participation2.setProjet(projet2);
        participation2.setRole("Manager");
        participation2.setPourcentage(20f);
        participationRepository.save(participation2);
    }

    @Test
    void testPersonneDeuxFoisMemeProjet() {

        var participation5 = new Participation();
        participation5.setContributeur(personne);
        participation5.setProjet(projet1);
        participation5.setRole("Manager");
        participation5.setPourcentage(1f);

        try {
            participationRepository.save(participation5);
            fail("Un contributeur ne peut pas participer plus de deux fois au même projet");
        }catch (Exception e) {

        }
        
    }

    @Test
    void testOverCentPourcent() {
        var participation5 = new Participation();
        participation5.setContributeur(personne);
        participation5.setProjet(projet3);
        participation5.setRole("Manager");
        participation5.setPourcentage(40f);

        try {
            participationRepository.save(participation5);
            fail("Cela ne doit pas fonctionner");
        }catch (Exception e) {

        }
    }

    @Test
    void testPersonneTroisProjet() {


        Exception exception = assertThrows(Exception.class, () -> {
            participationRepository.save(participation4);
        });

        assertNotNull(exception, "Une exception doit être levée si une personne participe à plus de 3 projets");
    }
}
