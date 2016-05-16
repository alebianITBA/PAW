package ar.edu.itba.paw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

@EnableWebMvc
@ComponentScan({ "ar.edu.itba.paw.controllers", "ar.edu.itba.paw.services", "ar.edu.itba.paw.persistence" })
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
  static final String RESOURCES_DIR = "/resources/";

  @Value("classpath:schema.sql")
  private Resource schemaSql;

  @Bean
  public ViewResolver viewResolver() {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource ds) {
    final DataSourceInitializer dsi = new DataSourceInitializer();
    dsi.setDataSource(ds);
    dsi.setDatabasePopulator(databasePopulator());
    return dsi;
  }

  private DatabasePopulator databasePopulator() {
    final ResourceDatabasePopulator dbp = new ResourceDatabasePopulator();
    dbp.addScript(schemaSql);
    return dbp;
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
  public MessageSource messageSource() {
    final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("/WEB-INF/i18n/messages");
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
    messageSource.setCacheSeconds(5);
    return messageSource;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations(RESOURCES_DIR);
  }
}
