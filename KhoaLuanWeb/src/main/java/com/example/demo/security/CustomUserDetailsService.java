package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaiKhoanDAO;
import com.example.demo.enity.TaiKhoan;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private TaiKhoanDAO taikhoanservice;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		TaiKhoan user = new TaiKhoan();
		try {
			user = taikhoanservice.GetOneTaiKhoan(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user.getUsername()==null) {
			throw new UsernameNotFoundException("Not found : "+ username);
		}
		return new CustomUserDetails(user);
	}

}
