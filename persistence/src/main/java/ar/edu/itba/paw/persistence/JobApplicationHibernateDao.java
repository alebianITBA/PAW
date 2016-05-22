package ar.edu.itba.paw.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;

@Repository
public class JobApplicationHibernateDao implements JobApplicationDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public JobApplication create(String description, User user, JobOffer jobOffer) {
		final JobApplication jobApplication = new JobApplication(description, user, jobOffer, new java.util.Date());
		em.persist(jobApplication);
		return jobApplication;
	}

	@Override
	public void delete(Long id) {
		Query q = em.createQuery("delete from JobApplication where id = :id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public JobApplication update(Long id, String description) {
		JobApplication application = find(id);
		if (application == null) {
			return null;
		}
		if (description != null && !description.isEmpty()) {
			application.setDescription(description);
		}
		em.persist(application);
		return application;
	}

	@Override
	public Long count() {
		Query query = em.createQuery("SELECT count(*) FROM JobApplication");
		return (Long) query.getSingleResult();
	}

	@Override
	public JobApplication find(Long id) {
		final TypedQuery<JobApplication> query = em.createQuery("from JobApplication as a where a.id = :id",
				JobApplication.class);
		query.setParameter("id", id);
		final List<JobApplication> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<JobApplication> all() {
		final TypedQuery<JobApplication> query = em.createQuery("from JobApplication as j ORDER BY j.createdAt DESC",
				JobApplication.class);
		return query.getResultList();
	}

	@Override
	public List<JobApplication> all(Integer page, Integer perPage) {
		final TypedQuery<JobApplication> query = em.createQuery("from JobApplication as j ORDER BY j.createdAt DESC",
				JobApplication.class);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

	@Override
	public List<JobApplication> userJobApplications(Long userId) {
		final TypedQuery<JobApplication> query = em.createQuery(
				"from JobApplication as a where a.user.id = :userId ORDER BY a.createdAt DESC", JobApplication.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<JobApplication> userJobApplications(Long userId, Integer page, Integer perPage) {
		final TypedQuery<JobApplication> query = em.createQuery(
				"from JobApplication as a where a.user.id = :userId ORDER BY a.createdAt DESC", JobApplication.class);
		query.setParameter("userId", userId);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

	@Override
	public List<JobApplication> jobOfferApplications(Long jobOfferId) {
		final TypedQuery<JobApplication> query = em.createQuery(
				"from JobApplication as a where a.jobOffer.id = :jobOfferId ORDER BY a.createdAt DESC",
				JobApplication.class);
		query.setParameter("jobOfferId", jobOfferId);
		return query.getResultList();
	}

	@Override
	public List<JobApplication> jobOfferApplications(Long jobOfferId, Integer page, Integer perPage) {
		final TypedQuery<JobApplication> query = em.createQuery(
				"from JobApplication as a where a.jobOffer.id = :jobOfferId ORDER BY a.createdAt DESC",
				JobApplication.class);
		query.setParameter("jobOfferId", jobOfferId);
		query.setFirstResult(page * perPage);
		query.setMaxResults(perPage);
		return query.getResultList();
	}

	@Override
	public void removeJobOfferApplications(Long jobOfferId) {
		Query query = em.createQuery("delete from JobApplication j where j.jobOffer.id = :id");
		query.setParameter("id", jobOfferId);
		query.executeUpdate();
	}

}
