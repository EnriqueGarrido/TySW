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
		MongoClient conexion = MongoBroker.get().getBD();
		
		BsonDocument criterio1=new BsonDocument();
		criterio1.append("email", new BsonString(email));
		criterio1.append("pwd", new BsonString(pwd));
		
		MongoCollection<BsonDocument> usuarios= conexion.getDatabase(db).getCollection("Players", BsonDocument.class);
		FindIterable<BsonDocument> resultado = usuarios.find(criterio1);
		Player usuario=null;

		if (resultado.first()!=null) {
			usuario=new Player();
			usuario.setEmail(resultado.first().getString("email").getValue());
			usuario.setUserName(resultado.first().getString("name").getValue());
			//usuario.setPhoto(resultado.first().getString("photo").getValue());
		} else {
			throw new Exception("Error en login");
		}
		
		MongoBroker.get().close(conexion);
		
		return usuario;
	}
}
