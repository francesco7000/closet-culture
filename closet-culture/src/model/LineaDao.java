package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class LineaDao {
	static final Logger logger = Logger.getLogger("MyLogger");

	public synchronized ArrayList<LineaBean> doRetrieveAll() {

		ArrayList<LineaBean> linee = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		LineaBean tempLinea = new LineaBean();

		String sqlSelect = "select l.id,l.descrizione,l.codice from linea l ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement =connection.prepareStatement(sqlSelect);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				tempLinea = new LineaBean();
				tempLinea.setId(rs.getInt("id"));
				tempLinea.setDescrizione(rs.getString("descrizione"));
				linee.add(tempLinea);

			}
		} 
		catch (SQLException e) {
			logger.log(null, "Eccezione non gestita: ");
		} 
		finally {
			try {
				if(preparedStatement!=null)
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				logger.log(null, "Eccezione non gestita: ");
			}
		}

		return linee;
	}

}
