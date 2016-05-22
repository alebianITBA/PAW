package ar.edu.itba.paw.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;

@Repository
public class PostHibernateDao implements PostDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Post create(String title, String description, User user) {
		final Post post = new Post(title, description, user, new java.util.Date());
		em.persist(post);
		return post;
	}

	@Override
	public void delete(Long id) {
		Query q = em.createQuery("delete from Post p where p.id = :id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public Post update(Long id, String title, String description) {
		Post post = find(id);
		if (post == null) {
			return null;
		}
		if (title != null && !title.isEmpty()){
			post.setTitle(title);
		}
		if (description != null && !description.isEmpty()){
			post.setDescription(description);
		}
		em.persist(post);
		return post;
	}

	@Override
	public Long count() {
		Query query = em.createQuery("SELECT count(*) FROM Post");
		return (Long) query.getSingleResult();
	}

	@Override
	public Post find(Long id) {
		final TypedQuery<Post> query = em.createQuery("from Post as p where p.id = :id", Post.class);
		query.setParameter("id", id);
		final List<Post> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<Post> all() {
		final TypedQuery<Post> query = em.createQuery("from Post as p ORDER BY p.createdAt DESC", Post.class);
		return query.getResultList();
	}

	@Override
	public List<Post> all(Integer page, Integer perPage) {
		final TypedQuery<Post> query = em.createQuery("from Post as p ORDER BY p.createdAt DESC", Post.class);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

	@Override
	public List<Post> userPosts(Long userId) {
		final TypedQuery<Post> query = em.createQuery("from Post as p where p.user.id = :user_id ORDER BY p.createdAt DESC", Post.class);
		query.setParameter("user_id", userId);
		return query.getResultList();
	}

	@Override
	public List<Post> userPosts(Long userId, Integer page, Integer perPage) {
		final TypedQuery<Post> query = em.createQuery("from Post as p where p.user.id = :user_id ORDER BY p.createdAt DESC", Post.class);
		query.setParameter("user_id", userId);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

}
