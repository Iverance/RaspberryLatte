DROP DATABASE IF EXISTS cab;       -- Delete if it exists
CREATE DATABASE cab;
USE cab;

-- CREATE TABLES --
CREATE TABLE cab.User
(
	userId VARCHAR(50) NOT NULL,
	transactionId VARCHAR(50),
	email VARCHAR(100),
	firstName VARCHAR(20) NOT NULL,
	lastName VARCHAR(20) NOT NULL,
	isDynamicRoute ENUM ('y','n') DEFAULT 'n',
	password VARCHAR(20),
	pickupLocationLat FLOAT (11,7),
	pickupLocationLong FLOAT (11,7),
	destLocationLat FLOAT (11,7),
	destLocationLong FLOAT (11,7),
	userName VARCHAR(20),

	PRIMARY KEY(userId)
	-- FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId)
);

CREATE TABLE cab.CabTransaction
(
	transactionId VARCHAR(50) NOT NULL,
	driverId VARCHAR(50),
	dynamicRouteId VARCHAR(25),
	userId VARCHAR(50),
	vehicleId VARCHAR(50),
	currentLocationLat FLOAT (11,7),
	currentLocationLong FLOAT (11,7),
	fare FLOAT(5,2),
	isTransacComplete ENUM ('y', 'n') DEFAULT 'n',
	paymentMethod ENUM ('cash', 'credit','paypal'),
	destLocationLat FLOAT (11,7),
	destLocationLong FLOAT (11,7),
	rating ENUM ('1', '2', '3', '4', '5'),
	routeId INT,
	staticOrDynamic ENUM ('static', 'dynamic') DEFAULT 'static',
	transactionDate	TIMESTAMP,
	tripLength FLOAT(6,2),
	

	PRIMARY KEY(transactionId)
	-- FOREIGN KEY (driverId) REFERENCES Driver(driverId),
	-- FOREIGN KEY (dynamicRouteId) REFERENCES DynamicRoute(dynamicRouteId),
	-- FOREIGN KEY (userId) REFERENCES User(userId)
	-- FOREIGN KEY (routeId) REFERENCES Route(routeId)
);


CREATE TABLE cab.Driver
(
	driverId VARCHAR(50) NOT NULL,
	transactionId VARCHAR(50),
	userId VARCHAR(50),
	vehicleId VARCHAR(50),
	routeId INT,
	tripName VARCHAR(50),
	driverLocationLat FLOAT (11,7),
	driverLocationLong FLOAT (11,7),
    
	PRIMARY KEY(driverId)
	-- FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId),
	-- FOREIGN KEY (userId) REFERENCES User(userId),
	-- FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId)
	-- FOREIGN KEY (routeId) REFERENCES Route(routeId)

);


CREATE TABLE cab.Vehicle
(
	vehicleId VARCHAR(50) NOT NULL,
	driverId VARCHAR(50),
	transactionId VARCHAR(50),
	color VARCHAR(20),
	comments varchar(100),
	numSeatsAvailable INT DEFAULT 4,
	numSeatsTotal INT DEFAULT 4,
	vehicleType ENUM ('coupe', 'hybrid', 'electric', 'sports', 'SUV', 'van', 'truck'),

	PRIMARY KEY(vehicleId)
	-- FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId)
);


CREATE TABLE cab.DynamicRoute
(
	dynamicRouteId VARCHAR(25) NOT NULL,
	transactionId VARCHAR(25),
	routeId INT,
	timeToCalcRoute FLOAT (6,3),

	PRIMARY KEY(dynamicRouteId)
	-- FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId)
	-- FOREIGN KEY (routeId) REFERENCES Route(routeId)
);

CREATE TABLE cab.Route
(
	routeId INT NOT NULL,
	vehicleId VARCHAR(50) NOT NULL,
	driverId VARCHAR(50),
	transactionId VARCHAR(50),
	path1Lat FLOAT (11,7), -- Making an array type representation
	path1Long FLOAT (11,7),
	path2Lat FLOAT (11,7),
	path2Long FLOAT (11,7),
	path3Lat FLOAT (11,7),
	path3Long FLOAT (11,7),
	path4Lat FLOAT (11,7),
	path4Long FLOAT (11,7),
	
	PRIMARY KEY(routeId)
	-- FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId)
	-- FOREIGN KEY (driverId) REFERENCES Driver(driverId)
	-- FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId)
);

-- Map Foreign Keys
ALTER TABLE cab.User ADD FOREIGN KEY (transactionId) REFERENCES CabTransaction (transactionId);
ALTER TABLE cab.CabTransaction ADD FOREIGN KEY (driverId) REFERENCES Driver (driverId);
ALTER TABLE cab.CabTransaction ADD FOREIGN KEY (dynamicRouteId) REFERENCES DynamicRoute(dynamicRouteId);
ALTER TABLE cab.CabTransaction ADD FOREIGN KEY (routeId) REFERENCES Route(routeId);
ALTER TABLE cab.Driver ADD FOREIGN KEY (transactionId) REFERENCES CabTransaction(transactionId);
ALTER TABLE cab.Driver ADD FOREIGN KEY (userId) REFERENCES User (userId);
ALTER TABLE cab.Driver ADD FOREIGN KEY (vehicleId) REFERENCES Vehicle (vehicleId);
ALTER TABLE cab.Driver ADD FOREIGN KEY (routeId) REFERENCES Route (routeId);
ALTER TABLE cab.Vehicle ADD FOREIGN KEY (transactionId) REFERENCES CabTransaction (transactionId);
ALTER TABLE cab.DynamicRoute ADD FOREIGN KEY (transactionId) REFERENCES CabTransaction (transactionId);
ALTER TABLE cab.DynamicRoute ADD FOREIGN KEY (routeId) REFERENCES Route (routeId);
ALTER TABLE cab.Route ADD FOREIGN KEY (vehicleId) REFERENCES Vehicle (vehicleId);
ALTER TABLE cab.Route ADD FOREIGN KEY (driverId) REFERENCES Driver (driverId);
ALTER TABLE cab.Route ADD FOREIGN KEY (transactionId) REFERENCES CabTransaction (transactionId);
