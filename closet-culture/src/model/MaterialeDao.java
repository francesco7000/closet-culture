package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.ArrayList;

public class MaterialeDao {
    static final Logger logger = Logger.getLogger("MyLogger");

    public synchronized ArrayList<MaterialeBean> doRetrieveAll() {


        ArrayList<MaterialeBean> materiali = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        MaterialeBean tempMateriale = new MaterialeBean();

        String sqlSelect = "select m.id,m.tipo from materiale m ";
        try {
            connection =  DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sqlSelect);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                tempMateriale = new MaterialeBean();
                tempMateriale.setId(rs.getInt("id"));
                tempMateriale.setTipo(rs.getString("tipo"));
                materiali.add(tempMateriale);

            }
        } 
        catch (SQLException e) {
            logger.log(null, "Eccezione non gestita: ");
        } 
        finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                DriverManagerConnectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                logger.log(null, "Eccezione non gestita: ");
            }
        }

        return materiali;
    }

}