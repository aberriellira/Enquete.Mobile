package br.com.enquetemobile.mobile;

import java.io.*;
import javax.microedition.io.*;
/*
import org.kxml.parser.*;
import org.kxml.*;
*/
public class RSSParser {
   /*
    * Dado tag xml de nível 1: <ns:logarResponse>
    * Dado tag xml de nível 2: <ns:return>
    * Vou pegar o valor que está em return
    */
/*
   protected RSSListener mRSSListener;

   public void setRSSListener(RSSListener listener)
   {
      mRSSListener = listener;
   }

   public void parse(final String url)
   {
      System.out.println("Entrando no primeiro parse");
      System.out.println("Acessando url: " + url);
      Thread t = new Thread() {
         public void run(){
            HttpConnection conexao = null;
            try{
               conexao = (HttpConnection)Connector.open(url);
               parse (conexao.openInputStream());
               System.out.println("Saí do segundo parse, mas ainda dentro do primeiro parser");
            }
            catch(IOException e1){
               mRSSListener.exception(e1);
            }
            finally{
               try{
                  if(conexao != null){
                     conexao.close();
                     System.out.println("Finalizada a conexão, ainda dentro do primeiro parser");
                  }
               }
               catch (IOException ignorado){}
               System.out.println("Saindo do finally");
            }
            System.out.println("Saindo do run");
         }
      };
      System.out.println("Iniciando a thread");
      t.start();
      System.out.println("Depois da thread");
   }

   public void parse(InputStream in) throws IOException
   {
      System.out.println("Entrando no segundo parse");
      Reader reader = new InputStreamReader(in);
      XmlParser parser = new XmlParser(reader);
      ParseEvent pe = null;

      System.out.println("pegando o parse");
      //parser.read(Xml.START_TAG,null,"logarResponse");
      System.out.println("já peguei o parse");
      boolean trucking = true;
      int controle = 0;
      boolean first = true;
      String result = null;
      while(controle<2)
      {
         System.out.println("Entrando no while");
         pe = parser.read();
         System.out.println("Li o parser");
         System.out.println(pe.getText());
         System.out.println(pe.getName());
         if(pe.getType() == Xml.START_TAG)
         {
            System.out.println("Entrando no primeiro if");
            if(pe.getType() == Xml.START_TAG && pe.getName().equals("return"))
            {
               System.out.println("Entrando no segundo if");
               pe = parser.read();
               result = pe.getText();
            }
            System.out.println("Valor do result dentro do parse: " + result);
            this.mRSSListener.itemParsed (result);
         }
         else
         {
            if(pe.getType()==Xml.END_TAG && pe.getName()=="logarResponse")
            {
               trucking = false;
            }
         }
         controle = controle + 1;
         System.out.println("Controle = " + controle);
      }
      System.out.println("Saindo do while");
   }*/
}