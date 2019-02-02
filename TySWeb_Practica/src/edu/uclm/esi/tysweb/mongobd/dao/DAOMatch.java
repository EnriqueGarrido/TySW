package edu.uclm.esi.tysweb.mongobd.dao;

import org.bson.BsonDocument;
import org.bson.BsonString;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import edu.uclm.esi.tysweb.games.Player;
import edu.uclm.esi.tysweb.games.Result;
import edu.uclm.esi.tysweb.mongodb.MongoBroker;

public class DAOMatch {
	
	private static String db = "GameDBCloud" ;
	
	/* INSERT RESULT */
	public static void insert(Result result) throws Exception{
		
		BsonDocument bMatch=new BsonDocument();
		bMatch.append("player1", new BsonString(result.getUserName1()));
		bMatch.put("player2", new BsonString(result.getUserName2()));
		bMatch.put("winner", new BsonString(result.getWinner()));
		bMatch.put("time", new BsonString(result.getTime()+""));
		
		MongoClient conection=MongoBroker.get().getBD();
		MongoCollection<BsonDocument> users = 
				conection.getDatabase(db).getCollection("Results", BsonDocument.class);
		
		users.insertOne(bMatch);
		
		MongoBroker.get().close(conection);
	}
	
}
