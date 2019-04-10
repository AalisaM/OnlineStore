package jschool.configs;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "jschool")
public class SpringWebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/")
        .addResourceLocations("/webapp/")
        .addResourceLocations("/webapp/**");
    registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
  }

  @Bean
  public CommonsMultipartResolver multipartResolver(){
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    return resolver;
  }


  @Bean(name = "dataSource")
  public DataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/store?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    driverManagerDataSource.setUsername("userT");
    driverManagerDataSource.setPassword("1234");
    return driverManagerDataSource;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/logout").setViewName("logout");
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver =
        new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(
        "jschool.model");
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  Properties hibernateProperties() {
    return new Properties() {
      {
        setProperty("hibernate.show_sql",
            "false");
        setProperty("hibernate.dialect",
            "org.hibernate.dialect.MySQLDialect");
        setProperty("hibernate.id.new_generator_mappings","false");

      }
    };
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(
      SessionFactory sessionFactory) {

    HibernateTransactionManager txManager
        = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);

    return txManager;
  }

  @Bean(name = "multipartResolver")
  public StandardServletMultipartResolver resolver() {
    return new StandardServletMultipartResolver();
  }


  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
