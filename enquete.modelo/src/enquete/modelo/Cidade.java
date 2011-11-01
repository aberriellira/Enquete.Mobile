package enquete.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para persistência de cidades.
 * @author Anselmo Lira
 */
@Entity
@Table(schema="enquete_mobile")
public class Cidade implements Serializable{

    public Cidade(){ }

    public Cidade(String nome, UF estado, String comentario)
    {
        this.Nome = nome;
        this.Estado = estado;
        this.Comentario = comentario;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCidade")
    private int Id;
    public int getId() { return Id; }
    public void setId(int id) { this.Id = id; }

    @Column(length=120)
    private String Nome;
    public String getNome() { return Nome; }
    public void setNome(String nome) { this.Nome = nome; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUF", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private UF Estado;
    public UF getUF() { return Estado; }
    public void setUF(UF estado) { this.Estado = estado; }

    @Column(length=500)
    private String Comentario;
    public String getComentario() { return Comentario; }
    public void setComentario(String comentario) { this.Comentario = comentario; }

    @OneToMany(mappedBy="Cidade", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection<Pessoa> Pessoas;
    public Collection<Pessoa> getPessoa() { return Pessoas; }
    public void setPessoa(Collection<Pessoa> pessoas) { this.Pessoas = pessoas; }
}
