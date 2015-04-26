package edu.sjsu.cab.storage;

import java.sql.*;
import java.util.List;


public class CabStorageServiceImplementation implements CabStorageService {

	Connection conn = null;
	Statement stmt = null;


	public CabStorageProfile createCabStorage(CabStorage storage)
			throws Exception {
		
		conn = getConnection();

		stmt = conn.createStatement();
		PreparedStatement ps = null;

		try {

			conn.setAutoCommit(false);
			String sql = "INSERT INTO cabStorage (`userId`,`transactionId`,`email`,`firstName`,"
					+ "`lastName`,`isDynamicRoute`,`password`,`pickupLocationLat`,`pickupLocationLong`,"
					+ "`destLocationLat`,`destLocationLong`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, storage.getUserId());
			ps.setString(2, storage.getCabTransactionId());
			ps.setString(3, storage.getDestLocationLat());
			ps.setString(6, storage.getDestLocationLong());
			ps.setString(4, storage.getEmail());
			ps.setString(5, storage.getFirstName());
			ps.setString(6, storage.getPickupLocationLat());
			ps.setString(6, storage.getPickupLocationLong());
			ps.setString(6, storage.getLastName());


			ps.executeUpdate();
			conn.commit();

		} finally {
			ps.close();
			conn = null;
		}

		return (CabStorageProfile) findUserByUuid(storage.getUserId());
	}
/*
	public Boolean deleteCabStorage(Long userId) throws Exception {

		PreparedStatement ps = null;
		Boolean success = false;
		conn = getConnection();
		
		try {

			String sql = "DELETE FROM user where user.userId = ?;";

			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);

			ps.executeQuery();
			success = true;
			return success;

		} catch (Exception ex) {
			success = false;
			return success;
		} finally {
			ps.close();
			conn = null;
		}
	}

	public BCabStorageProfile findByUuid(String uuid) throws Exception {

		PreparedStatement ps = null;
		BlobStorage blob = null;
		conn = getConnection();
		
		try {

			String sqlSelect = "SELECT * FROM user where user.userId = ?;";

			ps = conn.prepareStatement(sqlSelect);
			ps.setString(1, uuid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				cab = new CabStorage();
				cab.setUserId(rs.getLong("blobStorageId"));
				.
				.
				.
			}

			return new CabStorageProfile(cab);
		} finally {
			ps.close();
			blob = null;
			conn = null;
		}
	}

	@Override
	public Boolean deleteCabStorageByUuid(String uuid) throws Exception {
		PreparedStatement ps = null;
		Boolean success = false;
		conn = getConnection();

		try {

			String safeSql = "SET SQL_SAFE_UPDATES=0;";
			ps = conn.prepareStatement(safeSql);
			ps.executeQuery();
			
			
			String sql = "DELETE FROM user where user.userId = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			ps.executeQuery();

			success = true;
			return success;

		} catch (Exception ex) {
			success = false;
			return success;
		} finally {
			ps.close();
			conn = null;

		}

	}
	*/
	
	private Connection getConnection(){
		
		Connection localConn=null;
		try {

			Class.forName(CabDBConfiguration.getJdbcDriver());
			 localConn = DriverManager.getConnection(
					edu.sjsu.cab.storage.CabDBConfiguration.getLocalDbUrl(),
					edu.sjsu.cab.storage.CabDBConfiguration.getLocalDbUser(),
					edu.sjsu.cab.storage.CabDBConfiguration.getLocalDbPass());

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
	public Boolean deleteTransactionStorageByUuid(String transactionUuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteDriverByUuid(String driverUuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteVehicleStorageByUuid(String vehicleUuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteDynamicRouteStorageByUuid(String dynamicRouteId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteRouteStorageByUuid(String routeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CabStorageProfile> findUserByUuid(String uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CabStorageProfile> findTransactionByUuid(String uuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CabStorageProfile> findDriverByUuid(String uuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CabStorageProfile> findVehicleByUuid(String uuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CabStorageProfile> findDynamicRouteByUuid(String uuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CabStorageProfile> findRouteByUuid(String uuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
