package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.enity.LichHen;
import com.example.demo.enity.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Repository
public class LichHenDAO {
	
static String URL="http://18.118.228.236:5001";
	//static String URL="http://localhost:5001";
	static String POST_LICH_HEN=URL+"/lichhen/insert";
	static String GET_LICH_HEN_TRONG_NGAY=URL+"/lichhen/ktralichhenbn";
	static String GET_ALL_LICH_HEN_BY_BN=URL+"/lichhen/getlichhenbybn";
	
	
	/**
	 * @author Vien
	 * date : 12/4/2021
	 * @return Thêm lich hen vào cơ sở dữ liệu 
	 * @decripstion : Thêm lich hen bằng cái sử dụng RestFull API
	 * */
	//[START POST Lich Hen]
	public  int POSTLichHen(LichHen lh) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(lh);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_LICH_HEN);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type","application/json;charset=utf8");


	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	    return responseCode;
	}
	//[End POST Request]

	public String doichuoitungay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = formatter.format(date);
		return dateFormat;
	}
	
	public LichHen GetLichHenBenhNhan(String date,Long id) throws IOException {
		LichHen lh=new LichHen();
		URL urlForGetRequest = new URL(GET_LICH_HEN_TRONG_NGAY+"/"+date+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			Gson gson = new Gson();
			lh = gson.fromJson(response, LichHen.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return lh;
	}
	
	public  List<LichHen>  GetAllLichHenByBenhNhan(Long id) throws IOException {
		List<LichHen>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_LICH_HEN_BY_BN+"/"+id);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        LichHen[] lichhenList = gson.fromJson(object, LichHen[].class);
		        	
		        for(int i=0;i<lichhenList.length;i++)
		        	getall.add(lichhenList[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
}
