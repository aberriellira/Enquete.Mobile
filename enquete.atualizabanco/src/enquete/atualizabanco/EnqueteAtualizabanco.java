/*
 * Anotações do Java:
 * @Deprecated - elemento do código está em desuso
 * @Documented - indica que o elemento anotado deve ser documentado
 */

/*
 * Propriedades para o hibernate.cfg.xml:
 *  -> hibernate.dialect: dialeto SQL específico do banco de dados a ser utilizado
 *  -> hibernate.connection.driver.class: nome da classe do driver JDBC do banco de dados a ser utilizado
 *  -> hibernate.connection.url: URL de conexão específica do banco que está sendo utilizado
 *  -> hibernate.connection.username: nome de usuário para conexão com o banco
 *  -> hibernate.connection.password: senha do usuário do banco
 *  -> hibernate.connection.pool_size: tamanho do pool de conexões
 *  -> hibernate.connection.isolation: nível de isolamento
 *  -> hibernate.show_sql: define se o SQL gerado será mostrado
 */

/*
 * Anotações do Annotations:
 * @Entity - classe persistente mapeada
 * @Id - indica a chave primária
 * @GeneratedValue - definição automática para oo valor do identificador
 * @Transient - atributo não será persistido pro banco
 * @Column - configurações associadas à coluna persistida pro banco
 * @Temporal - mapeamento de datas e horas
 * @OneToMany - associação 1-para-n
 *      + exemplo Universidade-Centros (1 universidade tem vários Centros)
 * @ManyToOne - asociação n-para-1
 *      + exemplo Centros-Universidade ( vários Centros podem pertencer a uma única Universidade)
 */

/*
 * Atributos de @Column:
 *  -> name: nome da coluna no banco
 *  -> unique: rstrição de unicidade (default false)
 *  -> nullable: indica de a coluna pode assumir valor nulo (default true)
 *  -> length: tamanho máximo da coluna (inteiro)
 *  -> precision: precisão decimal dos possíveis valores para a coluna
 *  -> insertable: indica de atributo será inserido no momento da inserção de uma linha na tabela (default true)
 *  -> updatable: indica se atributo atualizado no momento da atualização de uma linha na tabela
 */

/*
 * Valores possíveis de @Temporal:
 *  -> TemporalType.DATE: mapear datas
 *  -> TemporalType.TIME: mapear hora
 *  -> TemporalType.TIMESTAMP: mapear datas e horas
 */

/*
 * Atributos de @OneToMany:
 *  -> mappedBy: nome do atributo na classe relacionada (ou do tipo da coleção) que se relaciona com a classe
 *     que tem o mapeamento 1-n
 *  -> fetch: indica quando o atributo será trazido da base
 */

/*
 * Valores possíveis de fetch (1-n):
 *  -> FetchType.EAGER: sempre que o objeto "pai" for trazido da base, seu conteúdo também será trazido
 *  -> FetchType.Lazy: conteúdo do objeto "pai" só será trazido quando for acessado pela primeira vez
 */

package enquete.atualizabanco;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import enquete.modelo.*;
import enquete.dao.HibernateUtil;

/**
 * Classe de métodos de atualização da base de dados.
 * @author Anselmo
 */
public class EnqueteAtualizabanco {

    /**
     * Método pricipal, que cria as tabelas.
     * @param args Parâmetros de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
	// exibindo uma mensagem na tela
        System.out.println("Um teste.");

	System.out.println("Crindo uma configuração.");
        AnnotationConfiguration acfg = new AnnotationConfiguration();

	System.out.println("Adicionando as classes à configuração.");
	acfg.addAnnotatedClass(enquete.modelo.Autorizacao.class);
        acfg.addAnnotatedClass(enquete.modelo.Alternativa.class);
        acfg.addAnnotatedClass(enquete.modelo.Cidade.class);
        acfg.addAnnotatedClass(enquete.modelo.ClassificacaoPesquisa.class);
        acfg.addAnnotatedClass(enquete.modelo.Cliente.class);
        acfg.addAnnotatedClass(enquete.modelo.Entrevistado.class);
        acfg.addAnnotatedClass(enquete.modelo.GrupoUsuario.class);
	acfg.addAnnotatedClass(enquete.modelo.Permissao.class);
        acfg.addAnnotatedClass(enquete.modelo.PermissaoQuiz.class);
        acfg.addAnnotatedClass(enquete.modelo.Pesquisa.class);
        acfg.addAnnotatedClass(enquete.modelo.Pessoa.class);
        acfg.addAnnotatedClass(enquete.modelo.Questao.class);
	acfg.addAnnotatedClass(enquete.modelo.Recurso.class);
        acfg.addAnnotatedClass(enquete.modelo.Resposta.class);
        acfg.addAnnotatedClass(enquete.modelo.UF.class);
        acfg.addAnnotatedClass(enquete.modelo.Usuario.class);
	// criando o schema
        SchemaExport se = new SchemaExport(acfg);
        se.create(true, true);

	System.out.println("Criando dados de inicialização.");
	SessionFactory sf = InicializaHibernate();
	CriaDadosExemplo(enquete.dao.HibernateUtil.getSessionFactory());
    }

    /**
     * Inicialização do Hibernate.
     * @return Fábrica de sessões para conexão com o banco.
     */
    public static SessionFactory InicializaHibernate()
    {
	System.out.println("Inicializando o hibernate.");
	System.out.println("Criando configuração.");
	AnnotationConfiguration cfg = new AnnotationConfiguration();
	cfg.configure();
	System.out.println("Criando SessionFactory");
        SessionFactory sf = cfg.buildSessionFactory();
	return sf;
    }

