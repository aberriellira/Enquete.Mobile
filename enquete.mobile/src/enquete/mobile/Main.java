package br.com.enquetemobile.mobile;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.rms.*;

public class Main extends MIDlet implements CommandListener,ItemCommandListener,RSSListener
{
    /*
     * Ordem das tarefas:
     *
     * 1- Capturar login e senha para acessar
     *      a) se login e senha incorretos, retornar à tela de login exibindo mensagem de erro
     *      b) se login e senha corretos, avançar para a atela de listagem de pesquisas
     *
     * 2- obter lista de pesquisas no banco de dados para o usuário corrente ordenado
     *
     * 3- listar pesquisas na tela na ordem em que foi obtido
     *      a) iterar sobre resultset obtido
     *
     * 4- dado pesquisa escolhida, obter id real dela e guardar
     *
     * 5- obter informações da pesquisa dada
     *
     * 6- obter lista de questões ordenada e iterar sobre cada questão
     *
     * Breve descrição dos comandos:
     *
     *   help - Exibir tela de ajuda (a mesma independente da tela, por hora
     *   sairInicio - sair do programa, a partir da tela de login
     *   sair - ir para a atela de confirmação de saída
     *   entrar - logar no sistema
     *   confirmarSaida - sair do sistema a partir da tela de confirmação de saída
     *   cancelarSaida - a aprtir da tela de confirmação de saída, ir pra tela de listageem de pesquisas
     *   iniciarPesquisa - iniciar a pesquisa, a partir da tela de descrição de pesquisas
     *   voltarInicio -- sair da tela de ajuda para a tela de login
     *   voltarLogado - sair da tela de ajuda para a tela de listagem
     */
    private Display tela;
    public int idUsuario; // usuário corrente
    public int idPesquisa; // pesquisa corrente;
    public int indiceQuestao; // questão corrente
    public int noTotalPesquisa; // total de pesquisas
    public int noTotalQuestao; // total de qustões
    public boolean jaPassou;
    public boolean conexaoEmAndamento;;
    public boolean sucessoConexao;
    private Command entrar, sairInicio, help, sair; // comandos da tela inicial
    private Command salvarConfiguracao, sairConfiguracao, entrarAjudaConfiguracao, sairAjudaConfiguracao;
    private Command confirmarSaida, cancelarSaida; // comandos da tela de confirmação de saída
    private Command iniciarPesquisa, avancarPergunta, retrocederPergunta, finalizarPesquisa, helpDentro, voltar, voltarDentro;
    private Form inicio, configuracao, ajudaConfiguracao, apresentaPesquisa, questao, sairConfirma, ajuda, ajudaDentro;
    private StringItem mensagemInicio;
    private TextField texto, login, senha, url;
    private Alert alerta;
    private List listaDePesquisas;
    private ChoiceGroup respostaPergunta;
    public String parametro;
    //RSSParser parser =  new RSSParser(); // crio a instância de forma global a todos

    public void acessaWebService(String url)
    {
        String retorno = null;
        //parser.setRSSListener(this);
        //System.out.println("Fazendo o parser");
        //parser.parse(url);
        //System.out.println("acesso finalizado");
    }

    // Faz o login no sistema e retorna um valor booleano indicando se o usuário entrou ou não.
    // Seta o usuário logado pelo seu id cadastrado no banco de dados.
    public boolean logarSistema(String loginUsuario, String senhaUsuario)
    {
       boolean permissao = false;
       //System.out.println("Definindo tela de espera");
       //Screen espera = new Form ("Entrando ..........");
       //System.out.println("Mostrando tela de espera.");
       //tela.setCurrent(espera);
       //System.out.println("Definindo url");
       //String url = "http://localhost:8084/axis2/services/EnqueteMetodos/logar?login=" + loginUsuario + "&senha=" + senhaUsuario;
       //System.out.println("Acessando o web service");
       //acessaWebService(url);
       //String resposta = parametro;
       //this.conexaoEmAndamento = true;
       /*
       if(parametro=="true")
       {
          permissao = true;
       }*/
       this.idUsuario = 1;
       return true;
    }

    // retorna o total de pesquisas cadastradaas para o usuário acessar
    public int totalPesquisa()
    {
       String url = "http://localhost:8084/axis2/services/EnqueteMetodos/totalPesquisa?idUsuario=" + String.valueOf(this.idUsuario);
       acessaWebService(url);
       String resposta = parametro;
       int total = 0;
       if (this.idUsuario==1)
       {
          total = 2;
       }
       return total;
    }

