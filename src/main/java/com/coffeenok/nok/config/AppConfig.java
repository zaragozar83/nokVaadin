package com.coffeenok.nok.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.coffeenok.nok.domain.AuthManager;
import com.coffeenok.nok.ui.LoginFormListener;

@Configuration
@ComponentScan(basePackages = {"com.coffeenok.nok.ui", "com.coffeenok.nok.service.impl", "com.coffeenok.nok.dao.impl", "com.coffeenok.nok.domain"})
public class AppConfig {

	@Autowired
	private DriverManagerDataSource dataSource;
	
	
	@Bean
	public DriverManagerDataSource driverManagerDataSource() {
		String driverClassName = "com.mysql.jdbc.Driver";
		//String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
		String url = "jdbc:mysql://localhost:3306/maqzar";
		String username = "root";
		String password = "";
		DriverManagerDataSource res = new DriverManagerDataSource();
		res.setDriverClassName(driverClassName);
		res.setUrl(url);
		res.setUsername(username);
		res.setPassword(password);
		return res;
	}
	
	/*
	@Bean DriverManagerDataSource driverManagerDataSource(){
			String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://AcciDev.lac.nsroot.net:2431;DatabaseName=AS400";
			String username = "weblogic";
			String password = "logic";
			DriverManagerDataSource res = new DriverManagerDataSource();
			res.setDriverClassName(driverClassName);
			res.setUrl(url);
			res.setUsername(username);
			res.setPassword(password);
			return res;
	}*/
	
	
	/*@Bean
	public OrderService orderService(){
		return new OrderServiceImpl();
	}*/
	
	/*@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}*/
	
	/*@Bean
	public OrderDao orderDAO() {
		return new OrderDaoImpl();
	}*/
	/*
	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}
*/
	@Bean
	public AuthManager authManager(){
		AuthManager auth = new AuthManager();
		return auth;
	}
	
	 @Bean
    public LoginFormListener loginFormListener() {
        return new LoginFormListener();
    }
}
