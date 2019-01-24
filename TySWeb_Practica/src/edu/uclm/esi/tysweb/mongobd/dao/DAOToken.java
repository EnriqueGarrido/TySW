package edu.uclm.esi.tysweb.mongobd.dao;

import org.bson.BsonDocument;
import org.bson.BsonString;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import edu.uclm.esi.tysweb.games.Token;
import edu.uclm.esi.tysweb.mongodb.MongoBroker;

public class DAOToken {

	private static String db = "GameDBCloud" ;
	
	public static void insert(Token new_token) throws Exception{
		BsonDocument bToken=new BsonDocument();
		bToken.append("email", new BsonString(new_token.getPlayerEmail()));
		bToken.append("token", new BsonString(new_token.getToken()));
		bToken.append("expired", new BsonString(String.valueOf(new_token.getExpiredDate())));
		
		MongoClient conection=MongoBroker.get().getBD();
		MongoCollection<BsonDocument> tokens = 
				conection.getDatabase(db).getCollection("Token", BsonDocument.class);
		
		tokens.insertOne(bToken);
		MongoBroker.get().close(conection);
	}
}
