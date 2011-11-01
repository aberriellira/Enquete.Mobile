/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enquete.modelo;

import enquete.modelo.GrupoUsuario;
import enquete.modelo.Recurso;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Associa cada grupo de usuário a um recurso que ele pode ver.
 * Cada recurso corresponde a uma página (ou ítem de menu) que será exibio ou não.
 * @author Anselmo
 */
@Entity
@Table(schema="enquete_mobile")
public class Permissao {

	public Permissao(){ }

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idPermissao")
	private int Id;
	public int getId(){ return Id; }
	public void setId(int id){ this.Id = id; }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idGrupoUsuario", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.ALL)
	private GrupoUsuario GrupoDeUsuario;
	public GrupoUsuario getGrupoDeUsuario(){ return GrupoDeUsuario; }
	public void setGrupoDeUsuario(GrupoUsuario grupo){ this.GrupoDeUsuario = grupo; }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idRecurso", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.ALL)
	private Recurso Recurso;
	public Recurso getRecurso(){ return Recurso; }
	public void setRecurso(Recurso recurso){ this.Recurso = recurso; }

	private TipoPermissao TipoDePermissao;
	public TipoPermissao getTipoDePermissao(){ return TipoDePermissao; }
	public void setTipoDePermissao(TipoPermissao tipoDePermissao){ this.TipoDePermissao = tipoDePermissao; }
}