    // retorna o nome (título) da pesquisa dado seu índice
    // o índice é dado pelo id da pesquisa na tabela de view retornada pelo banco de dados
    public String nomePesquisa (int indice)
    {

       /*
        * 1- Pego o número total de pesquisas associadas ao usuário
        * 2- Obtenho uma tabela (no web service) de pesquisas ordenada por um índice
        * 3- Itero sobre essa tabela, pegando um rótulo por vez
        */
        String nome = null;
         if(indice==1)
        {
            nome = "Pesquisa de opiniao de navegadoores";
        }
        if(indice==2)
        {
            nome = "Pesquisa de opiniao de SOs.";
        }
        return nome;
    }

    // retorna a descrição da pesquisa, conforme cadastrada no banco de dados
    public String descricaoPesquisa()
    {
       /*
        * Já escolhi uma pesquisa e tenho seu índice guardado
        */
        String descricao = null;
        if (this.idPesquisa==1)
        {
            descricao = "Pesquisa sobre uso dos navegadores principais no mercado.";
        }
        if(this.idPesquisa==2)
        {
            descricao = "Pesquisa sobre preferencias de uso dos principais SOs do mercado, como Windows e Linux.";
        }
        return  descricao;
    }

    // retorna o total de questões cadastradas na pesquisa
    // apenas para fins de exibição na tela do celular
    public int totalQuestao()
    {
        int total = 0;
        if (this.idPesquisa==1)
        {
            total = 4;
        }
        if (this.idPesquisa==2)
        {
            total = 4;
        }
        return total;
    }

    // retorna o enunciado da questão dado seu índice
    // as questões já são cadastradas numeradas no banco de dados segundo a ordem que eu quero que sejam exibidas
    public String enunciadoQuestao()
    {
       /*
        * Como as pesquisas são numeradas, basta dar o número da questão que
        * ele retorna o enunciado
        */
       String enunciado = null;
       if (this.idPesquisa==1)
       {
          if (this.indiceQuestao==1)
          {
             enunciado = "Voce usa computador em casa?";
          }
          if (this.indiceQuestao==2)
          {
             enunciado = "Qual o navegador que voce mais usa em casa?";
          }
          if (this.indiceQuestao==3)
          {
             enunciado = "Voce usa computador no trabalho?";
          }
          if (this.indiceQuestao==4)
          {
             enunciado = "Qual o navegador que voce mais usa no trabalho?";
          }
       }
       if (this.idPesquisa==2)
       {
          if (this.indiceQuestao==1)
          {
             enunciado = "Voce usa computador em casa?";
          }
          if (this.indiceQuestao==2)
          {
             enunciado = "Qual o sistema operacional que voce usa em casa?";
          }
          if (this.indiceQuestao==3)
          {
             enunciado = "Voce usa computador no trabalho?";
          }
          if (this.indiceQuestao==4)
          {
             enunciado = "Qual o sistema operacional que voce usa no trabalho?";
          }
       }
       return enunciado;
    }

    public int totalAlternativa()
    {
       int total = 0;
       if(idPesquisa==1){
          if(indiceQuestao==1){
             total = 2;}
          if(indiceQuestao==2){
             total = 6;}
          if(indiceQuestao==3){
             total = 2;}
          if(indiceQuestao==4){
             total = 6;}
       }
       if(idPesquisa==2){
          if(indiceQuestao==1){
             total = 2;}
          if(indiceQuestao==2){
             total = 6;}
          if(indiceQuestao==3){
             total = 2;}
          if(indiceQuestao==4){
             total = 6;}
       }
       return total;
    }

