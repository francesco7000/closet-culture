package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialeDao {
	
	public synchronized ArrayList<MaterialeBean> doRetrieveAll() {

		ArrayList<MaterialeBean> materiali = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		MaterialeBean tempMateriale = new MaterialeBean();

		String sqlSelect = "select m.id,m.tipo from materiale m ";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				tempMateriale = new MaterialeBean();
				tempMateriale.setId(rs.getInt("id"));
				tempMateriale.setTipo(rs.getString("tipo"));
				materiali.add(tempMateriale);

			}
		} 
		catch (SQLException e) {
			
			//e.printStackTrace();
		} 
		finally {
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				
				//e.printStackTrace();
			}
		}

		return materiali;
	}

}
