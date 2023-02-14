package org.sid;

import java.io.IOException;
import java.text.ParseException;

import org.sid.service.IGeneratorUpdateRequest;
import org.sid.service.IMolineteRecensementDifferenceAvecFredi;
import org.sid.service.impl.GeneratorUpdateRequestImpl;
import org.sid.service.impl.MolineteRecensementDifferenceAvecFrediImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*-----Recensement de la différence avec fichier de CR-------*/
/*		IMolineteRecensementDifferenceAvecFredi recensementDifferenceAvecFredi = new MolineteRecensementDifferenceAvecFrediImpl();
		try {
			recensementDifferenceAvecFredi.process();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
		/*--------Génération des requetes d'Update pour les demandes-------*/
		IGeneratorUpdateRequest generatorUpdateRequest = new GeneratorUpdateRequestImpl();
		generatorUpdateRequest.process();
	
	}

}
