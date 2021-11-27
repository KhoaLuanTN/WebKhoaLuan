package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import com.example.demo.enity.ChiTietDonThuoc;
import com.example.demo.enity.DonThuoc;
import com.example.demo.enity.Thuoc;
@Repository
public class ChiTietDonThuocDAO {

	static String URL="http://localhost:5001";
	
	static String GET_ALL_CHI_TIET_DON_THUOC=URL+"/chitietdonthuoc/getall";
	static String POST_CHI_TIET_DON_THUOC=URL+"/chitietdonthuoc/insert";
	static String GET_ALL_CHI_TIET_DON_THUOC_BY_DON_THUOC=URL+"/chitietdonthuoc/getallbydonthuoc";
	static String GET_ALL_CHI_TIET_DON_THUOC_BY_BENH_NHAN=URL+"/chitietdonthuoc/getallbybenhnhan";
	/**
	 * @author Vien
	 * date: 13/5/2021
	 * @return list danh sách chi tiết thuốc
	 * @decription: Lấy danh sách chi tiết thuốc được gọi về từ RestFullAPI
	 * */
	//[START GetAll]
	public  List<ChiTietDonThuoc>  GetAllChiTietDonThuoc() throws IOException {
		List<ChiTietDonThuoc>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_CHI_TIET_DON_THUOC);
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
		        ChiTietDonThuoc[] chitietdonthuocList = gson.fromJson(object, ChiTietDonThuoc[].class);
		        	
		        for(int i=0;i<chitietdonthuocList.length;i++)
		        	getall.add(chitietdonthuocList[i]);
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
	//[END GetALL]
	
	/**
	 * @author Vien
	 * date : 13/5/2021
	 * @return Thêm đơn thuốc vào cơ sở dữ liệu 
	 * @decripstion : Thêm đơn thuốc bằng cái sử dụng RestFull API
	 * */
	//[START POST Request]
	public  int POSTChiTietDonThuoc(ChiTietDonThuoc chitietdonthuoc) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(chitietdonthuoc);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_CHI_TIET_DON_THUOC);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type", "application/json");


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
	
	/**
	 * @author Vien
	 * date: 13/5/2021
	 * @return list danh sách chi tiết thuốc
	 * @decription: Lấy danh sách chi tiết thuốc được gọi về từ RestFullAPI
	 * */
	//[START GetAll]
	public  List<ChiTietDonThuoc>  GetAllChiTietDonThuocByDonThuoc(Long id) throws IOException {
		List<ChiTietDonThuoc>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_CHI_TIET_DON_THUOC_BY_DON_THUOC+"/"+id);
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
		        ChiTietDonThuoc[] chitietdonthuocList = gson.fromJson(object, ChiTietDonThuoc[].class);
		        	
		        for(int i=0;i<chitietdonthuocList.length;i++)
		        	getall.add(chitietdonthuocList[i]);
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
	//[END GetALL]
	
	public  List<ChiTietDonThuoc>  GetAllChiTietDonThuocByBenhNhan(Long id) throws IOException {
		List<ChiTietDonThuoc>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_CHI_TIET_DON_THUOC_BY_BENH_NHAN+"/"+id);
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
		        ChiTietDonThuoc[] chitietdonthuocList = gson.fromJson(object, ChiTietDonThuoc[].class);
		        	
		        for(int i=0;i<chitietdonthuocList.length;i++)
		        	getall.add(chitietdonthuocList[i]);
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
