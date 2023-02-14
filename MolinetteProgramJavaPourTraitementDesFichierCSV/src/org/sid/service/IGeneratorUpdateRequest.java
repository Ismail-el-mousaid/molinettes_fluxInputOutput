package org.sid.service;

import java.util.HashSet;

public interface IGeneratorUpdateRequest {
	
	// Delimiter
	static final String SPLITOR_CSV = ",";
	// Messages retours
	static final String SUCCESS_MESSAGE = "Successfully wrote to the file.";
	static final String ERROR_MESSAGE = "An error occurred.";

	public HashSet<String> readCRFredi(String csvFile);
	public String process();
	public void writeInFile(String pdls, int numFile);
	
}
