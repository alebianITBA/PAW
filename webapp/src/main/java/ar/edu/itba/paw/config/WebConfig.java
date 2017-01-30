package ar.edu.itba.paw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@EnableWebMvc
@ComponentScan({ "ar.edu.itba.paw.controllers", "ar.edu.itba.paw.services", "ar.edu.itba.paw.persistence" })
@Configuration
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter {

  private static final int CACHE_DURATION_SECONDS = 5;
  private static final String RESOURCES_DIR = "/resources/";
  private static final String RESOURCES_PATH = "/**";
  private final static String PREFIX_VIEW_RESOLVER = "/WEB-INF/jsp/";
  private final static String SUFFIX_VIEW_RESOLVER = ".jsp";

  @Value("classpath:schema.sql")
  private Resource schemaSql;

  @Bean
  public ViewResolver viewResolver() {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix(PREFIX_VIEW_RESOLVER);
    viewResolver.setSuffix(SUFFIX_VIEW_RESOLVER);
    return viewResolver;
  }

  @Bean
  public DataSource dataSource() throws ClassNotFoundException {
    final SimpleDriverDataSource ds = new SimpleDriverDataSource();
    if (System.getenv().containsKey("PAW_ENVIRONMENT") && System.getenv("PAW_ENVIRONMENT").equals("development")) {
      ds.setDriverClass(org.postgresql.Driver.class);
      ds.setUrl("jdbc:postgresql://localhost:5432/paw");
      ds.setUsername("paw");
      ds.setPassword("paw");
    } else {
      ds.setDriverClass(org.postgresql.Driver.class);
      ds.setUrl("jdbc:postgresql://10.16.1.110:5432/grupo5");
      ds.setUsername("grupo5");
      ds.setPassword("yoo9oTh0");
    }
    return ds;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
    final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setPackagesToScan("ar.edu.itba.paw.models");
    factoryBean.setDataSource(dataSource());

    final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    factoryBean.setJpaVendorAdapter(vendorAdapter);

    final Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");

    factoryBean.setJpaProperties(properties);

    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  @Bean
  public MessageSource messageSource() {
    final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
    messageSource.setCacheSeconds(CACHE_DURATION_SECONDS);
    return messageSource;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(RESOURCES_PATH).addResourceLocations(RESOURCES_DIR);
  }
}
