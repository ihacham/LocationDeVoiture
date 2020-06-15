package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class ClientRepository {

	private Connection connection;


	public ClientRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search client by name
	public ArrayList<Client> getClientByName(String name) {
		ArrayList<Client> clients = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from client where nom like ?");
			ps.setString(1,"%"+ name+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// codeClient nom prenom adresse imageScanneeDPermis tel age
				Client client = new Client();
				client.setCodeClient(rs.getLong(1));
				client.setNom(rs.getString(2));
				client.setPrenom(rs.getString(3));
				client.setAdresse(rs.getString(4));
				client.setImageScanneeDPermis(rs.getString(5));
				client.setTel(rs.getString(6));
				client.setAge(rs.getInt(7));
				clients.add(client);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clients;
	}

	// retrieve all clients
	public ArrayList<Client> getAllClients() {
		ArrayList<Client> clients = new ArrayList<>();
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from client");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// codeClient nom prenom adresse imageScanneeDPermis tel age
				Client client = new Client();
				client.setCodeClient(rs.getLong(1));
				client.setNom(rs.getString(2));
				client.setPrenom(rs.getString(3));
				client.setAdresse(rs.getString(4));
				client.setImageScanneeDPermis(rs.getString(5));
				client.setTel(rs.getString(6));
				client.setAge(rs.getInt(7));
				clients.add(client);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clients;
	}

	// add client
	public Client addClient(Client nvClient) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(
				"insert into Client(nom,	prenom,	adresse,imageScanneeDPermis	,tel,age) values (?,?,?,?,?,?)");
		ps.setString(1, nvClient.getNom());
		ps.setString(2, nvClient.getPrenom());
		ps.setString(3, nvClient.getAdresse());
		ps.setString(4, nvClient.getImageScanneeDPermis());
		ps.setString(5, nvClient.getTel());
		ps.setInt(6, nvClient.getAge());

		int rs = ps.executeUpdate();

		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			nvClient.setCodeClient(result.getLong(1));
		}
		
		return nvClient;
	}

	// edit client
	public void editClient(Client nvClient) throws SQLException {
		
		
		PreparedStatement ps = connection.prepareStatement(
				"update Client set nom=?, prenom=?,	adresse=?,imageScanneeDPermis=?	,tel=?,age=? where codeclient=?");
		ps.setString(1, nvClient.getNom());
		ps.setString(2, nvClient.getPrenom());
		ps.setString(3, nvClient.getAdresse());
		ps.setString(4, nvClient.getImageScanneeDPermis());
		ps.setString(5, nvClient.getTel());
		ps.setInt(6, nvClient.getAge());
		ps.setLong(7, nvClient.getCodeClient());

		int rs = ps.executeUpdate();

	}

	// delete client
	public void deleteClient(Client nvClient) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Client where codeclient=?");
		ps.setLong(1, nvClient.getCodeClient());

		int rs = ps.executeUpdate();

	}
}
