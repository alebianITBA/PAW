package ar.edu.itba.paw.persistence;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
public class DaoTest {

  @PersistenceContext
  private EntityManager em;

  protected void cleanUp() {
    em.createNativeQuery("DELETE FROM user_skills;");
    em.createNativeQuery("DELETE FROM job_offer_skills;");
    em.createNativeQuery("DELETE FROM job_applications;");
    em.createNativeQuery("DELETE FROM posts;");
    em.createNativeQuery("DELETE FROM skills;");
    em.createNativeQuery("DELETE FROM job_offers;");
    em.createNativeQuery("DELETE FROM users;");
  }
  
  protected String tableName() {
    // Override in child classes
    return "";
  }
  
  protected int rowCount() {
    return ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM " + tableName() + ";").getSingleResult()).intValue();
  }
}
