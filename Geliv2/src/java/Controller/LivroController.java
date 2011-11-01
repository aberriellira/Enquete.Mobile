/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Dao.LivroDao;
import Dao.LivroDaoImp;
import Model.Livro;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author José Alexandre
 */
@ManagedBean
@SessionScoped
public class LivroController {

    private Livro livro;
    private DataModel listaLivros;

    public DataModel getListarLivros() {
        List<Livro> lista = new LivroDaoImp().list();
        listaLivros = new ListDataModel(lista);
        return listaLivros;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void prepararAdicionarLivro(ActionEvent actionEvent){
        livro = new Livro();
    }

    public void prepararAlterarLivro(ActionEvent actionEvent){
        livro = (Livro)(listaLivros.getRowData());
    }

    public String excluirLivro(){

        Livro livroTemp = (Livro)(listaLivros.getRowData());
        LivroDao dao = new LivroDaoImp();
        dao.remove(livroTemp);
        return "index";

    }

    public void adicionarLivro(ActionEvent actionEvent){

        LivroDao dao = new LivroDaoImp();
        dao.save(livro);
        
    }

    public void alterarLivro(ActionEvent actionEvent){

        LivroDao dao = new LivroDaoImp();
        dao.update(livro);

    }

}
