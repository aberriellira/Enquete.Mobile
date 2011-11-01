/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enquete.dao;

import enquete.modelo.*;
import enquete.dao.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 *
 * @author Anselmo
 */
public class UsuarioDao {

	public void Save(Usuario usuario)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(usuario);
		t.commit();
	}

	/**
	 * Permite obter cliente a partir do id.
	 * @param id ID do cliente no banco.
	 * @return Retorna objeto do tipo cliente.
	 * @exception Ocorre exceção se cliente não encontrado.
	 */
	public Usuario getUsuario(long id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Usuario)session.load(Usuario.class, id);
	}

	/**
	 * Método que permite retornar lista de clientes.
	 * @return Listagem de clientes
	 */
	public List<Usuario> list()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.getTransaction();
		List lista = session.createQuery("from Usuario").list();
		t.commit();
		return lista;
	}

	public void Remove(Usuario usuario)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(usuario);
		t.commit();
	}

	public void Update(Usuario usuario)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(usuario);
		t.commit();
	}
}
