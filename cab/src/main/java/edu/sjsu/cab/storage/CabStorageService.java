package edu.sjsu.cab.storage;

import java.util.List;


public interface CabStorageService {
	
	public User createCabStorage(User storage) throws Exception;
	
	public Boolean deleteUserStorageByUuid(String userUuid) throws Exception;
	public Boolean deleteTransactionStorageByUuid(String transactionUuid) throws Exception;
	public Boolean deleteDriverByUuid(String driverUuid) throws Exception;
	public Boolean deleteVehicleStorageByUuid(String vehicleUuid) throws Exception;
	public Boolean deleteDynamicRouteStorageByUuid(String dynamicRouteId) throws Exception;
	public Boolean deleteRouteStorageByUuid(String routeId) throws Exception;
	
	public List<User> findUserByUuid(String uuid)  throws Exception;
	public List<User> findUserByRequest();
	public List<User> findTransactionByUuid(String uuid)  throws Exception;
	public List<User> findDriverByUuid(String uuid)  throws Exception;
	public List<User> findVehicleByUuid(String uuid)  throws Exception;
	public List<User> findDynamicRouteByUuid(String uuid)  throws Exception;
	public List<User> findRouteByUuid(String uuid)  throws Exception;
	
}
