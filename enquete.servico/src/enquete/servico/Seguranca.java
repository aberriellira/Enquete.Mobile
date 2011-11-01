package enquete.servico;

import enquete.modelo.*;

/**
 *
 * @author Anselmo Lira
 */
public class Seguranca {

    /**
     * Realiza a autenticação do usuário.
     * @param login Login do usuário.
     * @param senha Senha do usuário
     * @return Retorna inteiro indicando se login foi bem-sucedido ou não.
     *
     *
     */
    public static int Login(String login, String senha)
    {
        return 0;
    }

    /**
     * Método para recuperação de senha.
     * Deve criar senha aleatória e enviar por e-mal para o usuario.
     * @param login
     * @return
     */
    public static boolean RecuperaSenha(String login)
    {
        return true;
    }
}