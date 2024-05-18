package ma.zs.carriere.bean.core.mandat;

import java.util.Objects;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "responsabilite_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="responsabilite_detail_seq",sequenceName="responsabilite_detail_seq",allocationSize=1, initialValue = 1)
public class ResponsabiliteDetail  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    private LocalDateTime dateApplicationMin ;

    private LocalDateTime dateApplicationMax ;

    private BigDecimal salaire = BigDecimal.ZERO;

    private Responsabilite responsabilite ;


    public ResponsabiliteDetail(){
        super();
    }

    public ResponsabiliteDetail(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public ResponsabiliteDetail(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="responsabilite_detail_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public LocalDateTime getDateApplicationMin(){
        return this.dateApplicationMin;
    }
    public void setDateApplicationMin(LocalDateTime dateApplicationMin){
        this.dateApplicationMin = dateApplicationMin;
    }
    public LocalDateTime getDateApplicationMax(){
        return this.dateApplicationMax;
    }
    public void setDateApplicationMax(LocalDateTime dateApplicationMax){
        this.dateApplicationMax = dateApplicationMax;
    }
    public BigDecimal getSalaire(){
        return this.salaire;
    }
    public void setSalaire(BigDecimal salaire){
        this.salaire = salaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsabilite")
    public Responsabilite getResponsabilite(){
        return this.responsabilite;
    }
    public void setResponsabilite(Responsabilite responsabilite){
        this.responsabilite = responsabilite;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponsabiliteDetail responsabiliteDetail = (ResponsabiliteDetail) o;
        return id != null && id.equals(responsabiliteDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

