package edu.sjsu.cab.storage;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CabStorageServiceImplementation extends CabDaoAbstract implements CabStorageService {

    public Request createRequest(Request request) throws Exception {
        save(request);
        return request;
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
    public List<Request> findUserByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> findTransactionByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> findDriverByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> findVehicleByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> findDynamicRouteByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> findRouteByUuid(String uuid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<Request> findUserByRequest(Double lat, Double lng) {
        // TODO Auto-generated method stub
        List<Request> request = new ArrayList<Request>();
        try {
            // @formatter:off
            String query = "SELECT * FROM cab.Request "
                    + "WHERE cab.Request.isPicked = 'n' "
                    + "ORDER BY ABS( pickupLocationLat - ("+String.valueOf(lat)+"))+ABS(pickupLocationLong-("+String.valueOf(lng)+")) "
                    + "LIMIT 4";
            // @formatter:on
            request = (List<Request>) getCurrentSession().createSQLQuery(query).setResultTransformer( Transformers.aliasToBean(Request.class)).list();

//            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Request.class);
//            detachedCriteria.add(Restrictions.eq("isPicked", "n"));
//            users = findByCriteriaWithLimit(detachedCriteria, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }
}