    /**
     * Cria dados de inicialização (exemplos)
     * @param sf SessionFactory para conexão com o banco.
     */
    public static void CriaDadosExemplo(SessionFactory sf)
    {
	System.out.println("Abrindo sessão.");
        Session session = sf.openSession();
	System.out.println("Inicializando transação.");
        Transaction tx = session.beginTransaction();

	UF estado = new UF();
	estado.setNome("Rio de Janeiro");
	estado.setSigla("RJ");

	Cidade cidade = new Cidade();
	cidade.setNome("Rio de Janeiro");
	cidade.setUF(estado);

	Cliente cliente = new Cliente();
	cliente.setNome("Codax.");
	cliente.setRazaoSocial("Codax Soluções Tecnológicas.");
	cliente.setNumeroTelefone("2222-1035");
	cliente.setDDD("21");
	cliente.setEMailPrincipal("comercial@codax.com.br");
	cliente.setLogradouro("Rua da Quitanda");
	cliente.setNumeroEndereco("20");
	cliente.setComplementoEndereco("Sala 808");
	cliente.setBairro("Centro");
	cliente.setCidade(cidade);

	Usuario user = new Usuario();
	user.setNome("Administrador");
	user.setLogin("adm");
	user.setSenha("adm123");
	user.setCliente(cliente);
	cliente.setUsuario(user);

	GrupoUsuario grupoAdm = new GrupoUsuario();
	grupoAdm.setNome("Administrador");

	Recurso recursoUsuario = new Recurso();
	Recurso recursoPesquisa = new Recurso();
	Recurso recursoCliente = new Recurso();
	Recurso recursoClassificacao = new Recurso();
	recursoUsuario.setNome("Usuario");
	recursoUsuario.setLocalizacao("/Restrict/usuario.xhtml");
	recursoUsuario.setDescricao("Página de gerenciamento de usuários.");
	recursoPesquisa.setNome("Pesquisa");
	recursoPesquisa.setLocalizacao("/Restrict/pesquisa.xhtml");
	recursoPesquisa.setDescricao("Página de gerenciamento de pesquisas.");
	recursoCliente.setLocalizacao("/Restrict/cliente.xhtml");
	recursoCliente.setNome("Cliente");
	recursoCliente.setDescricao("Página de visualização e edição dos dados do cliente.");
	recursoClassificacao.setNome("Classificação");
	recursoClassificacao.setLocalizacao("/Restrict/categorias_pesquisa.xhtml");
	recursoClassificacao.setDescricao("Página de gerenciamento de categorias de pesquisa.");

	Autorizacao autorizacaoClienteAdm = new Autorizacao();
	Autorizacao autorizacaoPesquisaAdm = new Autorizacao();
	Autorizacao autorizacaoUsuarioAdm = new Autorizacao();
	Autorizacao autorizacaoClassificacaoAdm = new Autorizacao();
	autorizacaoClienteAdm.setGrupoDeUsuario(grupoAdm);
	autorizacaoClienteAdm.setRecurso(recursoCliente);
	autorizacaoClienteAdm.setTipoDePermissao(TipoPermissao.Editar);
	autorizacaoPesquisaAdm.setGrupoDeUsuario(grupoAdm);
	autorizacaoPesquisaAdm.setRecurso(recursoPesquisa);
	autorizacaoPesquisaAdm.setTipoDePermissao(TipoPermissao.Criar);
	autorizacaoUsuarioAdm.setGrupoDeUsuario(grupoAdm);
	autorizacaoUsuarioAdm.setRecurso(recursoUsuario);
	autorizacaoUsuarioAdm.setTipoDePermissao(TipoPermissao.Tudo);
	autorizacaoClassificacaoAdm.setGrupoDeUsuario(grupoAdm);
	autorizacaoClassificacaoAdm.setRecurso(recursoClassificacao);
	autorizacaoClassificacaoAdm.setTipoDePermissao(TipoPermissao.Consultar);

	ClassificacaoPesquisa categoriaInformatica = new ClassificacaoPesquisa();
	categoriaInformatica.setNome("Internet");
	categoriaInformatica.setDescricao("Pesquisas relacionadas à internet.");

	Pesquisa pesquisaDeNavegadores = new Pesquisa();
	pesquisaDeNavegadores.setCliente(cliente);
	pesquisaDeNavegadores.setDescricao("Enquete sobre uso de navegadores.");
	pesquisaDeNavegadores.setIsPublic(true);
	pesquisaDeNavegadores.setNome("Pesquisa sobre Navegadores");
	pesquisaDeNavegadores.setClassificacaoDaPesquisa(categoriaInformatica);

	Questao questaoUm = new Questao();
	Questao questaoDois = new Questao();
	questaoUm.setPesquisa(pesquisaDeNavegadores);
	questaoUm.setEnunciado("Qual o navegador que você mais usa em casa?");
	questaoUm.setNumeroQuestao(1);
	questaoDois.setEnunciado("Qual o navegador que você mais usa no trabalho?");
	questaoDois.setPesquisa(pesquisaDeNavegadores);
	questaoDois.setNumeroQuestao(2);

	Alternativa alternativaUmQuestaoUm = new Alternativa();
	Alternativa alternativaDoisQuestaoUm = new Alternativa();
	Alternativa alternativaTresQuestaoUm = new Alternativa();
	Alternativa alternativaQuatroQuestaoUm = new Alternativa();
	Alternativa alternativaUmQuestaoDois = new Alternativa();
	Alternativa alternativaDoisQuestaoDois = new Alternativa();
	Alternativa alternativaTresQuestaoDois = new Alternativa();
	Alternativa alternativaQuatroQuestaoDois = new Alternativa();
	alternativaUmQuestaoUm.setQuestao(questaoUm);
	alternativaUmQuestaoUm.setNumeroAlternativa(1);
	alternativaUmQuestaoUm.setValorAlternativa("Mozila Firefox");
	alternativaDoisQuestaoUm.setNumeroAlternativa(2);
	alternativaDoisQuestaoUm.setQuestao(questaoUm);
	alternativaDoisQuestaoUm.setValorAlternativa("Internet Explorer");
	alternativaTresQuestaoUm.setNumeroAlternativa(3);
	alternativaTresQuestaoUm.setQuestao(questaoUm);
	alternativaTresQuestaoUm.setValorAlternativa("Google Chrome");
	alternativaQuatroQuestaoUm.setNumeroAlternativa(4);
	alternativaQuatroQuestaoUm.setQuestao(questaoUm);
	alternativaQuatroQuestaoUm.setValorAlternativa("Outros");
	alternativaUmQuestaoDois.setQuestao(questaoDois);
	alternativaUmQuestaoDois.setNumeroAlternativa(1);
	alternativaUmQuestaoDois.setValorAlternativa("Mozila Firefox");
	alternativaDoisQuestaoDois.setNumeroAlternativa(2);
	alternativaDoisQuestaoDois.setQuestao(questaoDois);
	alternativaDoisQuestaoDois.setValorAlternativa("Internet Explorer");
	alternativaTresQuestaoDois.setNumeroAlternativa(3);
	alternativaTresQuestaoDois.setQuestao(questaoDois);
	alternativaTresQuestaoDois.setValorAlternativa("Google Chrome");
	alternativaQuatroQuestaoDois.setNumeroAlternativa(4);
	alternativaQuatroQuestaoDois.setQuestao(questaoDois);
	alternativaQuatroQuestaoDois.setValorAlternativa("Outros");

	session.save(categoriaInformatica);
	session.save(recursoUsuario);
	session.save(recursoPesquisa);
	session.save(recursoCliente);
	session.save(recursoClassificacao);
	session.save(pesquisaDeNavegadores);
	session.save(questaoUm);
	session.save(questaoDois);
	session.save(alternativaUmQuestaoUm);
	session.save(alternativaDoisQuestaoUm);
	session.save(alternativaTresQuestaoUm);
	session.save(alternativaQuatroQuestaoUm);
	session.save(alternativaUmQuestaoDois);
	session.save(alternativaDoisQuestaoDois);
	session.save(alternativaTresQuestaoDois);
	session.save(alternativaQuatroQuestaoDois);
	session.save(grupoAdm);
	session.save(autorizacaoClienteAdm);
	session.save(autorizacaoPesquisaAdm);
	session.save(autorizacaoClassificacaoAdm);
	session.save(autorizacaoUsuarioAdm);
	session.save(user);
	session.save(cliente);
	session.save(cidade);
	session.save(estado);
	tx.commit();
	session.close();
    }

    /**
     * Encerra o hibernate.
     * @param sf SessionFactory a ser fechada.
     */
    public static void FinalizaHibernate(SessionFactory sf)
    {
	    sf.close();
    }
}
