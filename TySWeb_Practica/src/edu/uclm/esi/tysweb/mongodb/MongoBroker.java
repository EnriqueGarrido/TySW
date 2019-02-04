package edu.uclm.esi.tysweb.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class MongoBroker {

	private Pool pool;

	private MongoBroker() {
		//this.pool=new Pool(13);
	}
	
	private static class MongoBrokeHolder{
		static MongoBroker singelton = new MongoBroker();
	}

	public static MongoBroker get() {
		return MongoBrokeHolder.singelton;
	}

	public MongoClient getBD() throws Exception {
		String serverUri = "mongodb://userGames:passwordGames@cluster0-shard-00-00-ofhzj.mongodb.net:27017,cluster0-shard-00-01-ofhzj.mongodb.net:27017,cluster0-shard-00-02-ofhzj.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true";
		MongoClientURI clientUri = new MongoClientURI(serverUri);
		MongoClient bd = new MongoClient(clientUri);
		return bd;
	}

	public void close(MongoClient bd) {
		bd.close();
	}
	
	
}
