package utils;

import DBlayer.DBController;
import DBlayer.IDBController;
import DBlayer.DBpool;

public class ClassFactory {
	static public DBpool injectDBPool() {
		return new DBpool();
	}
	
	static public IDBController injectDBController() {
		return new DBController();
	}
}

