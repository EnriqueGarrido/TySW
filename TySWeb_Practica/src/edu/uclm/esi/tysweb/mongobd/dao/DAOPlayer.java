package edu.uclm.esi.tysweb.mongobd.dao;

import org.bson.BsonDocument;
import org.bson.BsonString;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import edu.uclm.esi.tysweb.games.Player;
import edu.uclm.esi.tysweb.mongodb.MongoBroker;

public class DAOPlayer {

	private static String db = "GameDBCloud" ;
	
	/* LOGIN */
	public static Player login(String email, String pwd) throws Exception {
		MongoClient conection = MongoBroker.get().getBD();
		
		BsonDocument criterion=new BsonDocument();
		criterion.append("email", new BsonString(email));
		criterion.append("pwd", new BsonString(pwd));
		
		MongoCollection<BsonDocument> users= conection.getDatabase(db).getCollection("Players", BsonDocument.class);
		FindIterable<BsonDocument> result = users.find(criterion);
		Player user=null;

		if (result.first()!=null) {
			user=new Player();
			user.setEmail(result.first().getString("email").getValue());
			user.setUserName(result.first().getString("name").getValue());
			//usuario.setPhoto(resultado.first().getString("photo").getValue());
		} else {
			throw new Exception("Error loging in");
		}
		
		MongoBroker.get().close(conection);
		
		return user;
	}
	
	/* REGISTER */
	public static void insert(Player new_user, String pwd) throws Exception{
		if(isUserAlreadyInDB(new_user))
			throw new Exception ("Already Register");
		
		BsonDocument bUser=new BsonDocument();
		bUser.append("email", new BsonString(new_user.getEmail()));
		bUser.put("pwd", new BsonString(pwd));
		bUser.put("name", new BsonString(new_user.getUserName()));
		//bUsuario.put("score", new BsonInt32(usuario.getScore()));
		//bUsuario.put("photo", new BsonString(usuario.getPhoto()));
		
		MongoClient conection=MongoBroker.get().getBD();
		MongoCollection<BsonDocument> users = 
				conection.getDatabase(db).getCollection("Players", BsonDocument.class);
		
		users.insertOne(bUser);
		
		MongoBroker.get().close(conection);
	}
	
	/* CHECK USER EXISTANCE */
	public static boolean isUserAlreadyInDB (Player user) throws Exception {
		MongoClient conection = MongoBroker.get().getBD();
		
        BsonDocument criterion=new BsonDocument();
        criterion.append("email", new BsonString(user.getEmail()));
        
        MongoCollection<BsonDocument> users = conection.getDatabase(db).getCollection("Players", BsonDocument.class);
        BsonDocument user1=users.find(criterion).first();
        
        MongoBroker.get().close(conection);
        
        return user1!=null;
	}
}
