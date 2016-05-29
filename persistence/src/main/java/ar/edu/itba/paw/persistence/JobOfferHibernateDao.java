package ar.edu.itba.paw.persistence;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@Repository
public class JobOfferHibernateDao implements JobOfferDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public JobOffer create(String title, String description, User user) {
		final JobOffer jobOffer = new JobOffer(title, description, user, new java.util.Date());
		em.persist(jobOffer);
		return jobOffer;
	}

	@Override
	public JobOffer create(String title, String description, User user, List<Skill> skills) {
		final JobOffer jobOffer = new JobOffer(title, description, user, skills, new java.util.Date());
		em.persist(jobOffer);
		return jobOffer;
	}

	@Override
	public void delete(Long id) {
		Query q = em.createQuery("delete from JobOffer where id = :id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public JobOffer update(Long id, String title, String description) {
		JobOffer jobOffer = find(id);
		if (jobOffer == null) {
			return null;
		}
		if (title != null && !title.isEmpty()) {
			jobOffer.setTitle(title);
		}
		if (description != null && !description.isEmpty()) {
			jobOffer.setDescription(description);
		}
		em.persist(jobOffer);
		return jobOffer;
	}

	@Override
	public Long count() {
		Query query = em.createQuery("SELECT count(*) FROM JobOffer");
		return (Long) query.getSingleResult();
	}

	@Override
	public JobOffer find(Long id) {
		final TypedQuery<JobOffer> query = em.createQuery("from JobOffer as o where o.id = :id", JobOffer.class);
		query.setParameter("id", id);
		final List<JobOffer> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<JobOffer> all() {
		final TypedQuery<JobOffer> query = em.createQuery("from JobOffer as o ORDER BY o.createdAt DESC",
				JobOffer.class);
		return query.getResultList();
	}

	@Override
	public List<JobOffer> all(Integer page, Integer perPage) {
		final TypedQuery<JobOffer> query = em.createQuery("from JobOffer as j ORDER BY j.createdAt DESC",
				JobOffer.class);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId) {
		final TypedQuery<JobOffer> query = em
				.createQuery("from JobOffer as o where o.user.id = :userId ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage) {
		final TypedQuery<JobOffer> query = em
				.createQuery("from JobOffer as o where o.user.id = :userId ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("userId", userId);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"select o from JobOffer o join o.skills s where s.id in (:skills) ORDER BY o.createdAt DESC",
				JobOffer.class);
		query.setParameter("skills", skillsIds(skills));
		return query.getResultList();
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"select o from JobOffer o join o.skills s where s.id in (:skills) ORDER BY o.createdAt DESC",
				JobOffer.class);
		query.setParameter("skills", skillsIds(skills));
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}
	
	@Override
	public Long withSkillsCount(List<Skill> skills) {
		Query query = em.createQuery("SELECT count(*) FROM JobOffer o join o.skills s where s.id in (:skills)");
		query.setParameter("skills", skillsIds(skills));
		return (Long) query.getSingleResult();
	}

	private List<Long> skillsIds(List<Skill> skills) {
		List<Long> result = new LinkedList<Long>();
		for (Skill skill : skills) {
			result.add(skill.getId());
		}
		return result;
	}

	@Override
	public List<JobOffer> notFromUser(Long userId) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"from JobOffer as o where o.user.id != :userId ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<JobOffer> notFromUser(Long userId, Integer page, Integer perPage) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"from JobOffer as o where o.user.id != :userId ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("userId", userId);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}
	
	@Override
	public List<JobOffer> notApplied(Long userId, List<JobApplication> applications) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"from JobOffer o where o.id not in (:applied) and o.user.id != :userId ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("userId", userId);
		query.setParameter("applied", applicationOfferIds(applications));
		return query.getResultList();
	}
	
	@Override
	public List<JobOffer> notApplied(Long userId, List<JobApplication> applications, Integer page, Integer perPage) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"from JobOffer o where o.id not in (:applied) and o.user.id != :userId ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("userId", userId);
		query.setParameter("applied", applicationOfferIds(applications));
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}
	
	@Override
	public List<JobOffer> notAppliedWithSkills(Long userId, List<JobApplication> applications, List<Skill> skills) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"select o from JobOffer o join o.skills s where s.id in (:skills) and (o.id not in (:applied) and o.user.id != :userId) ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("skills", skillsIds(skills));
		query.setParameter("userId", userId);
		query.setParameter("applied", applicationOfferIds(applications));
		return query.getResultList();
	}
	
	@Override
	public List<JobOffer> notAppliedWithSkills(Long userId, List<JobApplication> applications, List<Skill> skills, Integer page, Integer perPage) {
		final TypedQuery<JobOffer> query = em.createQuery(
				"select o from JobOffer o join o.skills s where s.id in (:skills) and (o.id not in (:applied) and o.user.id != :userId) ORDER BY o.createdAt DESC", JobOffer.class);
		query.setParameter("skills", skillsIds(skills));
		query.setParameter("userId", userId);
		query.setParameter("applied", applicationOfferIds(applications));
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}
	
	private List<Long> applicationOfferIds(List<JobApplication> applications) {
		List<Long> result = new LinkedList<Long>();
		for (JobApplication application : applications) {
			result.add(application.getJobOffer().getId());
		}
		return result;
	}

}
