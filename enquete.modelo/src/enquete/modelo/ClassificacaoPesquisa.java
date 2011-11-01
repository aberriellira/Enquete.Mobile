package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(schema="enquete_mobile")
public class ClassificacaoPesquisa {

    public ClassificacaoPesquisa(){ }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idClassificacaoPesquisa", nullable=false)
    private int Id;
    public int getId(){ return Id; }
    public void setId(int id){ this.Id = id; }

    @Column(length=100)
    private String Nome;
    public String getNome(){ return Nome; }
    public void setNome(String nome){ this.Nome = nome; }

    @Column(length=1000)
    private String Descricao;
    public String getDescricao(){ return Descricao; }
    public void setDescricao(String descricao){ this.Descricao = descricao; }

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDeCadastro;
    public Date getDataDeCadastro(){ return DataDeCadastro; }
    public void setDataDeCadastro(Date data){ this.DataDeCadastro = data; }

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDaUltimaAtualizacao;
    public Date getDataDaUltimaAtualizacao(){ return DataDaUltimaAtualizacao; }
    public void setDataDaUltimaAtualizacao(Date data){ this.DataDaUltimaAtualizacao = data; }

    @OneToMany(mappedBy="Id", fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Pesquisa> Pesquisas;
    public Collection<Pesquisa> getCollectionPesquisa(){ return Pesquisas; }
    public void setCollectionPesquisa(Collection<Pesquisa> pesquisas){ this.Pesquisas = pesquisas; }
}
