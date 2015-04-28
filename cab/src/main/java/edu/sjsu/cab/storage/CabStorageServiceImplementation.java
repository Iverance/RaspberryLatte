package edu.sjsu.cab.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CabStorageServiceImplementation extends CabDaoAbstract implements CabStorageService{

    Connection conn = null;
    Statement stmt = null;

    public User createCabStorage(User user) throws Exception {

        conn = getConnection();

        stmt = conn.createStatement();
        PreparedStatement ps = null;

        try {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO cabStorage (`userId`,`transactionId`,`email`,`firstName`," + "`lastName`,`isDynamicRoute`,`password`,`pickupLocationLat`,`pickupLocationLong`," + "`destLocationLat`,`destLocationLong`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getCabTransactionId());
            ps.setFloat(3, user.getDestLocationLat());
            ps.setFloat(6, user.getDestLocationLong());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getFirstName());
            ps.setFloat(6, user.getPickupLocationLat());
            ps.setFloat(6, user.getPickupLocationLong());
            ps.setString(6, user.getLastName());

            ps.executeUpdate();
            conn.commit();

        } finally {
            ps.close();
            conn = null;
        }

        return findUserByUuid(user.getUserId()).get(0);
    }

    /*
     * public Boolean deleteCabStorage(Long userId) throws Exception {
     * 
     * PreparedStatement ps = null; Boolean success = false; conn = getConnection();
     * 
     * try {
     * 
     * String sql = "DELETE FROM user where user.userId = ?;";
     * 
     * ps = conn.prepareStatement(sql); ps.setLong(1, userId);
     * 
     * ps.executeQuery(); success = true; return success;
     * 
     * } catch (Exception ex) { success = false; return success; } finally { ps.close(); conn = null; } }
     * 
     * public BCabStorageProfile findByUuid(String uuid) throws Exception {
     * 
     * PreparedStatement ps = null; BlobStorage blob = null; conn = getConnection();
     * 
     * try {
     * 
     * String sqlSelect = "SELECT * FROM user where user.userId = ?;";
     * 
     * ps = conn.prepareStatement(sqlSelect); ps.setString(1, uuid);
     * 
     * ResultSet rs = ps.executeQuery();
     * 
     * if (rs.next()) { cab = new CabStorage(); cab.setUserId(rs.getLong("blobStorageId")); . . . }
     * 
     * return new CabStorageProfile(cab); } finally { ps.close(); blob = null; conn = null; } }
     * 
     * @Override public Boolean deleteCabStorageByUuid(String uuid) throws Exception { PreparedStatement ps = null; Boolean success = false; conn = getConnection();
     * 
     * try {
     * 
     * String safeSql = "SET SQL_SAFE_UPDATES=0;"; ps = conn.prepareStatement(safeSql); ps.executeQuery();
     * 
     * 
     * String sql = "DELETE FROM user where user.userId = ?";
     * 
     * ps = conn.prepareStatement(sql); ps.setString(1, userId);
     * 
     * ps.executeQuery();
     * 
     * success = true; return success;
     * 
     * } catch (Exception ex) { success = false; return success; } finally { ps.close(); conn = null;
     * 
     * }
     * 
     * }
     */

    private Connection getConnection() {

        Connection localConn = null;
        try {

            Class.forName(CabDBConfiguration.getJdbcDriver());
            localConn = DriverManager.getConnection(edu.sjsu.cab.storage.CabDBConfiguration.getLocalDbUrl(), edu.sjsu.cab.storage.CabDBConfiguration.getLocalDbUser(), edu.sjsu.cab.storage.CabDBConfiguration.getLocalDbPass());

            return localConn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localConn;
    }

    @Override
    public Boolean deleteUserStorageByUuid(String userUuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteTransactionStorageByUuid(String transactionUuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteDriverByUuid(String driverUuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteVehicleStorageByUuid(String vehicleUuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteDynamicRouteStorageByUuid(String dynamicRouteId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteRouteStorageByUuid(String routeId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findUserByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findTransactionByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findDriverByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findVehicleByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findDynamicRouteByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findRouteByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(value="transactionManager")
    public List<User> findUserByRequest() {
        // TODO Auto-generated method stub
        List<User> users= new ArrayList<User>();
        try {
            
            String query = "SELECT * from cab.User where cab.User.isPicked = 'n' LIMIT 10";
            
            SQLQuery sessionQuery = getCurrentSession().createSQLQuery(query);
            List<Object[]> records = sessionQuery.list();
            
            for (Object[] record : records) {
                User user = new User();

            }
            
            
//            while(rs.next()) {
//                User user = new User();
//                user.setCabTransactionId(rs.getString("transactionId"));
//                user.setDestLocationLat(rs.getFloat("destLocationLat"));
//                user.setDestLocationLong(rs.getFloat("destLocationLong"));
//                user.setEmail(rs.getString("email"));
//                user.setFirstName(rs.getString("firstName"));
//                user.setIsDyamicRoute(rs.getString("isDyamicRoute"));
//                user.setIsPicked(rs.getString("isPicked"));
//                user.setLastName(rs.getString("lastName"));
//                user.setPassword(rs.getString("password"));
//                user.setPickupLocationLat(rs.getFloat("pickupLocationLat"));
//                user.setPickupLocationLong(rs.getFloat("pickupLocationLong"));
//                user.setUserId(rs.getString("userId"));
//                users.add(user);
//             } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
