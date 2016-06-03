package com.coffeenok.nok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coffeenok.nok.dao.UserDao;
import com.coffeenok.nok.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	//@Autowired
		//private JdbcTemplate jdbcTemplate;
		@Autowired
		private DataSource dataSource;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List<User> findAllUser() {
			Connection conn = null;
			List<User> list = new ArrayList<User>();
			try{
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement("Select * FROM T_User");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("authority"));
					list.add(user);
				}
				rs.close();
				ps.close();
			}catch(SQLException e){
				throw new RuntimeException(e);
			}finally{
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						
					}
				}
			}
			return list;
			
			/*String query = "select * from T_User";
			RowMapper mapper = new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setAuthority(rs.getString("authority"));
					return user;
				}
			};
			return jdbcTemplate.query(query, mapper);*/
		}

}
