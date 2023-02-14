package org.sid.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sid.entities.DemandeAccordGaz;
import org.sid.entities.DemandeAccordOutput;
import org.sid.service.IMolineteRecensementDifferenceAvecFredi;

public class MolineteRecensementDifferenceAvecFrediImpl implements IMolineteRecensementDifferenceAvecFredi{
	
	
	public List<String> readCRFredi(String csvFile) {
		
		ArrayList<String> listConcatCorrelationObjet = new ArrayList<>();
		  
		try {
		    File file = new File(csvFile);
		    FileReader fr = new FileReader(file);
		    BufferedReader br = new BufferedReader(fr);
		    String line = " ";
		    String[] tempArr;
		      
		    while ((line = br.readLine()) != null) {
		    	// br.skip(1);
		    	tempArr = line.split(SPLITOR_CSV);
		        listConcatCorrelationObjet.add(tempArr[3]);
		    //    System.out.println("CONCAT_CORRELATION_OBJET : "+tempArr[3]);
		        
		    } 
		    br.close();
		  } catch(IOException ioe) {
		    	ioe.printStackTrace();
		    }
		 return listConcatCorrelationObjet;    
	  }
	  
	  
	  public List<DemandeAccordGaz> readDemandeAccordGaz(String inputeFile) throws ParseException {
		  List<DemandeAccordGaz> listDemandeAccordGaz = new ArrayList<>();
		  Map<Integer, String> maps = new HashMap<>();
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		  
		  try {
			BufferedReader br = new BufferedReader(new FileReader(new File(inputeFile)));
			String line = " ";
			String[] tabDemandeAccord = null;
			int j = 1;
			while ((line = br.readLine()) != null) {
				DemandeAccordGaz demandeAccordGaz = new DemandeAccordGaz();
				tabDemandeAccord = line.split(SPLITOR_CSV_DEMANDEACCORD);
			//	System.out.println("key: " +tabDemandeAccord[0]);
				maps.put(j, br.readLine());
				j++;
			//	System.out.println(tabDemandeAccord[1]+" "+tabDemandeAccord[3]);
				
				demandeAccordGaz.setIdGaz(tabDemandeAccord[0].substring(1, tabDemandeAccord[0].length()));  //Enlever le 1er caractere ( " ) 
				demandeAccordGaz.setCanalGaz(tabDemandeAccord[1]);
				demandeAccordGaz.setApplicationSourceGaz(tabDemandeAccord[2]);
				demandeAccordGaz.setEnergieGaz(tabDemandeAccord[3]);
				demandeAccordGaz.setPdlGaz(tabDemandeAccord[4].replaceAll("[^a-zA-Z0-9]+", ""));
				demandeAccordGaz.setCorrelationIdGaz(tabDemandeAccord[5]);
				demandeAccordGaz.setObjetIdGaz(tabDemandeAccord[6]);
				demandeAccordGaz.setConcatCorrelationObjetGaz(tabDemandeAccord[7].replaceAll("[^a-zA-Z0-9]+", ""));  //Pour enlever les ", et garder les lettres et les charactères				
				demandeAccordGaz.setTypeDemandeGaz(tabDemandeAccord[8]);
				demandeAccordGaz.setStatutDemandeGaz(tabDemandeAccord[9]);
				demandeAccordGaz.setDateEnregistrementGaz(new SimpleDateFormat("yyyy-MM-dd").format(formatter.parse(tabDemandeAccord[14].replaceAll("[^a-zA-Z0-9]+", ""))));
				demandeAccordGaz.setDateDerniereMAJGaz(new SimpleDateFormat("yyyy-MM-dd").format(formatter.parse(tabDemandeAccord[15].replaceAll("[^a-zA-Z0-9]+", ""))));
				demandeAccordGaz.setEtapeGaz(tabDemandeAccord[16]);
				
				listDemandeAccordGaz.add(demandeAccordGaz);
			}
			
		/*	listDemandeAccordGaz.forEach(demandeAccordGaz -> {
				System.out.println(demandeAccordGaz.getConcatCorrelationObjetGaz());
			});  */
			
		/*	for (Map.Entry<Integer, String> map : maps.entrySet()) {
	            System.out.println(map.getKey() + " = " + map.getValue());
	        }    */
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return listDemandeAccordGaz;
		  
	  }
	  
	  
	  static String id = "";
	  
	  public void process() throws IOException, ParseException {
		  List<DemandeAccordOutput> output = new ArrayList<>();
		  ArrayList<String> listConcatCorrelationObjet = (ArrayList<String>) readCRFredi(INPUT_FILE_FREDI_LOCATION);
		  ArrayList<DemandeAccordGaz> listDemandeAccordGazs = (ArrayList<DemandeAccordGaz>) readDemandeAccordGaz(INPUT_FILE_DEMANDEACCORDGAZ_LOCATION);
		  
		  FileWriter myWriter = new FileWriter(OUTPUT_FILE_LOCATION);
		  FileWriter myWriterInnexistant = new FileWriter(OUTPUT_FILE_LOCATION_INNEXISTANT);
		  
		  // Un Stream parallèle va en interne paralléliser l'exécution du pipeline d'opérations en répartissant les données sur plusieurs threads pour utiliser les coeurs présents sur la machine.
		  // map() : transforme chaque élément de type Personne pour renvoyer uniquement le nom
		  // L'opération distinct() permet de supprimer les doublons contenus dans un Stream. Les éléments contenus dans le Stream retournés sont donc uniques. La méthode distinct() n'attend aucun paramètre et renvoie un Steam avec les éléments dont les doublons ont été retirés.
		  // listDemandeAccordGazs.parallelStream().map(DemandeAccordGaz::getConcatCorrelationObjetGaz).distinct()
		  listConcatCorrelationObjet.forEach(concat -> {
			  listDemandeAccordGazs.forEach(demande -> {
				 if (demande.getConcatCorrelationObjetGaz().equals(concat)) {
					 System.out.println(demande.getConcatCorrelationObjetGaz()+" "+concat+" : "+demande.getConcatCorrelationObjetGaz().equals(concat));
					 DemandeAccordOutput accordOutput = new DemandeAccordOutput();
					 accordOutput.setPdl(demande.getPdlGaz());
					 accordOutput.setConcatCorrelationObjet(demande.getConcatCorrelationObjetGaz());
					 accordOutput.setTypeDemande(demande.getTypeDemandeGaz());
					 accordOutput.setStatutDemande(demande.getStatutDemandeGaz());
					 
					 accordOutput.setDateEnregistrement(demande.getDateEnregistrementGaz());
					 accordOutput.setDateDerniereMAJ(demande.getDateDerniereMAJGaz());
					 accordOutput.setEtape(demande.getEtapeGaz());
					 
					 output.add(accordOutput);
					 try {
						myWriter.write("\n"+accordOutput.getPdl()+", "+accordOutput.getConcatCorrelationObjet()+", "+accordOutput.getTypeDemande()+", "+accordOutput.getStatutDemande()+", "+accordOutput.getDateEnregistrement()
										+", "+accordOutput.getDateDernierMAJ()+", "+accordOutput.getEtape() );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 id = concat;
					
				 }
			  });
			  if (!concat.equals(id)) {
				  System.out.println(concat+" n'existe pas dans notre table");
					 try {
						myWriterInnexistant.write("\n" +concat);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 id = "";
			  }
		  });
		  output.forEach(o -> {
			  System.out.println(o.toString());
		  });
		  myWriter.close();
		  myWriterInnexistant.close();
	  }
	  
	 
			
}
 		