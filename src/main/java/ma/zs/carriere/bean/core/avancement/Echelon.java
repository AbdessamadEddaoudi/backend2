package ma.zs.carriere.bean.core.avancement;

import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;

import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "echelon")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "echelon_seq", sequenceName = "echelon_seq", allocationSize = 1, initialValue = 1)
public class Echelon extends BaseEntity {

    private Long id;


    @Column(length = 500)
    private String ref;

    @Column(length = 500)
    private String libelle;

    private BigDecimal salaire = BigDecimal.ZERO;

    
    private Echelle echelle;


    public Echelon() {
        super();
    }

    public Echelon(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Echelon(String libelle) {
        this.libelle = libelle;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "echelon_seq")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getSalaire() {
        return this.salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echelle")
    public Echelle getEchelle() {
        return this.echelle;
    }

    public void setEchelle(Echelle echelle) {
        this.echelle = echelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Echelon echelon = (Echelon) o;
        return id != null && id.equals(echelon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

