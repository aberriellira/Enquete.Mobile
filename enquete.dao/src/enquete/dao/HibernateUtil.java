package enquete.dao;

import enquete.modelo.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Classe de utilitários relacionados ao hibernate
 * @author Anselmo
 */
public class HibernateUtil {

	/** SessionFactory disponibilizada para todos os projetos que usam o modelo. */
	private static SessionFactory sessionFactory;

	/**
	 * Método que permite obter uma SessionFactory e, caso não haja, cria uma.
	 * @return SessionFactory.
	 */
	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			System.out.println("Inicializando o hibernate.");
			System.out.println("Criando configuração.");
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg.configure();
			System.out.println("Criando SessionFactory");
			sessionFactory = cfg.buildSessionFactory();
			return sessionFactory;
		}
		else
		{
			return sessionFactory;
		}
	}

	/**
	 * Retorna uma SessionFactory
	 * @param args Argumentos de linha de comando.
	 */
	public static void main(String[] args)
	{
		HibernateUtil.getSessionFactory();
	}

}
