package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe de persistência para as alternativas das questões.
 * @author Anselmo
 */
@Entity
@Table(schema="enquete_mobile")
public class Alternativa {

    /**
     * Construtor padrão.
     */
    public Alternativa() { }

    /**
     * Id do objeto no banco (primary key da tabela).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAlternativaQuestao", nullable=false)
    private int Id;
    /**
     * Método de persistência para obter o id do objeto no banco.
     * @return Id do objeto.
     */
    public int getId() { return Id; }
    /**
     * Método de persistência para setar/alterar o id do objeto no banco.
     * @param id Identificador do objto no banco.
     */
    public void setId(int id){ this.Id = id; }

    /**
     * Valor da alternativa, a ser mostrada na tela.
     */
    @Column(length=100)
    private String ValorAlternativa;
    /**
     * Método de persistência para obter o valor da alternativa.
     * @return String contendo o texto a ser mostrado na tela.
     */
    public String getValorAlternativa(){ return ValorAlternativa; }
    /**
     * 
     * @param valorAlternativa
     */
    public void setValorAlternativa(String valorAlternativa){ this.ValorAlternativa = valorAlternativa; }

    private int NumeroAlternativa;
    public int getNumeroAlternativa(){ return NumeroAlternativa; }
    public void setNumeroAlternativa(int numeroAlternativa){ this.NumeroAlternativa = numeroAlternativa; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idQuestao",insertable=true,updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Questao Questao;
    public Questao getQuestao(){ return Questao; }
    public void setQuestao(Questao questao){ this.Questao = questao; }

    @OneToMany(mappedBy="AlternativaEscolhida", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Resposta> Quiz;
    public Collection<Resposta> getCollectionQuiz(){ return Quiz; }
    public void setCollectionQuiz(Collection<Resposta> quiz){ this.Quiz = quiz; }
}

