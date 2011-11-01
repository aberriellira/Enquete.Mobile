/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enquete.modelo;

/**
 * Tipos pré-definidos de permissão de acesso.
 * @author Anselmo
 */
public enum TipoPermissao {
	/** O usuário não tem nenhima permissão. */
	Nenhum,
	/** O usuário tem permissão de criação. Por default, esta permissão permite consultar e editar. */
	Criar,
	/** O usuário tem permissão de consulta (listar os objtos). */
	Consultar,
	/** O usuário tem permissão e edição. Por default, esta permissão permite consulta. */
	Editar,
	/** Permissão especial que permite criação, edição, consulta e edição de segurança. */
	Tudo,
	/** O usuario pode setar permissões dos usuários. */
	Seguranca
}
