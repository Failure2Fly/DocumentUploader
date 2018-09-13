package com.fdmgroup.documentuploader.rowmapper;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fdmgroup.documentuploader.pojo.Document;

public class DocumentMapper implements RowMapper<Document> {

	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
		Document document = new Document();
		document.setName(rs.getString("file_name"));
		document.setDate(rs.getDate("store_date"));
		document.setAccountId(rs.getInt("associated_account_id"));
		document.setRepositoryPath(Paths.get(rs.getString("stored_file_path")));
		document.setSourcePath(null);

		return document;
	}

}
