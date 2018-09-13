package com.fdmgroup.documentuploader.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class QuestionDao implements Dao<String, Integer> {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(String item) {
		String SQL = "INSERT INTO SECURITY_QUESTION VALUES (?,?)";
	}

	@Override
	public void delete(String item) {
		String SQL = "SELECT";
	}

	@Override
	public void update(String item) {
		String SQL = "";
	}

	@Override
	public String read(Integer item) {
		int intItem = (int) item;
		String SQL = "SELECT QUESTION FROM SECURITY_QUESTION WHERE question_id = ?";
		String question = jdbcTemplateObject.queryForObject(SQL, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		}, intItem);
		return question;
	}

	public List<String> readAll() {
		String SQL = "SELECT QUESTION FROM SECURITY_QUESTION";
		List<String> list = jdbcTemplateObject.query(SQL, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});

		return list;
	}

}
