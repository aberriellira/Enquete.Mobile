package enquete.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe para persistência de recursos do sistema.
 * Recursos são páginas do site ou acesso ao aplicativo móvel.
 *
 * @author Anselmo
 *
 * @version 1.0
 */
@Entity
@Table(schema="enquete_mobile")
public class Recurso
{
	/**
	 * Construtor padrão da classe
	 */
	public Recurso(){ }

	/**
	 * Identificador do objeto na tabela do banco de dados.
	 * Chave primária.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idRecurso")
	private int Id;
	/**
	 * Método para obter id do recurso a partir de objeto obtido do hibernate.
	 * @return Id do Recurso.
	 */
	public int getId(){ return Id; }
	/**
	 * Método que permite setar o id do recurso.
	 * @param id Novo id do recurso.
	 */
	public void setId(int id) { this.Id = id; }

	/**
	 * Nome do recurso.
	 */
	@Column(length=120)
	private String Nome;
	/**
	 * Método de persistência para obtenção do nome do recurso.
	 * @return Nome do recurso no formato String
	 */
	public String getNome(){ return Nome; }
	/**
	 * Método de persistência para setar o nome do recurso.
	 * @param nome Novo nome a ser definido.
	 */
	public void setNome(String nome){ this.Nome = nome; }

	/**
	 * Local onde este recurso está.
	 * Pode ser o link para uma página web ou um ítem de menu de uma palicação móvel ou desktop.
	 */
	@Column(length=200)
	private String Localizacao;
	/**
	 * Método de persistência para obtenção da localização do recurso.
	 * @return Página onde se encontra o recurso.
	 */
	public String getLocalizacao(){ return Localizacao; }
	/**
	 * Método de persistência que permite definir a localização do recurso.
	 * @param pagina Página xhtml onde está o recurso.
	 */
	public void setLocalizacao(String pagina){ this.Localizacao = pagina; }

	/**
	 * Descrião do recurso.
	 */
	@Column(length=2000)
	private String Descricao;
	/**
	 * Método que permite obter a descrição do recurso.
	 * @return Descrição do recurso.
	 * @exception Se a descrição não foi definida, devo retornar uma exceção.
	 */
	public String getDescricao(){ return Descricao; }
	/**
	 * Método que permite definir a descrição pro recurso.
	 *
	 * @param descricao Descrição do recurso.
	 *
	 * @exception Se a descrição inserida for maior que 2000 caracteres, devo estourar uma
	 * exceção.
	 */
	public void setDescricao(String descricao){ this.Descricao = descricao; }

	/**
	 * Conjunto de autorizações/permissões que apontam para este recurso.
	 */
	@OneToMany(mappedBy="Recurso",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Collection<Autorizacao> Autorizacoes;
	/**
	 * Método de persstência para obter coleção de autorizações associadas a este recurso.
	 * @return Array de permissões que apontam para este recurso.
	 */
	public Collection<Autorizacao> getCollectionAutorizacoes(){ return Autorizacoes; }
	/**
	 * Método de persistência que permite definir/alterar a lista de permissões.
	 * @param autorizacoes Array de autorizações.
	 */
	public void setCollectionAutorizacoes(Collection<Autorizacao> autorizacoes){ this.Autorizacoes = autorizacoes; }
}
