package ar.edu.itba.paw.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class UserHibernateDao implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(String firstName, String lastName, String email, String password) {
		final User user = new User(firstName, lastName, email, password, new java.util.Date());
		em.persist(user);
	}

	@Override
	public void delete(Long id) {
		Query q = em.createQuery("delete from User where id = :id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void update(Long id, String firstName, String lastName, String email, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByEmail(String email) {
		final TypedQuery<User> query = em.createQuery("from User as u where u.email = :email", User.class);
		query.setParameter("email", email);
		final List<User> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Long count() {
		Query query = em.createQuery("SELECT count(*) FROM User");
		return (Long) query.getSingleResult();
	}

	@Override
	public User find(Long id) {
		final TypedQuery<User> query = em.createQuery("from User as u where u.id = :id", User.class);
		query.setParameter("id", id);
		final List<User> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<User> all() {
		final TypedQuery<User> query = em.createQuery("from User as u ORDER BY u.email ASC", User.class);
		return query.getResultList();
	}

	@Override
	public List<User> all(Integer page, Integer perPage) {
		final TypedQuery<User> query = em.createQuery("from User as u ORDER BY u.email ASC", User.class);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

}
