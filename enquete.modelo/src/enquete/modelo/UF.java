package enquete.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para persistência de estados.
 * @author Anselmo Lira
 */
@Entity
@Table(schema="enquete_mobile")
public class UF {

    /**
     * Construtor padrão.
     */
    public UF(){ }

    public UF(String nome, String sigla)
    {
        this.Nome = nome;
        this.Sigla = sigla;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUF")
    private int Id;
    public int getId(){ return Id; }
    public void setId(int id){ this.Id = id; }

    @Column(length = 120)
    private String Nome;
    public String getNome(){ return Nome; }
    public void setNome(String nome){ this.Nome = nome; }

    /**
     * Sigla do estado.
     */
    @Column(length = 2)
    private String Sigla;
    public String getSigla(){ return Sigla; }
    public void setSigla(String sigla){ this.Sigla = sigla; }

    /**
     * Cidades pertencentes ao estado corrente.
     */
    @OneToMany(mappedBy="Estado", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Cidade> Cidades;
    public Collection<Cidade> getCollectionCidade(){ return Cidades; }
    public void setCollectionCidade(Collection<Cidade> municipios){ this.Cidades = municipios; }
}