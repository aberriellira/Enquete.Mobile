package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para persistência de pesquisas.
 * @author Anselmo
 */
@Entity
@Table(schema="enquete_mobile")
public class Pesquisa {

    /**
     * Construtor padrão da classe.
     */
    public Pesquisa(){ }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Id",nullable=false)
    private int Id;
    /**
     * Obtêm identificador da pesquisa no banco (chave primária)
     * @return Identificador da pesquisa.
     */
    public int getId(){ return Id; }
    /**
     * Permite definir um Id para a pesquisa.
     * Não é bom mexer com este método pois pode quebrar a conexão lógica no banco.
     * @param id
     */
    public void setId(int id){ this.Id = id; }

    @Column(length=150)
    private String Nome;
    /**
     * Obtêm nome da pesquisa.
     * @return Texto contendo o nome da pesquisa.
     */
    public String getNome(){ return Nome; }
    /**
     * Define nome para a pesquisa.
     * @param nome Nome que se quer dar à pesquisa.
     */
    public void setNome(String nome){ this.Nome = nome; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idClassificacaoPesquisa", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private ClassificacaoPesquisa Classificacao;
    /**
     * Obtem a classificação/grupo/tipo ao qual pertence a pesquisa corrente.
     * Esta classificação pode ser definida pelo usu´rio ou pelo administrador do sistema.
     * @return
     */
    public ClassificacaoPesquisa getClassificacaoDaPesquisa(){ return Classificacao; }
    /**
     * Altera/define a classificação/grupo ao qual pertence a pesquisa corrente.
     * @param classificacaoDaPesquisa Classificação à qual pertence a pesquisa corrente.
     */
    public void setClassificacaoDaPesquisa(ClassificacaoPesquisa classificacaoDaPesquisa){ this.Classificacao = classificacaoDaPesquisa; }

    @Column(length=1000)
    private String Descricao;
    /**
     * Obtém descrição da pesquisa.
     * @return Texto descritivo da pesquisa.
     */
    public String getDescricao(){ return Descricao; }
    /**
     * Cia/altera descrição da pesquisa corrente.
     * @param descricao Texto descritivo.
     */
    public void setDescricao(String descricao){ this.Descricao = descricao; }

    private boolean IsPublic;
    /**
     * Verifica se a pesquisa corrente é pública ou não.
     * @return Booleano indicando se a pesquisa é pública.
     */
    public boolean getIsPublic(){ return IsPublic; }
    /**
     * Método para definir se a pesquisa é pública ou não.
     * @param publico Booleano.
     */
    public void setIsPublic(boolean publico){ this.IsPublic = publico; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idCliente", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Cliente Cliente;
    /**
     * Método para obter cliente ao qual pertence a pesquisa corrente.
     * @return Objeto do tipo Cliente.
     */
    public Cliente getCliente(){ return Cliente; }
    /**
     * Método para definir cliente dono da pesquisa corrente.
     * @param cliente Objeto do tipo Cliente.
     */
    public void setCliente(Cliente cliente){ this.Cliente = cliente; }

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDeCadastro;
    public Date getDataDeCadastro(){ return DataDeCadastro; }
    public void setDataDeCadastro(Date data){ this.DataDeCadastro = data; }

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDaUltimaAtualizacao;
    public Date getDataDaUltimaAtualizacao(){ return DataDaUltimaAtualizacao; }
    public void setDataDaUltimaAtualizacao(Date data){ this.DataDaUltimaAtualizacao = data; }

    @OneToMany(mappedBy="Pesquisa", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Questao> Questoes;
    /**
     * Método para obter array de questões pertencentes à pesquisa corrente
     * @return Coleção de questões da pesquisa corrente.
     */
    public Collection<Questao> getCollectionQuestao(){ return Questoes; }
    /**
     * Método para setar coleção de perguntas da pesquisa corrente.
     * @param questoes Array de questões.
     */
    public void setCollectionQuestao(Collection<Questao> questoes){ this.Questoes = questoes; }

    /**
     * Método que permite obter total de questões existentes em dada pesquisa.
     * @return Total de questões da pesquisa corrente.
     */
    public int TotalDeQuestao()
    {
	    Collection<Questao> questoes = this.getCollectionQuestao();
	    return questoes.size();
    }
}

