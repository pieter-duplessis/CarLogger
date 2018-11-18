/*
================================
Author:			Pieter du Plessis
Create Date:	2018/11/18
Last Update:	2018/11/18
Language:		SQLite

Description:
This file is for the creation of the database, tables and preset data.

Please note:
The name of the database file must be 'CarLogger.db'

================================
*/

-- Creating the table to store to details about the cars.
CREATE TABLE `CAR` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`carName`	TEXT,
	`manufacturer`	TEXT,
	`model`	TEXT,
	`yearMade`	INTEGER,
	`regNo`	TEXT,
	`vinNo`	TEXT,
	`active`	TEXT
);

-- Create the table to store the details about the type of events.
CREATE TABLE `TYPEEVENT` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`typeEvent`	TEXT,
	`typeDescr`	TEXT,
	`active`	TEXT
);

-- Create the table to store the details about what has been done to the car.
CREATE TABLE `LOG` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`carName`	TEXT,
	`date`	TEXT,
	`kmReading`	INTEGER,
	`supp`	TEXT,
	`docNo`	TEXT,
	`typeEvent`	TEXT,
	`comment`	TEXT
);

-- Create the table to store the details about the problems found with the car.
CREATE TABLE `CARPROBLEM` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`carName`	TEXT,
	`date`	TEXT,
	`kmReading`	INTEGER,
	`fixed`	TEXT,
	`probName`	TEXT,
	`comment`	TEXT
);

-- Create the table to store the details about the spare parts that have been bought but not used yet.
CREATE TABLE `SPAREPARTS` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`carName`	INTEGER,
	`date`	TEXT,
	`supp`	TEXT,
	`docNo`	TEXT,
	`part`	TEXT,
	`used`	TEXT,
	`comment`	TEXT
);

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




