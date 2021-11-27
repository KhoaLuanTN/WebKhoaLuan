package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.example.demo.enity.BenhNhan;
import com.example.demo.enity.NhanVien;
import com.example.demo.enity.Role;
import com.example.demo.enity.TaiKhoan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
@Repository
public class NhanVienDAO {
	
	static String URL="http://localhost:5001";
	
	static String GET_ALL_NHAN_VIEN=URL+"/nhanvien/getall";
	static String PUT_NHAN_VIEN=URL+"/nhanvien/update";
	static String POST_NHAN_VIEN=URL+"/nhanvien/insert";
	static String GET_NHAN_VIEN_THEO_TEN=URL+"/nhanvien/getbyname";
	static String GET_NHAN_VIEN_THEO_SDT=URL+"/nhanvien/getbysdt";
	static String GET_NHAN_VIEN_THEO_CMND=URL+"/nhanvien/getbycmnd";
	static String GET_ONE_ROLE=URL+"/role/getone";
	static String GET_ONE_NHAN_VIEN=URL+"/nhanvien/getone";
	static String GET_NHAN_VIEN_BY_ROLE=URL+"/nhanvien/getbyRole";
	
	
	TaiKhoanDAO taiKhoanController;
	
	
	public  List<NhanVien>  GetAllNhanVien() throws IOException {
		List<NhanVien>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_NHAN_VIEN);
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
		        NhanVien[] nhanVienList = gson.fromJson(object, NhanVien[].class);
		        	
		        for(int i=0;i<nhanVienList.length;i++)
		        	getall.add(nhanVienList[i]);
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
	
	public  List<NhanVien>  GetAllNhanVienByRole(Long id) throws IOException {
		List<NhanVien>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_NHAN_VIEN_BY_ROLE+"/"+id);
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
		        NhanVien[] nhanVienList = gson.fromJson(object, NhanVien[].class);
		        	
