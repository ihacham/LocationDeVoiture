package ma.ac.ensaagadir.utils;

import ma.ac.ensaagadir.models.Client;

public class ApplicationSession {
	
	private Client selectedClient;
	

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public ApplicationSession() {
	}
	
	
}
