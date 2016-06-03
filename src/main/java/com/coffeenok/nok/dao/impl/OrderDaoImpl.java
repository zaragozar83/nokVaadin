package com.coffeenok.nok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coffeenok.nok.dao.OrderDao;
import com.coffeenok.nok.domain.Order;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/
	@Autowired
	private DataSource dataSource;
	
	
	public void createDbTable() {
		//jdbcTemplate.execute("create table if not exists orders (id integer, nombre varchar(100))");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Order> findAll() {
		Connection conn = null;
		List<Order> list = new ArrayList<Order>();
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM T_Orders");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Order order = new Order(rs.getInt("id"), rs.getString("nombre"));
				list.add(order);
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
		
		/*
		String query = "select * from T_Orders";
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setNombre(rs.getString("nombre"));
				return order;
			}
		};
		return jdbcTemplate.query(query, mapper);*/
	}

	public void save(Order order) {
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO T_Orders (nombre) values ('"+order.getNombre()+"')");
			st.close();
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
		
		//String query = "insert into T_Orders (nombre) values (?)";
		//jdbcTemplate.update(query, new Object[]{order.getNombre()});
	}

	public void deleteOrder(Order order) {
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("DELETE FROM T_Orders WHERE id = ?", order.getId());
			st.close();
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
		/*String query = "delete T_Orders where id = ?";
		jdbcTemplate.update(query, new Object[]{order.getId()});*/
	}

}
