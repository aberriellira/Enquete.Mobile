package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para persistência de grupos de usuários.
 * @author Anselmo Lira
 */
@Entity
@Table(schema="enquete_mobile")
public class GrupoUsuario {

    public GrupoUsuario(){ }

    /**
     * Id do objeto.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idGrupoUsuario")
    private int Id;
    /**
     * Método de persstência para obter o Id do objeto.
     * @return Id do objeto no banco (primary key)
     */
    public int getId(){ return Id; }
    /**
     * Método de persistência para setar o Id do objeto.
     *
     * @param id Novo Id do objeto.
     *
     * @exception Se Id já utilizado, deve retrnar exceção.
     */
    public void setId(int id){ this.Id = id; }

    /**
     * Nome do grupo de usuário.
     */
    private String Nome;
    public String getString(){ return Nome; }
    public void setNome(String nome){ this.Nome = nome; }

    private String Descricao;
    public String getDescricao(){ return Descricao; }
    public void setDescricao(String descricao){ this.Descricao = descricao; }

    @OneToMany(mappedBy="Grupo",fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Usuario> Usuarios;
    public Collection<Usuario> getCollectionUsuarios(){ return Usuarios; }
    public void setCollectionUsuarios(Collection<Usuario> usuarios){ this.Usuarios = usuarios; }


    @OneToMany(mappedBy="GrupoDeUsuario",fetch=FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Autorizacao> Autorizacoes;
    public Collection<Autorizacao> getCollectionAutorizacao(){ return Autorizacoes; }
    public void setCollectionAutorizacao(Collection<Autorizacao> autorizacoes){ this.Autorizacoes = autorizacoes; }
}
