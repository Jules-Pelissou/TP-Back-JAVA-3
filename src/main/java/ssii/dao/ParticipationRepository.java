package ssii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssii.entity.Participation;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    // Personnes par projet
    @Query("SELECT p.projet.code as IdProj, p.contributeur.nom as Nom, p.contributeur.prenom as Prenom, p.contributeur.matricule as MatriculePers FROM Participation p WHERE p.projet.code = :codeProjet")
    List<PersonneParProjet> personneParProjet(Integer codeProjet);

    // Pourcentage personne Total Projet
    @Query("SELECT SUM(p.pourcentage) FROM Participation p WHERE p.contributeur.matricule = :codePersonne")
    Integer pourcentagePersonne(Integer codePersonne);

    // Nb Projet par personne
    @Query("SELECT SUM(p.projet.code) FROM Participation p")
    Integer nbProjetParPers(Integer codePersonne);
}