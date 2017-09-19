package com.justudin.testbigdata;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class QueryTest {
	private static String API_URL = "";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		final long numClient = 5; // 1,5,10

		long startTime = System.currentTimeMillis();
		// System.out.println(startTime);

		for (int thread = 0; thread < numClient; thread++) {
			// queryData();
			new queryThread(thread, startTime).start();
		}

	}

	public static void queryData() throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(API_URL+"test");
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network
			// socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager.
			try {
				// System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				//String bodyAsString = EntityUtils.toString(response1.getEntity());
				//System.out.print(bodyAsString);
				// and ensure it is fully consumed
				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
	}

	private static class queryThread extends Thread {
		private int id;
		private long startTime;

		public queryThread(int id, long startTime) {
			this.id = id;
			this.startTime = startTime;
		}

		@Override
		public void run() {
			// run process here
			try {
				queryData();
				long endTime = System.currentTimeMillis();
				System.out.println("Thread-" + id + " : " + (endTime - startTime));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
