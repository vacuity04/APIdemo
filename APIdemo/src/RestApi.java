import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;

public class RestApi {

	HttpRequest request;
	HttpResponse<String> response;
	String apiKey;
	Transcript transcript = new Transcript();
	Gson gson = new Gson();
	File rawgKeyFile;
	File rapidKeyFile;
	String rawgKey;
	String rapidKey;
	Scanner scan;
	
	public RestApi() {
		getKeys();
	}
	
	public void getKeys() {

		try {
			rawgKeyFile = new File("rawgKey.txt");
			rapidKeyFile = new File("rapidKey.txt");
			
			scan = new Scanner(rawgKeyFile);
			rawgKey = scan.nextLine();
			System.out.println(rawgKey);
			scan.close();
			
			scan = new Scanner(rapidKeyFile);
			rapidKey = scan.nextLine();
			System.out.println(rapidKey);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Transcript getGame(int index) throws IOException, InterruptedException {
		
		request = HttpRequest.newBuilder()
				.uri(URI.create("https://rawg-video-games-database.p.rapidapi.com/games/" + index + rawgKey))
				.header("X-RapidAPI-Key", rapidKey)
				.header("X-RapidAPI-Host", "rawg-video-games-database.p.rapidapi.com")
				.build();
		response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		transcript = gson.fromJson(response.body(), Transcript.class);
		
		System.out.println(transcript.getId() + " | " + transcript.getName());
		
		return transcript;
	}
}
