	package com.example.springjspdemo.dao.impl;
	
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	import javax.sql.DataSource;
	
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.jdbc.core.RowMapper;
	import org.springframework.jdbc.core.support.JdbcDaoSupport;
	import org.springframework.stereotype.Repository;
	
	import com.example.springjspdemo.controller.bean.User;
	import com.example.springjspdemo.dao.UserDao;
	
	import jakarta.annotation.PostConstruct;
	@Repository
	public class UserDaoImpl extends JdbcDaoSupport implements UserDao{
	
		@Autowired
		DataSource dataSource;
		@PostConstruct
		public void initialize()//based on app.properties
		{
			setDataSource(dataSource);
		}
		
		
		
		@SuppressWarnings("deprecation")
		@Override
		public User getUserById(String userId) {//actual querying
			//String sql="select cast(aes_decrypt (unhex(`user_pswd`), 'secret') as char(50)) from `user` where user_id=?";
			//String sql = "SELECT CAST(AES_DECRYPT(UNHEX(`user_pswd`), 'secret') AS CHAR(50)) FROM `user` WHERE user_id=?";
	 
			//just a string
			String sql = "SELECT user_pswd FROM users WHERE user_id=?";
	
			
			return getJdbcTemplate().queryForObject(sql, new Object[] { userId }, new RowMapper<User>()
		        {
				@Override
				public User mapRow (ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
			//	user.setUserId(userId);
				//user.setPassword(rs.getString(1));
				user.setPassword(rs.getString("user_pswd"));
	
				return user;
				}
		});
		}
	
	
		@Override
		public int insertuser(String username, String password) {
			
			String sql2 = "INSERT INTO users(user_id, user_pswd) VALUES(?, ?)";
			
			int i=0;
			i=getJdbcTemplate().update(sql2,username,password);
			return i;
		}
	
	}
	
