package ar.edu.itba.paw.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.models.Skill;

@Repository
public class SkillHibernateDao implements SkillDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Skill create(String name) {
		final Skill skill = new Skill(name, new java.util.Date());
		em.persist(skill);
		return skill;
	}

	@Override
	public void delete(Long id) {
		Query q = em.createQuery("delete from Skill where id = :id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public Skill update(Long id, String name) {
		Skill skill = find(id);
		if (skill == null) {
			return null;
		}
		if (name != null && !name.isEmpty()) {
			skill.setName(name);
			em.persist(skill);
		}
		return skill;
	}

	@Override
	public Long count() {
		Query query = em.createQuery("SELECT count(*) FROM Skill");
		return (Long) query.getSingleResult();
	}

	@Override
	public Skill find(Long id) {
		final TypedQuery<Skill> query = em.createQuery("from Skill as s where s.id = :id", Skill.class);
		query.setParameter("id", id);
		final List<Skill> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Skill findByName(String name) {
		final TypedQuery<Skill> query = em.createQuery("from Skill as s where s.name = :name", Skill.class);
		query.setParameter("name", name);
		final List<Skill> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<Skill> all() {
		final TypedQuery<Skill> query = em.createQuery("from Skill as s ORDER BY s.name ASC", Skill.class);
		return query.getResultList();
	}

}
