package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LineaDao {

	public synchronized ArrayList<LineaBean> doRetrieveAll() {

		ArrayList<LineaBean> linee = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		LineaBean tempLinea = new LineaBean();

		String sqlSelect = "select l.id,l.descrizione,l.codice from linea l ";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				tempLinea = new LineaBean();
				tempLinea.setId(rs.getInt("id"));
				tempLinea.setDescrizione(rs.getString("descrizione"));
				linee.add(tempLinea);

			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return linee;
	}

}