		        for(int i=0;i<nhanVienList.length;i++)
		        	getall.add(nhanVienList[i]);
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
	public  int POSTNhanVien(NhanVien nv) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(nv);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_NHAN_VIEN);
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
	public  int PUTNhanVien(NhanVien nv) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String PUT_PARAMS = gson.toJson(nv);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_NHAN_VIEN+"/"+nv.getId());
	    HttpURLConnection putConnection = (HttpURLConnection) obj.openConnection();
	    putConnection.setRequestMethod("PUT");
	    putConnection.setRequestProperty("Content-Type", "application/json");


	    putConnection.setDoOutput(true);
	    OutputStream os = putConnection.getOutputStream();
	    os.write(PUT_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = putConnection.getResponseCode();
	    String message=putConnection.getResponseMessage();
	    

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            putConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("PUT NOT WORKED");
	    }
	    return responseCode;
	}
	public Date doingaytuchuoi(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
        try {
             date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
	}
	public String doichuoitungay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = formatter.format(date);
		return dateFormat;
	}
	public Role GetOneRole(Long id) throws IOException {
		Role role=new Role();
		URL urlForGetRequest = new URL(GET_ONE_ROLE+"/"+id);
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
			role = gson.fromJson(response, Role.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return role;
	}
	public NhanVien NhanVienDangSuDung(String username) {
		NhanVien nv= new NhanVien();
		List<NhanVien> list= new ArrayList<NhanVien>();
		try {
			list=GetAllNhanVien();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<1;i++)
		{
			if(username.equals(list.get(i).getTaiKhoan().getUsername()))
				nv=list.get(i);
		}
		return nv;
	}
	public boolean KiemTraTaiKhoan(TaiKhoan tk)
	{
		taiKhoanController=new TaiKhoanDAO();
		TaiKhoan kiemtra=null;
		try {
			kiemtra = taiKhoanController.GetOneTaiKhoan(tk.getUsername());
			System.out.println(kiemtra.getUsername());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(kiemtra.getUsername()==null)
			return false;
		else
			return true;
	}
	public boolean Checkcmnd(String cmnd)
	{
		Pattern cmndCheck = Pattern.compile("[0-9]{9}");
		if(cmndCheck.matcher(cmnd).matches())
			return true;
		
			return false;		
	}
	public boolean Checkemail(String email)
	{
		Pattern emailCheck = Pattern.compile("^(.+)@(.+)$");
		if(emailCheck.matcher(email).matches())
			return true;
		
			return false;		
	}
	public boolean Checksdt(String sdt)
	{
		Pattern sdtCheck = Pattern.compile("0[0-9]{9}");
		if(sdtCheck.matcher(sdt).matches())
			return true;
		
			return false;		
	}
	public List<NhanVien> searchCMND(String cmnd) {
			ArrayList<NhanVien>list =new ArrayList<NhanVien>();
			ArrayList<NhanVien>ketqua =new ArrayList<NhanVien>();
			try {
				list.addAll(GetAllNhanVien());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			for (NhanVien nv : list) 
			{
				if(nv.getCmnd().toLowerCase().indexOf(cmnd.toLowerCase())!=-1)
				{
					ketqua.add(nv);
				}
				
					
			}
			return ketqua;
		}
	public List<NhanVien> searchSDT(String sdt) {
		ArrayList<NhanVien>list =new ArrayList<NhanVien>();
		ArrayList<NhanVien>ketqua =new ArrayList<NhanVien>();
		try {
			list.addAll(GetAllNhanVien());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		for (NhanVien nv : list) 
		{
			if(nv.getSoDienThoai().toLowerCase().indexOf(sdt.toLowerCase())!=-1)
			{
				ketqua.add(nv);
			}
				
		}
		return ketqua;
	}
	public List<NhanVien> searchName(String name) {
		ArrayList<NhanVien>list =new ArrayList<NhanVien>();
		ArrayList<NhanVien>ketqua =new ArrayList<NhanVien>();
		try {
			list.addAll(GetAllNhanVien());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		for (NhanVien nv : list) 
		{
			if(nv.getTen().toLowerCase().indexOf(name.toLowerCase())!=-1)
			{
				ketqua.add(nv);
			}
				
		}
		return ketqua;
	}
	
	/**
	 * @author Vien
	 * date: 17/4/2021
	 * @return list danh sách nhân viên theo tên
	 * @decription: Lấy danh sách nhân viên theo tên được gọi về từ RestFullAPI
	 * */
	//[START Tìm kiếm theo tên nhân viên]
	public  List<NhanVien>  SearchName(String name) throws IOException {
		List<NhanVien>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_NHAN_VIEN_THEO_TEN+"/"+name);
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
		        NhanVien[] nhanViens = gson.fromJson(object, NhanVien[].class);
		        	
		        for(int i=0;i<nhanViens.length;i++)
		        	getall.add(nhanViens[i]);
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
	//[END Tìm kiếm theo tên]
	
	/**
	 * @author Vien
	 * date: 17/4/2021
	 * @return list danh sách nhân viên theo số điện thoại
	 * @decription: Lấy danh sách nhân viên theo số điện thoại được gọi về từ RestFullAPI
	 * */
	//[START Tìm kiếm theo sdt nhân viên]
	public  List<NhanVien>  SearchSDT(String sdt) throws IOException {
		List<NhanVien>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_NHAN_VIEN_THEO_SDT+"/"+sdt);
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
		        NhanVien[] nhanViens = gson.fromJson(object, NhanVien[].class);
		        	
		        for(int i=0;i<nhanViens.length;i++)
		        	getall.add(nhanViens[i]);
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
	//[END Tìm kiếm theo số điện thoại]
	
	/**
	 * @author Vien
	 * date: 17/4/2021
	 * @return list danh sách nhân viên theo chứng minh nhân dân
	 * @decription: Lấy danh sách nhân viên theo chứng minh nhân dân được gọi về từ RestFullAPI
	 * */
	//[START Tìm kiếm theo chứng minh nhân dân bệnh nhân]
	public  List<NhanVien>  SearchCMND(String cmnd) throws IOException {
		List<NhanVien>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_NHAN_VIEN_THEO_CMND+"/"+cmnd);
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
		        NhanVien[] nhanViens = gson.fromJson(object, NhanVien[].class);
		        	
		        for(int i=0;i<nhanViens.length;i++)
		        	getall.add(nhanViens[i]);
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
	//[END Tìm kiếm nhân viên theo chứng minh nhân dân]

	/**
	 * @author Vien
	 * date: 20/4/2021
	 * @return một bệnh nhân
	 * @decription: Lấy bênh nhân theo id bệnh nhân được gọi về từ RestFullAPI
	 * */
	//[START lấy về 1 nhan vien theo id]
	public NhanVien GetOneNhanVien(Long id) throws IOException {
		NhanVien bn=new NhanVien();
		URL urlForGetRequest = new URL(GET_ONE_NHAN_VIEN+"/"+id);
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
			
			Gson gson = new GsonBuilder()
        		    .setDateFormat("yyyy-MM-dd")
        		    .create();
			bn = gson.fromJson(response, NhanVien.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return bn;
	}
	//[END lấy về 1 nhanvien theo id]
}
