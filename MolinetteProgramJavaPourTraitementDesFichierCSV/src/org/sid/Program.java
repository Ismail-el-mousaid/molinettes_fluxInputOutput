package org.sid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*-------Génération des requetes d'Insert pour créer des demandes Elec--------*/

public class Program {
	
	public static final String delimiter = ",";
	
	public static void read(String csvFile) {
	    try {
	      FileWriter myWriter = new FileWriter("C:\\Users\\ielmousa\\Desktop\\RattrapageCasActifsDontRenouvellementEnEchec\\Requetes (Resultats de Molinette)\\Rattrapage-Part3.txt");
	      File file = new File(csvFile);
	      FileReader fr = new FileReader(file);
	      BufferedReader br = new BufferedReader(fr);
	      String line = " ";
	      String[] tempArr;
	      List<Integer> list = Arrays.asList(2, 4, 5, 13, 14, 15, 16, 17, 19, 20, 23, 24, 25, 26);
	      List<Integer> list2 = Arrays.asList(1, 3, 6, 7);
	      while ((line = br.readLine()) != null) {
	        tempArr = line.split(delimiter);
	        
	      //myWriter.write("\n" + "INSERT INTO DEMANDE_ACCORD (ID, OBJET_ID, PDL, NUM_BUSINESS_PARTNER, NUM_COMPTE_CONTRAT, ENERGIE, TYPE_ACCORD, STATUT_DEMANDE, DATE_ENREGISTREMENT, CANAL, DATE_DERNIERE_MAJ, CODE_ERREUR_FONC, MESSAGE_ERREUR_FONC)  values (");
		  // System.out.print("INSERT INTO DEMANDE_ACCORD (ID, OBJET_ID, PDL, NUM_BUSINESS_PARTNER, NUM_COMPTE_CONTRAT, ENERGIE, TYPE_ACCORD, STATUT_DEMANDE, DATE_ENREGISTREMENT, CANAL, DATE_DERNIERE_MAJ, CODE_ERREUR_FONC, MESSAGE_ERREUR_FONC)  values (");
	        
	        
//INSERT INTO DEMANDE_ACCORD values (2893508, null, "E", "1163970000000", "a2785847-d1ac-4069-8852-410bcad36ab0", 
//"26c67579-ae82-11eb-8164-000d3a2927b0", "a2785847-d1ac-4069-8852-410bcad36ab026c67579-ae82-11eb-8164-000d3a2927b0", 
//"RENOUVELLEMENT", "RENOUVELLEMENT_EN_COURS", "IDXE", "CEL", "508232830", "305778555", 
//NOW(), NOW(), "FREDI_MS", "KO",  null, null, 0, null, null, 10, "erreur fonctionnel", null, "M_ACCORD_ET_PURGE_KO", null, null, null);	        
	        
	        myWriter.write("\n" + "INSERT INTO DEMANDE_ACCORD (ID, CANAL, ENERGIE, TYPE_DEMANDE, STATUT_DEMANDE, TYPE_ACCORD, DEMANDEUR, DATE_ENREGISTREMENT, DATE_DERNIERE_MAJ, ETAPE, STATUT_ETAPE, NBR_REJEU_TECH, NBR_REJEU_FONC, MESSAGE_ERREUR_FONC, TEMPLATE_MAIL_ERREUR, OBJET_ID, CORRELATION_ID, CONCAT_CORRELATION_OBJET, PDL, NUM_BUSINESS_PARTNER, NUM_COMPTE_CONTRAT) values (null, null, 'E', 'RENOUVELLEMENT', 'RENOUVELLEMENT_EN_COURS', 'IDXE', 'CEL', NOW(), NOW(), 'FREDI_MS', 'KO', 3, 12, 'erreur fonctionnel', 'M_ACCORD_ET_PURGE_KO',   ");
		    System.out.print("INSERT INTO DEMANDE_ACCORD (ID, OBJET_ID, PDL, NUM_BUSINESS_PARTNER, NUM_COMPTE_CONTRAT, ENERGIE, TYPE_ACCORD, STATUT_DEMANDE, DATE_ENREGISTREMENT, CANAL, DATE_DERNIERE_MAJ, CODE_ERREUR_FONC, MESSAGE_ERREUR_FONC)  values (");
	        
	        for(int i = 0; i<tempArr.length; i++) {
	        /*	if (i == 2 | i == 4 | i == 5 | i == 13 | i == 14 | i == 15 | i == 16 | i == 17 | i == 19 | i == 20 | i == 23 | i == 24 | i == 25 | i == 26) {
	        		continue;
	        	}   */
	        /*	if (i==3) {
	        		int var = new BigDecimal("113E+12").intValue();
	        		System.out.print(var);
			        myWriter.write(var);
	        	}  */
	      /*  	if (list.contains(i)) {
	        		continue;
	        	}
	        	else if (i==tempArr.length-1 | i == 22) {
	        		System.out.print(tempArr[i] + "); ");
			        myWriter.write(tempArr[i] + "); ");
	        	} else {
	        		System.out.print(tempArr[i] + ", ");
			        myWriter.write(tempArr[i] + ", ");
	        	}  */
	        	if (list2.contains(i)) {
	        	/*	if (i == 7) {
	        			System.out.print(tempArr[i] + " ");
				        myWriter.write(tempArr[i] + " ");
	        		}  */
	        		System.out.print(tempArr[i] + ", ");
			        myWriter.write(tempArr[i] + ", ");
			        if (i == 1) {
			        	String correlation_id = UUID.randomUUID().toString();
			        	System.out.print("'"+correlation_id + "', ");
				        myWriter.write("'"+correlation_id + "', ");
				        String objet_id = tempArr[1].replace("'", "");
				        System.out.print(correlation_id.concat(objet_id) + ", ");
				        myWriter.write("'"+correlation_id.concat(objet_id)+ "', ");
			        }
	        	}
	        	
	        	if (i==tempArr.length-1) {
	        		System.out.print("); ");
			        myWriter.write("); ");
	        	}
	        }
	       /* for (String tempStr: tempArr) {
	          System.out.print(tempStr + ", ");
	          myWriter.write(tempStr + ", ");
	        }  */
	        System.out.println();
	      }
	      br.close();
	    }
	    catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
	  
	  public static void main(String[] args) {
	    //csv file to read
		//  String csvFile = "C:\\Users\\ielmousa\\Desktop\\RattrapageCasActifsDontRenouvellementEnEchec\\Les extracts\\Rattrapage-Part-3.csv";
		//  Program.read(csvFile);
		  // Generate UUID
		  String correlation_id = UUID.randomUUID().toString();
		  System.out.println("correlation_id : " +correlation_id);
		  String objet_id = "26c67579-ae82-11eb-8164-000d3a2927b0";
		  String concat_correlation_objet = correlation_id.concat(objet_id);
		  System.out.println("concat_correlation_objet : "+concat_correlation_objet);
		 
	  }
			
}
 		