DROP DATABASE IF EXISTS java_db;
CREATE DATABASE java_db;
USE java_db;
CREATE TABLE properties (
    propertyId INT PRIMARY KEY
);
CREATE TABLE agents (
    agentId   INT PRIMARY KEY,
    agentName VARCHAR(255)
);

CREATE TABLE buyers (
    buyerId   INT PRIMARY KEY,
    buyerName VARCHAR(255),
    gender    CHAR(1),
    age INT
);

CREATE TABLE houses (
    propertyId     INT PRIMARY KEY,
    rooms          INT,
    bathrooms      INT,
    landsize       INT,
    hasGarden      TINYINT(1),
    parkingSpot    INT,
    buildingSize   INT,
    yearBuilt      INT,
    regionName     VARCHAR(255),
    address        VARCHAR(255),
    latitude       DOUBLE,
    longitude      DOUBLE,
    distance       DOUBLE,
    postcode       INT,
    councilArea    VARCHAR(255),
    propertyCount  INT,
    hasSwimmingPool TINYINT(1),
    hasFence       TINYINT(1),
	FOREIGN KEY (propertyId) REFERENCES properties(propertyId)
);

CREATE TABLE units (
    propertyId    INT PRIMARY KEY,   -- AUTO_INCREMENT
    rooms         INT,
    bathrooms      INT,
    parkingSpot   INT,
    landsize      INT,
    buildingSize  INT,
    yearBuilt     INT,
    floorLevel    INT,
    regionName    VARCHAR(255),
    address       VARCHAR(255),
    latitude      DOUBLE,
    longitude     DOUBLE,
    distance      DOUBLE,
    postcode      INT,
    councilArea   VARCHAR(255),
    propertyCount INT,
    hasBalcony    TINYINT(1),
    hasElevator   TINYINT(1),
	FOREIGN KEY (propertyId) REFERENCES properties(propertyId)
);

CREATE TABLE townhouses (
    propertyId          INT PRIMARY KEY,  -- AUTO_INCREMENT
    landsize            INT,
    rooms               INT,
    bathrooms            INT,
    parkingSpot         INT,
    buildingSize        INT,
    regionName          VARCHAR(255),
    address             VARCHAR(255),
    latitude            DOUBLE,
    longitude           DOUBLE,
    distance            DOUBLE,
    postcode            INT,
    councilArea         VARCHAR(255),
    propertyCount       INT,
    yearBuilt           INT,
    numberOfSharedWalls INT,
    numberOfLevels      INT,
	FOREIGN KEY (propertyId) REFERENCES properties(propertyId)
);

CREATE TABLE developmentSite (
    propertyId        INT PRIMARY KEY,   -- AUTO_INCREMENT
    landsize          INT,
    regionName        VARCHAR(255),
    address           VARCHAR(255),
    latitude          DOUBLE,
    longitude         DOUBLE,
    distance          DOUBLE,
    postcode          INT,
    councilArea       VARCHAR(255),
    propertyCount     INT,
    isLandCleared     TINYINT(1),
    underConstruction TINYINT(1),
	FOREIGN KEY (propertyId) REFERENCES properties(propertyId)
);

CREATE TABLE transactions (
    transactionId INT PRIMARY KEY,
    price         DOUBLE,
    method        VARCHAR(50),
    agentId       INT,
    buyerId       INT,
    propertyId    INT,
    dateT          DATE,
    FOREIGN KEY (agentId) REFERENCES agents(agentId),
    FOREIGN KEY (buyerId) REFERENCES buyers(buyerId),
    FOREIGN KEY (propertyId) REFERENCES properties(propertyId)
);
