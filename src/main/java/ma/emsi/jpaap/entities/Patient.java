package ma.emsi.jpaap.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity // When we declare an entity
// The class needs to have and ID
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    // Id annotation with auto increment Identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20) // Specifies column length
    private String nom;
    @Temporal(TemporalType.DATE) // Date Format
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
