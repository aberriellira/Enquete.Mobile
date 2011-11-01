package enquete.modelo;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(schema="enquete_mobile")
public class PermissaoQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idPermissaoQuiz")
    private int Id;
    public int getId(){ return Id; }
    public void setId(int id){ this.Id = id; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUsuario", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Usuario Usuario;
    public Usuario getUsuario(){ return Usuario; }
    public void setUsuario(Usuario user){ this.Usuario = user; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idPesquisa", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Pesquisa Pesquisa;
    public Pesquisa getPesquisa(){ return Pesquisa; }
    public void setPesquisa(Pesquisa pesquisa){ this.Pesquisa = pesquisa; }
}

