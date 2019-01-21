package edu.uclm.esi.tysweb.mongodb;

import com.mongodb.MongoClient;

public class MongoBroker {

	private Pool pool;

	private MongoBroker() {
		this.pool=new Pool(15);
	}
	
	private static class MongoBrokeHolder{
		static MongoBroker singelton = new MongoBroker();
	}

	public static MongoBroker get() {
		return MongoBrokeHolder.singelton;
	}

	public MongoClient getBD() throws Exception {
		return this.pool.getBD();
	}

	public void close(MongoClient bd) {
		this.pool.close(bd);
	}
}
