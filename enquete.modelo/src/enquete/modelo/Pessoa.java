package enquete.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para a persistência de pessoas.
 * @author Anselmo
 */
@Entity
@Table(schema="enquete_mobile")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    public Pessoa(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idPessoa")
    private int Id;
    public int getId(){ return Id; }
    public void setId(int id){ this.Id = id; }

    @Column(length=120)
    private String Nome;
    public String getNome(){ return Nome; }
    public void setNome(String nome){ this.Nome = nome; }

    @Column(length=150)
    private String EMailPrincipal;
    public String getEMailPrincipal(){ return EMailPrincipal; }
    public void setEMailPrincipal(String email){ this.EMailPrincipal = email; }

    @Column(length=150)
    private String EMailSecundario;
    public String getEMailSecundario(){ return EMailSecundario; }
    public void setEMailSecundario(String email){ this.EMailSecundario = email; }

    @Column(length=5)
    private String DDD;
    public String getDDD(){ return DDD; }
    public void setDDD(String ddd){ this.DDD = ddd; }

    @Column(length=12)
    private String NumeroTelefone;
    public String getNumeroTelefone(){ return NumeroTelefone; }
    public void setNumeroTelefone(String numero){ this.NumeroTelefone = numero; }

    @Column(length=300)
    private String Logradouro;
    public String getLogradouro(){ return Logradouro; }
    public void setLogradouro(String logradouro){ this.Logradouro = logradouro; }

    @Column(length=10)
    private String NumeroEndereco;
    public String getNumeroEndereco(){ return NumeroEndereco; }
    public void setNumeroEndereco(String numero){ this.NumeroEndereco = numero; }

    @Column(length=100)
    private String ComplementoEndereco;
    public String getComplementoEndereco(){ return ComplementoEndereco; }
    public void setComplementoEndereco(String complemento){ this.ComplementoEndereco = complemento; }

    @Column(length=10)
    private String CEP;
    public String getCEP(){ return CEP; }
    public void setCEP(String cep){ this.CEP = cep; }

    @Column(length=130)
    private String Bairro;
    public String getBairro(){ return Bairro; }
    public void setBairro(String bairro){ this.Bairro = bairro; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCidade", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Cidade Cidade;
    public Cidade getCidade(){ return Cidade; }
    public void setCidade(Cidade cidade){ this.Cidade = cidade; }
}