package ssii.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    private String poste;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superieur")
    @ToString.Exclude
    private List<Personne> subordonnes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "superieur_id")
    private Personne superieur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contributeur")
    @ToString.Exclude
    private List<Participation> contributeur = new ArrayList<>();

}

