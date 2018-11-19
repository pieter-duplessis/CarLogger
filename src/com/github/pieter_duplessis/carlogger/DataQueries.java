package com.github.pieter_duplessis.carlogger;

/*
 * This file contains all the queries for SQLite and MySQL.
 * 
 */

public class DataQueries {
	// Log Queries
	static String logQuery() {
		return "SELECT id AS 'ID', carName AS 'Car Name', date AS 'Date', kmReading AS 'Odometer Reading', supp AS 'Supplier/Service Provider', docNo AS 'Document', typeEvent AS 'Event' FROM LOG ORDER BY date DESC " + Data.queryLimit();
	}
	
	static String logAdd(String carName, String date, String km, String supp, String doc, String event, String comment) {
		return "INSERT INTO LOG(carName, date, kmReading, supp, docNo, typeEvent, comment) VALUES ('"+carName+"', '"+date+"', '"+km+"', '"+supp+"', '"+doc+"', '"+event+"', '"+comment+"') ";
	}
	
	static String logEditQuery(String id) {
		return "SELECT carName, date, kmReading, supp, docNo, typeEvent, comment FROM LOG WHERE id = '"+id+"';";
	}
	
	static String logEditUpdate(String id, String carName, String date, String km, String supp, String doc, String event, String comment) {
		return "UPDATE LOG SET carName = '"+carName+"', date = '"+date+"', kmReading = '"+km+"', supp = '"+supp+"', docNo = '"+doc+"', typeEvent = '"+event+"', comment = '"+comment+"' WHERE id = '"+id+"';";
	}
	
	
	// Car Problem Queries
	static String problemQuery() {
		return "SELECT id AS 'ID', carName AS 'Car', date AS 'Date', kmReading AS 'Odometer', fixed AS 'Fixed', probName AS 'Problem Name' FROM CARPROBLEM ORDER BY fixed DESC, date DESC " + Data.queryLimit();
	}
	
	static String problemAdd(String carName, String date, String km, String probName, String comment) {
		return "INSERT INTO CARPROBLEM(carName, date, kmReading, fixed, probName, comment) VALUES ('"+carName+"', '"+date+"', '"+km+"', 'Not Fixed', '"+probName+"', '"+comment+"') ";
	}
	
	static String problemEditQuery(String id) {
		return "SELECT carName, date, kmReading, fixed, probName, comment FROM CARPROBLEM WHERE id = '"+id+"';";
	}
	
	static String problemEditUpdate(String id, String carName, String date, String km, String fixed, String probName, String comment) {
		return "UPDATE CARPROBLEM SET carName = '"+carName+"', date = '"+date+"', kmReading = '"+km+"', fixed = '"+fixed+"', probName = '"+probName+"', comment = '"+comment+"' WHERE id = '"+id+"';";
	}
	
	
	// Spare Parts Queries
	static String partQuery() {
		return "SELECT id AS 'ID', carName AS 'Car', date AS 'Date', supp AS 'Supplier/Service Provider',  docNo AS 'Document No.', part AS 'Part', used AS 'Used' FROM SPAREPARTS ORDER BY used, date DESC " + Data.queryLimit();
	}
	
	static String partAdd(String carName, String date, String supp, String doc, String part, String used, String comment) {
		return "INSERT INTO SPAREPARTS(carName, date, supp, docNo, part, used, comment) VALUES ('"+carName+"', '"+date+"', '"+supp+"', '"+doc+"', '"+part+"', '"+used+"', '"+comment+"') ";
	}
	
	static String partEditQuery(String id) {
		return "SELECT carName, date, supp, docNo, part, used, comment FROM SPAREPARTS WHERE id = '"+id+"';";
	}
	
	static String partEditUpdate(String id, String carName, String date, String supp, String doc, String part, String used, String comment) {
		return "UPDATE SPAREPARTS SET carName = '"+carName+"', date = '"+date+"', supp = '"+supp+"', docNo = '"+doc+"', part = '"+part+"', used = '"+used+"', comment = '"+comment+"' WHERE id = '"+id+"';";
	}
	
	
	// Car Queries
	static String carQuery() {
		return "SELECT id AS 'ID', carName AS 'Car Name', manufacturer AS 'Manufacturer', model AS 'Model', yearMade AS 'Year', regNo AS 'Reg. No', vinNo AS 'VIN Number' FROM CAR WHERE active = 'Yes' ORDER BY carName " + Data.queryLimit();
	}
	
	static String carAdd(String carName, String man, String model, String year, String reg, String vin) {
		return "INSERT INTO CAR(carName, manufacturer, model, yearMade, regNo, vinNo, active) VALUES ('"+carName+"', '"+man+"', '"+model+"', '"+year+"', '"+reg+"', '"+vin+"', 'Yes');";
	}
	
	static String carEditQuery(String id) {
		return "SELECT carName, manufacturer, model, yearMade, regNo, vinNo FROM CAR WHERE id = '"+id+"';";
	}
	
	static String carEditUpdate(String id, String carName, String man, String model, String year, String reg, String vin) {
		return "UPDATE CAR SET carName = '"+carName+"', manufacturer = '"+man+"', model = '"+model+"', yearMade = '"+year+"', regNo = '"+reg+"', vinNo = '"+vin+"' WHERE id = '"+id+"';";
	}
	
	static String carRemove(String carName) {
		return "UPDATE CAR SET active = 'No' WHERE carName = '"+carName+"';";
	}
	
	static String carCombo() {
		return "SELECT carName FROM CAR WHERE active = 'Yes';";
	}
	
	static String carNameAlreadyExistNew() {
		return "SELECT carName FROM CAR WHERE active = 'Yes';";
	}
	
	static String carNameAlreadyExistUpdate(String id) {
		return "SELECT carName FROM CAR WHERE id != '"+id+"' AND active = 'Yes';";
	}
	
	
	// Event Type Queries
	static String eventQuery() {
		return "SELECT id AS 'ID', typeEvent AS 'Event Type', typeDescr AS 'Description' FROM TYPEEVENT WHERE active = 'Yes' ORDER BY typeEvent " + Data.queryLimit();
	}
	
	static String eventAdd(String event, String descr) {
		return "INSERT INTO TYPEEVENT(typeEvent, typeDescr, active) VALUES ('"+event+"', '"+descr+"', 'Yes');";
	}
	
	static String eventEditQuery(String id) {
		return "SELECT typeEvent, typeDescr FROM TYPEEVENT WHERE id = '"+id+"';";
	}
	
	static String eventEditUpdate(String id, String event, String descr) {
		return "UPDATE TYPEEVENT SET typeEvent = '"+event+"', typeDescr = '"+descr+"' WHERE id = '"+id+"';";
	}
	
	static String eventRemove(String event) {
		return "UPDATE TYPEEVENT SET active = 'No' WHERE typeEvent = '"+event+"';";
	}
	
	static String eventCombo() {
		return "SELECT typeEvent FROM TYPEEVENT WHERE active = 'Yes';";
	}
	
	static String eventAlreadyExistNew() {
		return "SELECT typeEvent FROM TYPEEVENT WHERE active = 'Yes';";
	}
	
	static String eventAlreadyExistUpdate(String id) {
		return "SELECT typeEvent FROM TYPEEVENT WHERE id != '"+id+"' AND active = 'Yes';";
	}
	
}