    public String opcaoQuestao(int indiceAlternativa)
    {
       String escolha = null;

       if (this.idPesquisa==1)
       {
          if (this.indiceQuestao==1)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Sim";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Nao";
             }
          }
          if (indiceQuestao==2)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Mozila Firefox";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Internet Explorer";
             }
             if (indiceAlternativa==3)
             {
                escolha = "Safari";
             }
             if (indiceAlternativa==4)
             {
                escolha = "Opera";
             }
             if (indiceAlternativa==5)
             {
                escolha = "Outro";
             }
             if (indiceAlternativa==6)
             {
                escolha = "Não uso computador em casa";
             }
          }
          if (this.indiceQuestao==3)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Sim";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Nao";
             }
          }
          if (this.indiceQuestao==4)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Mozila Firefox";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Internet Explorer";
             }
             if (indiceAlternativa==3)
             {
                escolha = "Safari";
             }
             if (indiceAlternativa==4)
             {
                escolha = "Opera";
             }
             if (indiceAlternativa==5)
             {
                escolha = "Outro";
             }
             if (indiceAlternativa==6)
             {
                escolha = "Não uso computador no trabalho";
             }
          }
       }
       if (this.idPesquisa==2)
       {
          if (this.indiceQuestao==1)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Sim";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Nao";
             }
          }
          if (this.indiceQuestao==2)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Microsoft Windows";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Apple Machintosh";
             }
             if (indiceAlternativa==3)
             {
                escolha = "Linux";
             }
             if (indiceAlternativa==4)
             {
                escolha = "Solaris/BSD";
             }
             if (indiceAlternativa==5)
             {
                escolha = "Outros";
             }
             if (indiceAlternativa==6)
             {
                escolha = "Nõ uso computador em casa";
             }
          }
          if (this.indiceQuestao==3)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Sim";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Nao";
             }
          }
          if (this.indiceQuestao==4)
          {
             if (indiceAlternativa==1)
             {
                escolha = "Microsoft Windows";
             }
             if (indiceAlternativa==2)
             {
                escolha = "Apple Machintosh";
             }
             if (indiceAlternativa==3)
             {
                escolha = "Linux";
             }
             if (indiceAlternativa==4)
             {
                escolha = "Solaris/BSD";
             }
             if (indiceAlternativa==5)
             {
                escolha = "Outros";
             }
             if (indiceAlternativa==6)
             {
                escolha = "Não uso computador no trabalho.";
             }

          }
       }
       return escolha;
    }

    public Main()
    {
       this.jaPassou = false;
        tela = Display.getDisplay(this);

        // tela inicial
        inicio = new Form("Enquete para celular");
        entrar = new Command("Entrar", Command.SCREEN,1);
        help = new Command("Ajuda", Command.SCREEN, 1);
        helpDentro = new Command("Ajuda", Command.SCREEN, 1);
        sairInicio = new Command("Sair", Command.CANCEL, 1);
        texto = new TextField("","",50,TextField.ANY);
        mensagemInicio = new StringItem("","Bem-vindo!");
        login = new TextField("Login","",12,TextField.ANY);
        senha = new TextField("Senha","",12,TextField.PASSWORD);
        mensagemInicio.setLayout(Item.LAYOUT_CENTER);
        inicio.addCommand(help);
        inicio.addCommand(entrar);
        inicio.addCommand(sairInicio);
        inicio.append(mensagemInicio);
        inicio.append(login);
        inicio.append(senha);
        inicio.setCommandListener(this);

        /*
         * Declaração da tela de descrição de pesquisas
         * Preciso acessá-la mais tarde, logo declaro ela aqui
         */
        apresentaPesquisa = new Form("Descricao da pesquisa");
        iniciarPesquisa = new Command("Iniciar pesquisa",Command.SCREEN, 1);

        // tela de ajuda de fora
        ajuda = new Form("Ajuda do Sistema de enquete");
        StringItem textoAjuda;
        voltar = new Command("Voltar",Command.SCREEN,1);
        textoAjuda = new StringItem("Bem-vindo ao enqueteMobile v1.0. Este programa é protegido pelas leis de direitos autorais.","");
        ajuda.addCommand(voltar);
        ajuda.append(textoAjuda);

        // tela de ajuda de dentro
        ajudaDentro = new Form("Ajuda do Sistema de enquete");
        StringItem textoAjudaDentro;
        voltarDentro = new Command("Voltar",Command.SCREEN,1);
        textoAjudaDentro = new StringItem("Bem-vindo ao enqueteMobile v1.0. Este programa é protegido pelas leis de direitos autorais.","");
        ajudaDentro.addCommand(voltarDentro);
        ajudaDentro.append(textoAjudaDentro);

        // tela de listagem de pesquisas


        // tela de questoes
        questao = new Form ("Enquete v1.0");
        avancarPergunta = new Command ("Avançar",Command.SCREEN, 1);
        retrocederPergunta = new Command("Anterior",Command.SCREEN,1);
        finalizarPesquisa = new Command ("Finalizar", Command.OK, 1);
        sair = new Command ("Sair", Command.SCREEN, 1);

        // tela de confirmar saída
        sairConfirma = new Form("Confirmar saida");

    }

    public void pauseApp()
    {
    }

    public void startApp()
    {
        tela.setCurrent(inicio);
    }

    public void destroyApp(boolean unconditional)
    {
    }

    public void commandAction(Command c, Displayable s)
    {
        if (c == help)
        {
            //item4.setText("");
            tela.setCurrent(ajuda);
        }
         if (c == helpDentro)
        {
            //item4.setText("");
            tela.setCurrent(ajudaDentro);
        }
         if (c == voltar)
        {
           tela.setCurrent(inicio);
        }
        if (c == voltarDentro)
        {
           tela.setCurrent(this.listaDePesquisas);
        }
        if (c == entrar)
        {
           String loginUser = this.login.getString();
           String passwordUser = this.senha.getString();
           System.out.println("Fazendo o login");
           boolean teste = logarSistema(loginUser, passwordUser);
           System.out.println("Já fiz o login");
           if (teste)
           {
              this.noTotalPesquisa = this.totalPesquisa();
              int noPesquisas = this.noTotalPesquisa;
              int i;
              String listagem[] = new String[noPesquisas];
              for (i=0; i<noPesquisas; i++)
              {
                 int codPesquisa = i+1;
                 String nomeDaPesquisa = nomePesquisa(codPesquisa);
                 listagem[i] = nomePesquisa(i+1);
              }
              listaDePesquisas = new List("Pesquisas disponiveis",List.IMPLICIT,listagem,null);
              listaDePesquisas.addCommand(helpDentro);
              listaDePesquisas.addCommand(sairInicio);
              listaDePesquisas.setCommandListener(this);
              tela.setCurrent(listaDePesquisas);
           }
           else
           {
              mensagemInicio.setText(parametro);
              //tela.setCurrent(inicio);
           }
        }
        if (c == listaDePesquisas.SELECT_COMMAND)
        {
           apresentaPesquisa.deleteAll();
           apresentaPesquisa.removeCommand(iniciarPesquisa);
           this.idPesquisa = listaDePesquisas.getSelectedIndex() + 1;
           StringItem nomeDaPesquisa;
           nomeDaPesquisa = new StringItem(nomePesquisa(this.idPesquisa),"");
           StringItem descricaoDaPesquisa;
           descricaoDaPesquisa = new StringItem(descricaoPesquisa(),"");
           apresentaPesquisa.addCommand(iniciarPesquisa);
           apresentaPesquisa.addCommand(sairInicio);
           apresentaPesquisa.setCommandListener(this);
           apresentaPesquisa.append(nomeDaPesquisa);
           apresentaPesquisa.append(descricaoDaPesquisa);
           tela.setCurrent(apresentaPesquisa);
        }
        if (c == iniciarPesquisa)
        {
            this.noTotalQuestao = totalQuestao();
            this.indiceQuestao = 1;
            int totalAlternativas = this.totalAlternativa();
            this.respostaPergunta = new ChoiceGroup(indiceQuestao + "- " + enunciadoQuestao(),Choice.EXCLUSIVE);
            for (int i=0; i<totalAlternativas; i++)
            {
               respostaPergunta.append(this.opcaoQuestao(i+1), null);
            }
            questao.append(respostaPergunta);
            questao.addCommand(sair);
            questao.addCommand(avancarPergunta);
            questao.setCommandListener(this);
            tela.setCurrent(questao);
        }
        if (c == avancarPergunta)
        {
           questao.deleteAll();
           this.indiceQuestao = indiceQuestao + 1;
           int totalAlternativas = this.totalAlternativa();
           this.respostaPergunta.deleteAll();
           this.respostaPergunta.setLabel(this.enunciadoQuestao());
           for (int i=0; i<totalAlternativas; i++)
           {
              respostaPergunta.append(this.opcaoQuestao(i+1), null);
           }
           questao.append(respostaPergunta);
           questao.addCommand(sair);
           if (this.indiceQuestao == this.noTotalQuestao)
           {
              questao.addCommand(finalizarPesquisa);
           }
           if (this.indiceQuestao != this.noTotalQuestao)
           {
              questao.addCommand(avancarPergunta);
           }
           questao.addCommand(retrocederPergunta);
           questao.setCommandListener(this);
           tela.setCurrent(questao);
        }
        if (c == retrocederPergunta)
        {
           questao.deleteAll();
           this.indiceQuestao = indiceQuestao - 1;
           int totalAlternativas = this.totalAlternativa();
           this.respostaPergunta.deleteAll();
           this.respostaPergunta.setLabel(this.enunciadoQuestao());
           for (int i=0; i<totalAlternativas; i++)
           {
              respostaPergunta.append(this.opcaoQuestao(i+1), null);
           }
           questao.append(respostaPergunta);
           questao.addCommand(sair);
           if (this.indiceQuestao == this.noTotalQuestao)
           {
              questao.addCommand(finalizarPesquisa);
           }
           if (this.indiceQuestao != this.noTotalQuestao)
           {
              questao.addCommand(avancarPergunta);
           }
           questao.setCommandListener(this);
           tela.setCurrent(questao);
        }
        if (c == this.finalizarPesquisa)
        {
           tela.setCurrent(this.listaDePesquisas);

        }
        if (c == sairInicio)
        {
            notifyDestroyed();
        }
        if (c == sair)
        {
            notifyDestroyed();
        }
    }

    public void commandAction(Command c, Item i)
    {
        if (c == sairInicio)
        {
            notifyDestroyed();
        }
        if (c == voltar)
        {
           tela.setCurrent(inicio);
        }
    }

    public void itemParsed(String result){
       System.out.println("Executando o itemParsed");
       parametro = result;
       System.out.println("Valor do parametro = " + parametro);
      tela.setCurrent(inicio);
   }

   public void exception(java.io.IOException ex){
      Alert a = new Alert("Exception: ", ex.toString(),null,null);
      a.setTimeout(Alert.FOREVER);
      tela.setCurrent(a, inicio);
   }
}