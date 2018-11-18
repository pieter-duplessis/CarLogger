/*
================================
Author:			Pieter du Plessis
Create Date:	2018/03/24
Last Update:	2018/11/16
Language:		MySQL

Description:
This file is for the creation of the database, tables and preset data.

================================
*/

-- Creating the Database
CREATE DATABASE CARLOGGER /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE CARLOGGER;

-- Creating the table to store to details about the cars.
CREATE TABLE CAR (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  carName VARCHAR(30),
  manufacturer TEXT(30),
  model TEXT(30),
  yearMade INT(4) DEFAULT NULL,
  regNo TINYTEXT,
  vinNo TEXT(30),
  active TEXT(10), -- To be Change to boolean value
  PRIMARY KEY (ID),
  CONSTRAINT uc_carName UNIQUE (carName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the table to for the type of events for the log.
CREATE TABLE TYPEEVENT (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  typeEvent VARCHAR(30) NOT NULL,
  typeDescr TEXT(100),
  active TEXT(10), -- To be changed to boolean value
  PRIMARY KEY (ID),
  CONSTRAINT uc_typeEvent UNIQUE (typeEvent)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creating the table to store the log information about the cars.
CREATE TABLE LOG (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  carName VARCHAR(30) NOT NULL,
  date DATE NOT NULL,
  kmReading INT(11) DEFAULT NULL,
  supp VARCHAR(30),
  docNo TEXT(30),
  typeEvent VARCHAR(30) NOT NULL,
  Comment  VARCHAR(9999) DEFAULT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT fk_l_carName FOREIGN KEY (carName) REFERENCES CAR(carName) ON UPDATE CASCADE,
  CONSTRAINT fk_l_typeEvent FOREIGN KEY (typeEvent) REFERENCES TYPEEVENT(typeEvent) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creating the table to store the car problems that have been found on the cars.
CREATE TABLE CARPROBLEM (
	ID INT(11) NOT NULL AUTO_INCREMENT,
	carName VARCHAR(30) NOT NULL,
	date DATE NOT NULL,
	kmReading INT(11) DEFAULT NULL,
	fixed VARCHAR(20), -- To be changed to boolean value
	probName VARCHAR(30),
	comment VARCHAR(9999),
	PRIMARY KEY (ID),
	CONSTRAINT fk_cp_carName FOREIGN KEY (carName) REFERENCES CAR(carName) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creating the table to store the spare part for the car that have not been used to see what you have.
CREATE TABLE SPAREPARTS (
	ID INT(11) NOT NULL AUTO_INCREMENT,
	carName VARCHAR(30),
	date DATE NOT NULL,
	supp VARCHAR(30),
	docNo VARCHAR(30),
	part VARCHAR(30),
	used VARCHAR(20), -- To be changed to boolean value
	comment VARCHAR(9999),
	PRIMARY KEY (ID),
	CONSTRAINT fk_sp_carName FOREIGN KEY (carName) REFERENCES CAR(carName) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert data for Type of Events
INSERT INTO typeevent(id, typeEvent, typeDescr, active) VALUES
('1', 'Ownership', 'Taken ownership of the car.', 'Yes'),
('2', 'Service (Standard)', 'Standard service, change of filters, spark plugs and oil.', 'Yes'),
('3', 'Service (Advanced)', 'Advanced service.', 'Yes'),
('4', 'Service (Other)', 'Other, specified in comments.', 'Yes'),
('5', 'Wheel Alignment', 'Alignment of the wheels.', 'Yes'),
('6', 'Rotate Tyres', 'When the tyres have been rotated.', 'Yes'),
('7', 'Flat Tyre', 'When the car has had a flat tyre and had to be repaired or replaced.', 'Yes'),
('8', 'Wash Car', 'Car had a wash.', 'Yes'),
('9', 'Slow Puncture - Discovered', 'Discovered a slow puncture.', 'Yes'),
('10', 'Slow Puncture - Fixed', 'Had the slow puncture fixed.', 'Yes');




