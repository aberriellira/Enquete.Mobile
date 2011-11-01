package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import java.lang.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(schema="enquete_mobile")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Usuario extends Pessoa{

    public Usuario(){ }

    @Column(length=20)
    private String Login;
    public String getLogin (){ return Login; }
    public void setLogin(String login) { this.Login = login; }

    @Column(length=15)
    private String Senha;
    public String getSenha(){ return Senha; }
    public void setSenha(String senha){ this.Senha = senha; }

    @Temporal(TemporalType.DATE)
    private Date DataNascimento;
    public Date getDataNascimento(){ return DataNascimento; }
    public void setDataNascimento(Date data){ this.DataNascimento = data; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCliente")
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Cliente Cliente;
    public Cliente getCliente(){ return Cliente; }
    public void setCliente(Cliente cliente){ this.Cliente = cliente; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idGrupoUsuario")
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private GrupoUsuario Grupo;
    public GrupoUsuario getGrupo(){ return Grupo; }
    public void setGrupo(GrupoUsuario grupo){ this.Grupo = grupo; }

    @Column(length=14)
    private String CPF;
    public String getCpf(){ return CPF; }
    public void setCpf(String cpf){ this.CPF = cpf; }

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDeCadastro;
    public Date getDataDeCadastro(){ return DataDeCadastro; }
    public void setDataDeCadastro(Date data){ this.DataDeCadastro = data; }

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataDaUltimaAtualizacao;
    public Date getDataDaUltimaAtualizacao(){ return DataDaUltimaAtualizacao; }
    public void setDataDaUltimaAtualizacao(Date data){ this.DataDaUltimaAtualizacao = data; }

    @OneToMany(mappedBy="Entrevistador", fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Resposta> Respostas;
    public Collection<Resposta> getCollectionResposta(){ return Respostas; }
    public void setCollectionResposta(Collection<Resposta> respostas){ this.Respostas = respostas; }
}