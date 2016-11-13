package com.example.exsendvideohttprequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class AsyncTeste extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... params) {

		HttpURLConnection connection = null;
		DataOutputStream outputStream = null;
		String pathToOurFile = "/sdcard/video.mp4";
		String urlServer = "http://christopherwp.com.br/icamp/upload-videos-comicio/1/11";
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1024;

		try {
			File file = new File(pathToOurFile);
			FileInputStream fileInputStream = new FileInputStream(file);

			URL url = new URL(urlServer);
			connection = (HttpURLConnection) url.openConnection();
			// Allow Inputs &amp; Outputs.
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// Set HTTP method to POST.
			connection.setRequestMethod("POST");

			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);

			outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			System.out.println(file.getName());
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"video\";filename=\""
							+ file.getName() + "\"" + lineEnd);
			outputStream.writeBytes(lineEnd);

			buffer = new byte[maxBufferSize];

			bytesRead = 0;
			while ((bytesRead = fileInputStream.read(buffer, 0, maxBufferSize)) > 0) {
				System.out.println(">> " + bytesRead);
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens
					+ lineEnd);

			// Responses from the server (code and message)
			int serverResponseCode = connection.getResponseCode();
			String serverResponseMessage = connection.getResponseMessage();

			System.out.println(serverResponseMessage + "(" + serverResponseCode
					+ ")");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			System.out.println(sb.toString());

			fileInputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
