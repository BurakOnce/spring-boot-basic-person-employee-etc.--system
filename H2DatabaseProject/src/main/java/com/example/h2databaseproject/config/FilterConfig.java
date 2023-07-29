package com.example.h2databaseproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());

        filter.addUrlPatterns("/PostPerson");
        filter.addUrlPatterns("/UpdatePerson");
        filter.addUrlPatterns("/DeletePerson");
        filter.addUrlPatterns("/DeleteAllPersons");
        filter.addUrlPatterns("/CountPersons");
        filter.addUrlPatterns("/GetAllPersons");
        filter.addUrlPatterns("/GetOnePerson");
        filter.addUrlPatterns("/FindPersonsByFirstName");
        filter.addUrlPatterns("/FindPersonsByDesiredAge");
        filter.addUrlPatterns("/FindPersonsByYoungerThenDesiredAge");
        filter.addUrlPatterns("/FindPersonsByOlderThenDesiredAge");
        filter.addUrlPatterns("/SortAscPersonsByAge");
        filter.addUrlPatterns("/SortDescPersonsByAge");

        return filter;
    }
}
