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
public class ClienteDao {

	public void Save(Cliente cliente)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(cliente);
		t.commit();
	}

	/**
	 * Permite obter cliente a partir do id.
	 * @param id ID do cliente no banco.
	 * @return Retorna objeto do tipo cliente.
	 * @exception Ocorre exceção se cliente não encontrado.
	 */
	public Cliente getCliente(long id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Cliente)session.load(Cliente.class, id);
	}

	/**
	 * Método que permite retornar lista de clientes.
	 * @return Listagem de clientes
	 */
	public List<Cliente> list()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.getTransaction();
		List lista = session.createQuery("from Cliente").list();
		t.commit();
		return lista;
	}

	public void Remove(Cliente cliente)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(cliente);
		t.commit();
	}

	public void Update(Cliente cliente)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(cliente);
		t.commit();
	}
}
