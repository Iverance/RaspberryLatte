package edu.sjsu.cab.storage;

import java.util.List;


public interface CabStorageService {
	
	public Request createRequest(Request storage) throws Exception;
	
	public Boolean deleteUserStorageByUuid(String userUuid) throws Exception;
	public Boolean deleteTransactionStorageByUuid(String transactionUuid) throws Exception;
	public Boolean deleteDriverByUuid(String driverUuid) throws Exception;
	public Boolean deleteVehicleStorageByUuid(String vehicleUuid) throws Exception;
	public Boolean deleteDynamicRouteStorageByUuid(String dynamicRouteId) throws Exception;
	public Boolean deleteRouteStorageByUuid(String routeId) throws Exception;
	
	public List<Request> findUserByUuid(String uuid)  throws Exception;
	public List<Request> findUserByRequest(Double lat, Double lng);
	public List<Request> findTransactionByUuid(String uuid)  throws Exception;
	public List<Request> findDriverByUuid(String uuid)  throws Exception;
	public List<Request> findVehicleByUuid(String uuid)  throws Exception;
	public List<Request> findDynamicRouteByUuid(String uuid)  throws Exception;
	public List<Request> findRouteByUuid(String uuid)  throws Exception;

    
	
}
