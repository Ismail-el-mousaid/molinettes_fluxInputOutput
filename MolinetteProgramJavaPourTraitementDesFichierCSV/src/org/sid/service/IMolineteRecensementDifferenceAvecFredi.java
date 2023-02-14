package org.sid.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.sid.entities.DemandeAccordGaz;

public interface IMolineteRecensementDifferenceAvecFredi {
	
	// INPUT File
	static final String INPUT_FILE_FREDI_LOCATION = "C:\\Users\\ielmousa\\Desktop\\Train\\kanban\\RattrapageActivationDesDemandesGaz\\RecensementDeLaDifferenceAvecCrFredi\\CR_D_GRDF_TJDC_20230201_151057_FDT.csv";
	static final String INPUT_FILE_DEMANDEACCORDGAZ_LOCATION = "C:\\Users\\ielmousa\\Desktop\\Train\\kanban\\RattrapageActivationDesDemandesGaz\\RecensementDeLaDifferenceAvecCrFredi\\DEMANDE_ACCORD_GAZ_202302061625.csv";
	// OUTPUT File
	static final String OUTPUT_FILE_LOCATION = "C:\\Users\\ielmousa\\Desktop\\Train\\kanban\\RattrapageActivationDesDemandesGaz\\RecensementDeLaDifferenceAvecCrFredi\\Output\\output.csv";
	static final String OUTPUT_FILE_LOCATION_INNEXISTANT = "C:\\Users\\ielmousa\\Desktop\\Train\\kanban\\RattrapageActivationDesDemandesGaz\\RecensementDeLaDifferenceAvecCrFredi\\Output\\outputInnexistant.csv";
	// Delimiter
	static final String SPLITOR_CSV = ";";
	static final String SPLITOR_CSV_DEMANDEACCORD = ",";
	// Requete SQL
	static final String SQL_REQUEST_PREFIX = "SELECT * FROM REF.R_ACCORD_CLIENT rac ";			
	static final String SQL_REQUEST_PREFIX2 = " WHERE  GUID_ACCORD_CLIENT IN ";			
	static final String SQL_REQUEST_SUFFIX = ";\n";
	// Size
	static final int SPLIT_SIZE = 500;
	
	
	List<String> readCRFredi(String csvFile);
	List<DemandeAccordGaz> readDemandeAccordGaz(String inputeFile) throws ParseException;
	void process() throws IOException, ParseException;

}
