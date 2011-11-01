package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe de persistência de respostas dos usuários às pesquisas.
 * @author Anselmo
 */
@Entity
@Table(schema="enquete_mobile")
public class Resposta {

    /**
     * Construtor padrão da classe.
     */
    public Resposta(){ }

    /**
     * Id do objeto no banco.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idQuiz")
    private int Id;
    /**
     * Método para obtenção do Id do objeto no banco.
     * @return Id do objeto no banco.
     */
    public int getId(){ return Id; }
    /**
     * Método de persstência para definir/alterar o Id do objeto no banco.
     * @param id Novo Id.
     * @exception Se alterado o Id, deve gerar exceção (identificador não pode ser mexido).
     */
    public void setId(int id){ this.Id = id; }

    /**
     * Entrevistado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idEntrevistadoQuiz", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Entrevistado Entrevistado;
    /**
     * Método de persistência para obter o entrevistado que deu a resposta corrente.
     * @return Objeto Entrevstado.
     */
    public Entrevistado getEntrevistado(){ return Entrevistado; }
    /**
     * Método de persistência para definir/alterar o entrevistado que deu a resposta.
     * @param entrevistado Entrevistado que deu a resposta.
     */
    public void setEntrevistado(Entrevistado entrevistado){ this.Entrevistado = entrevistado; }

    /**
     * Alternativa escolhida.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idAlternativaQuestao", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Alternativa AlternativaEscolhida;
    /**
     * Método de persistência para obter a alternativa escolhida pelo entrevistado.
     *
     * @return Alternativa escolhida.
     * Vale lembrar que a questão (e, consequentemente, a pesquisa) serão obtidas pos referência à Alternativa.
     */
    public Alternativa getAlternativaEscolhida(){ return AlternativaEscolhida; }
    /**
     * Método de persistência para definir/alterar a alternativa escolhida.
     * @param alternativa
     */
    public void setAlternativaEscolhida(Alternativa alternativa){ this.AlternativaEscolhida = alternativa; }

    /**
     * Entrevistador responsável pela enquete.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idUsuario", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Usuario Entrevistador;
    /**
     * Método de persistência para obter o usuário que realizou a entrevista.
     * @return Entrevistador.
     */
    public Usuario getEntrevistador(){ return Entrevistador; }
    /**
     * Método de persstência para definir/alterar o entrevstador.
     * @param entrevistador Usuário que realizou a entrevista.
     * Este método deve ser chamado somente pelo web service por intermédio da aplicação mobile.
     */
    public void setEntrevistador(Usuario entrevistador){ this.Entrevistador = entrevistador; }

    /**
     * Data e Hora em que o registro foi inserido.
     * Este objeto deve ser usado somente para fins de log de atividades dos usuários.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDeRegistro;
    /**
     * Método de persistência que permite obter a data/hora em que o registro foi inserido.
     * @return Data/Hora em que o registro foi inserido.
     */
    public Date getDataDeRegitro(){ return DataDeRegistro; }
    /**
     * Método de persistência para definir/alterar a data/hora em que o registro foi inserido.
     * Este método deve ser chamado somente pelo web service.
     * @param data Data/Hora da inserção do registro.
     */
    public void setDataDeRegistro(Date data){ this.DataDeRegistro = data; }
}