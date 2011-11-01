package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Entrevistado", schema="enquete_mobile")
@PrimaryKeyJoinColumn(name="idEntrevistado")
public class Entrevistado extends Pessoa{

    public Entrevistado(){ }    

    @Column(length=200)
    private String Atividade;
    public String getAtividade(){ return Atividade; }
    public void setAtividade(String atividade){ this.Atividade = atividade; }

    @OneToMany(mappedBy="Entrevistado", fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Resposta> Respostas;
    public Collection<Resposta> getCollectionQuiz(){ return Respostas; }
    public void setCollectionQuiz(Collection<Resposta> respostas){ this.Respostas = respostas; }
}
