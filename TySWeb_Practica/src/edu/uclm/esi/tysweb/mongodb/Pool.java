package edu.uclm.esi.tysweb.mongodb;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;


public class Pool {
	private ConcurrentLinkedQueue<MongoClient> libres;
	private ConcurrentLinkedQueue<MongoClient> usadas;

	private String serverUri;

	private MongoClient client;

	public Pool(int numeroDeConexiones) {
		try {
			String url = "localhost";
			String db = "OCA";

			this.libres = new ConcurrentLinkedQueue<>();
			this.usadas = new ConcurrentLinkedQueue<>();
			for (int i = 0; i < numeroDeConexiones; i++) {
				MongoClient bd;
				// MongoCredential credenciales = MongoCredential.createCredential("root", db,
				// "root".toCharArray());
				String user = "";
				String pwd = "";
				ServerAddress address = new ServerAddress(url);
				// List<MongoCredential> lista = Arrays.asList(credenciales);
				// serverUri="mongodb://" + user + ":" + pwd + "@localhost:27017/games";
				serverUri = "mongodb://userGames:passwordGames@cluster0-shard-00-00-ofhzj.mongodb.net:27017,cluster0-shard-00-01-ofhzj.mongodb.net:27017,cluster0-shard-00-02-ofhzj.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true";
				MongoClientURI clientUri = new MongoClientURI(this.serverUri);
				bd = new MongoClient(clientUri);
				// bd = new MongoClient(address, lista);
				this.libres.add(bd);
			}
		} catch (Exception e) {
			System.exit(-1);
		}
	}

	public MongoClient getBD() throws Exception {
		if (this.libres.size() == 0)
			throw new Exception("No hay conexiones libres");
		MongoClient bd = this.libres.poll();
		this.usadas.offer(bd);
		return bd;
	}

	public void close(MongoClient bd) {
		this.usadas.remove(bd);
		this.libres.offer((MongoClient) bd);
	}
}
