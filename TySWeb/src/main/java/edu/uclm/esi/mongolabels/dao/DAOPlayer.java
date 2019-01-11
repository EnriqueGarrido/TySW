package edu.uclm.esi.mongolabels.dao;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

import edu.uclm.esi.games.Player;
import edu.uclm.esi.games.RegisteredPlayer;

public class DAOPlayer {
	
	private static String db = "games" ;
	
	/* LOGIN */
	public static Player login(String email, String pwd) throws Exception {
		MongoClient conexion = MongoBroker.get().getBD();
		
		BsonDocument criterio1=new BsonDocument();
		criterio1.append("email", new BsonString(email));
		criterio1.append("pwd", new BsonString(pwd));
		
		BsonDocument criterio2=new BsonDocument();
		criterio2.append("user", new BsonString(email));
		criterio2.append("pwd", new BsonString(pwd));
		
		MongoCollection<BsonDocument> usuarios= conexion.getDatabase(db).getCollection("usuarios", BsonDocument.class);
		FindIterable<BsonDocument> resultado = usuarios.find(criterio1);
		Player usuario=null;

		if (resultado.first()!=null) {
			usuario=new RegisteredPlayer();
			usuario.setEmail(resultado.first().getString("email").getValue());
			usuario.setUserName(resultado.first().getString("user").getValue());
			//usuario.setPhoto(resultado.first().getString("photo").getValue());
		}else {
			resultado = usuarios.find(criterio2);
			if (resultado.first()!=null) {
				usuario=new RegisteredPlayer();
				usuario.setEmail(resultado.first().getString("email").getValue());
				usuario.setUserName(resultado.first().getString("user").getValue());
				//usuario.setPhoto(resultado.first().getString("photo").getValue());
			} else
				throw new Exception("Error en login");
		}
		MongoBroker.get().close(conexion);
		
		return usuario;
	}
}
