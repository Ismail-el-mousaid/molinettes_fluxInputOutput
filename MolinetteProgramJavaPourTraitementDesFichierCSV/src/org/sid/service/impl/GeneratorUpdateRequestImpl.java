package org.sid.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.sid.service.IGeneratorUpdateRequest;

public class GeneratorUpdateRequestImpl implements IGeneratorUpdateRequest {

	// INPUT File
	private static final String INPUT_FILE_DEMANDES_A_RATTRAPER_LOCATION = "C:\\Users\\ielmousa\\Desktop\\Train\\kanban\\RattrapageActivationDesDemandesGaz\\RecensementDeLaDifferenceAvecCrFredi\\Output\\Demandes_A_Rattraper.csv";
	// OUTPUT File
	private static final String OUTPUT_FILE_LOCATION = "C:\\Users\\ielmousa\\Desktop\\Train\\kanban\\RattrapageActivationDesDemandesGaz\\Output\\requetes";
	private static final String OUTPUT_FILE_EXTENTION = ".txt";
	// Requete SQL
	private static final String SQL_REQUEST_PREFIX = "UPDATE DEMANDE_ACCORD_GAZ SET ETAPE = 'FREDI_MS', STATUT_ETAPE = 'OK' ";			
	private static final String SQL_REQUEST_PREFIX2 = "WHERE PDL IN (";			
	private static final String SQL_REQUEST_SUFFIX = ");\n";
	// Size
	private static final int SPLIT_SIZE = 5000;
	
	
	
	public HashSet<String> readCRFredi(String csvFile) {
		
		HashSet<String> listPDLs = new HashSet<String>();
		
		String line = " ";
	    String[] tempArr;
	    String pdl;
	    String pdlFinal;
		  
		try {
		    File file = new File(csvFile);
		    FileReader fr = new FileReader(file);
		    BufferedReader br = new BufferedReader(fr);
		    
		      
		    while ((line = br.readLine()) != null) {
		    	br.skip(1);
		    	tempArr = line.split(SPLITOR_CSV);
		        pdl = tempArr[0].replaceAll("[^a-zA-Z0-9]+", "");
		        if (pdl.length()!=14) {
		        	pdlFinal = "0".concat(pdl);
		        } else {
		        	pdlFinal = pdl;
		        }
		        
		        listPDLs.add(pdlFinal);
		        
		    } 
		    
		    br.close();
		  } catch(IOException ioe) {
			  	System.out.println(ERROR_MESSAGE);
		    	ioe.printStackTrace();
		    }
		 return listPDLs;    
	  }
	
	
	int counter = 0;
	int totalDemades = 0;
	String pdlList = "";
	int i = 0;
	
	public String process() {
		HashSet<String> listPDL;
		
		try {
			listPDL = readCRFredi(INPUT_FILE_DEMANDES_A_RATTRAPER_LOCATION);
			listPDL.forEach(pdl -> {
				try {
					if (counter == SPLIT_SIZE || listPDL.size()-1 == totalDemades) {
					//	return; // équivaut à break dans for (pour arreter la boucle)
						System.out.println("nbr demandes pris en charge dans ce fichier : "+counter);
						writeInFile(pdlList, i);
						pdlList = "";
						counter = 0;
						i++;
					}
					
					pdlList += "'"+pdl+"', ";
					counter++;
					totalDemades++;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(ERROR_MESSAGE);
					e.printStackTrace();
				}
			});
			System.out.println("nbr totale de demandes à rattraper va devenir après l'élimination des doublons via HashSet : "+totalDemades);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return pdlList;
	}
	
	
	public void writeInFile(String pdls, int numFile) {
		FileWriter myWriter;
		try {
			File file = new File(OUTPUT_FILE_LOCATION+"_"+numFile+OUTPUT_FILE_EXTENTION);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
				myWriter = new FileWriter(file);
				myWriter.write(SQL_REQUEST_PREFIX);
				myWriter.write(SQL_REQUEST_PREFIX2);
				
				myWriter.write(pdls);
				
				myWriter.write(SQL_REQUEST_SUFFIX);
				myWriter.close();
				System.out.println(SUCCESS_MESSAGE+" \n -----------");
			} else {
				System.out.println("File already exists. \n -----------");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
}
