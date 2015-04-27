package edu.sjsu.cab.storage;

import java.util.List;

import edu.sjsu.cab.storage.CabStorage;
import edu.sjsu.cab.storage.CabStorageProfile;

public interface CabStorageService {
	
	public CabStorageProfile createCabStorage(CabStorage storage) throws Exception;
	
	public Boolean deleteUserStorageByUuid(String userUuid) throws Exception;
	public Boolean deleteTransactionStorageByUuid(String transactionUuid) throws Exception;
	public Boolean deleteDriverByUuid(String driverUuid) throws Exception;
	public Boolean deleteVehicleStorageByUuid(String vehicleUuid) throws Exception;
	public Boolean deleteDynamicRouteStorageByUuid(String dynamicRouteId) throws Exception;
	public Boolean deleteRouteStorageByUuid(String routeId) throws Exception;
	
	public List<CabStorageProfile> findUserByUuid(String uuid)  throws Exception;
	public List<CabStorageProfile> findTransactionByUuid(String uuid)  throws Exception;
	public List<CabStorageProfile> findDriverByUuid(String uuid)  throws Exception;
	public List<CabStorageProfile> findVehicleByUuid(String uuid)  throws Exception;
	public List<CabStorageProfile> findDynamicRouteByUuid(String uuid)  throws Exception;
	public List<CabStorageProfile> findRouteByUuid(String uuid)  throws Exception;
	
}
