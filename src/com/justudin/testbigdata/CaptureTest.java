package com.justudin.testbigdata;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

public class CaptureTest {
	private static String API_URL = "";
	
	public static void main(String[] args) throws JSONException, ClientProtocolException, IOException {

		final long numClient = 1;
		final long numData = 50000;
		
		long startTime = System.currentTimeMillis();
		// System.out.println(startTime);

		for (int thread = 0; thread < numClient; thread++) {
			new postThread(thread, startTime, numData).start();
		}
	}

	public void CaptureTestAuto(int numClient, long numData) {
		// TODO Auto-generated constructor stub
		long startTime = System.currentTimeMillis();
		// System.out.println(startTime);
		//set number of thread
		//td.setThreadNum(numClient);
		//td.set(numClient);
		for (int thread = 0; thread < numClient; thread++) {
			new postThread(thread, startTime, numData).start();
		}
	}

	public static void postData() throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(API_URL+"test");
		// Create a JSON embeded object
		JSONObject obj = new JSONObject();
		obj.put("deviceId", "2d47c2cb29386015");
		obj.put("deviceName", "Android 001");
		obj.put("deviceDescription", "Android 001 use in cold storing");
		obj.put("sentAt", "2017-09-01T17:37:39.528Z");
		obj.put("temperature", "30.5");
		obj.put("humidity", "34.9");
		obj.put("longitude", "126.9932125");
		obj.put("latitude", "37.5605866");
		obj.put("image", "15042550617192d47c2cb29386015");
		StringEntity entity = new StringEntity(obj.toString());
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		CloseableHttpResponse response = client.execute(httpPost);
		// assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		client.close();
	}

	private static class postThread extends Thread {
		private int id;
		private long startTime;
		private long numData;
		//threadData td = new threadData();

		public postThread(int id, long startTime, long numData) {
			this.id = id;
			this.startTime = startTime;
			this.numData = numData;
		}

		@Override
		public void run() {
			try {
				
				for (int num = 0; num < this.numData; num++) {
					postData();
				}
				long endTime = System.currentTimeMillis();
				System.out.println("Thread-" + id + " : " + (endTime - startTime));
				// thread number - 1;
				//td.setDoneThreadNum();
				//td.decrease();
				//td.addAndGet(1);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void deleteData() {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpDelete deleteRequest = new HttpDelete(API_URL+"test");
			CloseableHttpResponse response = client.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			System.out.println("Delete data: "+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}