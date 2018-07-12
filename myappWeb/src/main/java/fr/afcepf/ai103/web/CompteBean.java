package fr.afcepf.ai103.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import fr.afcepf.ai103.data.Compte;
import fr.afcepf.ai103.service.ServiceCompte;

@ManagedBean
@SessionScoped
public class CompteBean {
	
	private Long numClient = null;
	
	private ServiceCompte serviceCompte = new ServiceCompte();
	
	//avec get/set
	private Long numCptDeb = null;  //à selectionner dans liste déroulante dans virement.xhtml
	private Long numCptCred = null; //à selectionner dans liste déroulante dans virement.xhtml
	private Double montant = null; //à saisir dans virement.xhtml
	
	private List<Compte> comptes ; //à afficher sous forme de tableau (h:dataTable)
	
	//constructeur par défaut:
	public CompteBean() {
	}
	
	//méthode appelée après que numClient soit automatiquement mis à jour par JSF
	public void initComptes(ComponentSystemEvent event){
		comptes = serviceCompte.comptesDuClient(numClient);
	}
	
	public String effectuerVirement() {
		String suite = null;
		//effectuer le virement
		serviceCompte.transferer(montant, numCptDeb, numCptCred);
		//recharger en mémoire les nouveaux soldes qui ont évolués et qui seront ré-afficher
		comptes = serviceCompte.comptesDuClient(numClient);
		//demander à naviguer vers comptes.xhtml pour réafficher les nouveaux soldes:
		suite = "comptes" ; //.xhtml 
		return suite;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public Long getNumClient() {
		return numClient;
	}

	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}

	public Long getNumCptDeb() {
		return numCptDeb;
	}

	public void setNumCptDeb(Long numCptDeb) {
		this.numCptDeb = numCptDeb;
	}

	public Long getNumCptCred() {
		return numCptCred;
	}

	public void setNumCptCred(Long numCptCred) {
		this.numCptCred = numCptCred;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	

}
