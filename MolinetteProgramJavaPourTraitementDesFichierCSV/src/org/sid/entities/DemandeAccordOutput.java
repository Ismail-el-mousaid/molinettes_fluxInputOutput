package org.sid.entities;

import java.util.Date;

public class DemandeAccordOutput {
	
	private String pdl;
	private String concatCorrelationObjet;
	private String typeDemande;
	private String statutDemande;
	private String dateEnregistrement;
	private String dateDerniereMAJ;
	private String etape;
	
	public String getPdl() {
		return pdl;
	}
	
	public void setPdl(String pdl) {
		this.pdl = pdl;
	}
	
	public String getConcatCorrelationObjet() {
		return concatCorrelationObjet;
	}

	public void setConcatCorrelationObjet(String correlationObjetGaz) {
		this.concatCorrelationObjet = correlationObjetGaz;
	}
	
	public String getTypeDemande() {
		return typeDemande;
	}
	
	public void setTypeDemande(String typeDemande) {
		this.typeDemande = typeDemande;
	}
	
	public String getStatutDemande() {
		return statutDemande;
	}
	
	public void setStatutDemande(String statutDemande) {
		this.statutDemande = statutDemande;
	}

	public String getDateEnregistrement() {
		return dateEnregistrement;
	}
	
	public void setDateEnregistrement(String dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}
	
	public String getDateDernierMAJ() {
		return dateDerniereMAJ;
	}
	
	public void setDateDerniereMAJ(String dateDerniereMAJ) {
		this.dateDerniereMAJ = dateDerniereMAJ;
	}
	
	public String getEtape() {
		return etape;
	}
	
	public void setEtape(String etape) {
		this.etape = etape;
	}
	
	
	public String toString() {
		return "DemandeAccordOutput : "+this.concatCorrelationObjet+", "
				+this.typeDemande+", "+this.statutDemande+", "+this.dateEnregistrement;
	}
}
