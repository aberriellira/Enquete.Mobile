package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import java.util.IllegalFormatException;
import java.lang.Exception;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para persistência de questões.
 * @author Anselmo
 */
@Entity
@Table(schema="enquete_mobile")
public class Questao {

    /**
     * Construtor padrão.
     */
    public Questao() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idQuestao")
    private int Id;
    public int getId(){ return Id; }
    public void setId(int id){ this.Id = id; }

    private Date DataDeCadastro;
    public Date getDataDeCadastro(){ return this.DataDeCadastro; }
    public void setDataDeCadastro(Date data){ this.DataDeCadastro = data; }

    private int NumeroDaQuestao;
    /**
     * Obter ordem em que esta questão deve aparecer.
     * @return Número indicando a ordem em que esta pergunta deve aparecer na enquete.
     */
    public int getNumeroQuestao(){ return NumeroDaQuestao; }
    /**
     * Define/altera número da questão.
     * @param numero Número indicativo da nova posição em que esta pergunta deve aparecer.
     */
    public void setNumeroQuestao(int numero){ this.NumeroDaQuestao = numero; }

    @Column(length = 300)
    private String Enunciado;
    /**
     * Obtêm enunciado da questão.
     * @return Enunciado.
     */
    public String getEnunciado(){ return Enunciado; }
    /**
     * Define/altera enunciado da questão.
     * @param enunciado Novo enunciado.
     */
    public void setEnunciado(String enunciado){ this.Enunciado = enunciado; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Id",insertable=true,updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Pesquisa Pesquisa;
    /**
     * Obtêm pesquisa à qual pertence a questão corrente.
     * @return Objeto do tipo Pesquisa.
     */
    public Pesquisa getPesquisa(){ return Pesquisa; }
    /**
     * Definir/alterar pesquisa proprietária desta questão.
     * @param pesquisa Objeto do tipo Pesquisa.
     */
    public void setPesquisa(Pesquisa pesquisa){ this.Pesquisa = pesquisa; }

    @OneToMany(mappedBy="Questao",fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Alternativa> Alternativas;
    /**
     * Obter array de alternativas pertencentes a esta questão.
     * @return Coleção de objetos do tipo Alternativa
     */
    public Collection<Alternativa> getCollectionAlternativas(){ return Alternativas; }
    /**
     * Definir/alterar coleção de alternativas.
     * @param alternativas Nova coleção de alternativas.
     */
    public void setCollectionAlternativas(Collection<Alternativa> alternativas){ this.Alternativas = alternativas; }

    /**
     * Obtêm número de alternativas da questão atual.
     * @return Número de alternativas.
     * @exception Estoura se a questão não possui alternativas.
     * Não pode haver questões sem alternativas no sistema.
     */
    public int NumeroDeAlternativas()
    {
	    Collection<Alternativa> alternativas = this.getCollectionAlternativas();
	    if(alternativas.isEmpty())
	    {

	    }
	    return alternativas.size();
    }
}