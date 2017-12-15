package com.bloodshare;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.adrjun.firebase.FireBaseAdmin;
import com.bloodshare.filter.TokenFilter;

@SpringBootApplication
@ComponentScan("com")
public class SpringApplicationLauncher {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationLauncher.class, args);
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
		fact.setEntityManagerFactory(emf);
		return fact;
	}
	
	@Bean
	public FilterRegistrationBean tokenFilerRegister()
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new TokenFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
	
	@Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
          = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }
	
//	@Bean
//	public FireBaseAdmin fireBase()
//	{
//		return new FireBaseAdmin();
//	}
}
