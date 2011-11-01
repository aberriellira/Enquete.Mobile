package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Cliente", schema = "enquete_mobile")
@PrimaryKeyJoinColumn(name = "idCliente")
public class Cliente extends Pessoa{

    public Cliente(){ }

    public Cliente(String razaoSocial, String cnpj)
    {
	    this.CNPJ = cnpj;
	    this.RazaoSocial = razaoSocial;
    }

    @Column(length=200)
    private String RazaoSocial;
    public String getRazaoSocial(){ return RazaoSocial; }
    public void setRazaoSocial(String razaoSocial){ this.RazaoSocial = razaoSocial; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idUsuario", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Usuario Gerente;
    public Usuario getUsuario(){ return Gerente; }
    public void setUsuario(Usuario gerente){ this.Gerente = gerente; }

    @Column(length=50)
    private String CNPJ;
    public String getCnpj(){ return CNPJ; }
    public void setCNPJ(String cnpj){ this.CNPJ = cnpj; }

    @OneToMany(mappedBy="Cliente", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Usuario> Usuarios;
    public Collection<Usuario> getCollectionUsuario(){ return Usuarios; }
    public void setCollectionUsuario(Collection<Usuario> usuarios){ this.Usuarios = usuarios; }

    @OneToMany(mappedBy="Cliente", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Pesquisa> Pesquisas;
    public Collection<Pesquisa> getCollectionPesquisa(){ return Pesquisas; }
    public void setCollectionPesquisa(Collection<Pesquisa> pesquisas){ this.Pesquisas = pesquisas; }
}
