package enquete.modelo;

/**
 * Tipos pré-definidos de formas de logout.
 * @author Anselmo
 */
public enum TipoLogoff {
	/** Logoff por expiração da sessão. */
	SessaoExpirada,
	/** Logout feito pelo usuário. */
	LoggoffPeloUsuario,
	/** Logout por tentar forçar o acesso a área não permitida pelas credenciais do usuário. */
	QuebraDeRestricaoDePermissao
}
