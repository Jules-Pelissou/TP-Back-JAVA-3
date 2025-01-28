package ssii.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    @NonNull
    private Personne contributeur;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    @NonNull
    private Projet projet;

    @NonNull
    private String role;

    @NonNull
    private Float pourcentage;
}
