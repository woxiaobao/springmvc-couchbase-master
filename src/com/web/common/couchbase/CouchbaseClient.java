package com.web.common.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public class CouchbaseClient {
	
	// ip of the bucket (empty) string if none
	private static String ip = "127.0.0.1";
	
	 // Name of the Bucket to connect to
	private static String bucketdb = "default";
 
    // Password of the bucket (empty) string if none
	//private static String password = "ejianlian";
	
	//bucket and cluster class
	private static Bucket bucket = null;
	private static CouchbaseCluster cluster = null;
	
	static{
		// Create a cluster reference
	    cluster = CouchbaseCluster.create(ip);

	    //Connect to the bucket and open it
	    //Bucket bucket = cluster.openBucket(bucketdb,password);
	    bucket = cluster.openBucket(bucketdb);
	}
	
	public static Bucket getBucket(){
		return bucket;
	}

	
}
