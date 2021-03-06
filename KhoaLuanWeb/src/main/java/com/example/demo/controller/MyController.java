package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.security.CustomUserDetails;
import com.example.demo.dao.BenhNhanDAO;
import com.example.demo.dao.ChiTietDonThuocDAO;
import com.example.demo.dao.LichHenDAO;
import com.example.demo.dao.NhanVienDAO;
import com.example.demo.dao.PhieuKhamDAO;
import com.example.demo.dao.TaiKhoanDAO;
import com.example.demo.enity.BenhNhan;
import com.example.demo.enity.ChiTietDonThuoc;
import com.example.demo.enity.LichHen;
import com.example.demo.enity.NhanVien;
import com.example.demo.enity.PhieuKhambenh;
import com.example.demo.enity.Role;
import com.example.demo.enity.TaiKhoan;

@Controller
public class MyController {
	@Autowired
	TaiKhoanDAO taikhoanDao;
	@Autowired
	BenhNhanDAO benhnhanDao;
	@Autowired
	NhanVienDAO nhanvienDao;
	@Autowired
	LichHenDAO lichhenDao;
	@Autowired
	PhieuKhamDAO phieukhamDao;
	@Autowired
	ChiTietDonThuocDAO chitietdonthuocDao;
	@Autowired
	public JavaMailSender javaMailSender;

	@GetMapping(value = { "/", "/trang-chu" })
	public String hienThiTrangChu(Principal principal, Model model) {
		if (principal != null) {
			System.out.println(principal.getName());
		}
		
		
		if (principal != null) {
			String username = principal.getName();
			model.addAttribute("us", username);
			try {
				model.addAttribute("chucvu",
						benhnhanDao.GetOneBenhNhanByUser(username).getTaiKhoan().getRole().getName());
				model.addAttribute("name",
						benhnhanDao.GetOneBenhNhanByUser(username).getTen());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// System.out.println("username l?? :" + username);
			try {
				System.out.println("T??n Ch???c Danh :"
						+ benhnhanDao.GetOneBenhNhanByUser(username).getTaiKhoan().getRole().getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			model.addAttribute("chucvu", null);
		}
		return "index";
	}

	@GetMapping("/dich-vu")
	public String hienThiDanhSachDichVu() {
		return "dich-vu";
	}

	@GetMapping("/doimatkhau")
	public String hienthidoiMatKhau() {

		return "doi-mat-khau";

	}

	@PostMapping("/doimatkhau")
	public String doiMatKhau(@RequestParam("matkhaucu") String matkhaucu, @RequestParam("matkhaumoi") String matkhaumoi,
			@RequestParam("nhaplaimatkhau") String nhaplaimatkhau, Principal principal,
			RedirectAttributes redirectAttributes) throws IOException {

		TaiKhoan tk = new TaiKhoan();
		tk = taikhoanDao.GetOneTaiKhoan(principal.getName());
		if (tk.getPassword().equals(matkhaucu)) {
			if (matkhaumoi.equals(nhaplaimatkhau)) {
				tk.setPassword(matkhaumoi);
				taikhoanDao.UpdateTK(tk);
			} else {
				redirectAttributes.addFlashAttribute("thatbai",
						"?????i m???t kh???u th???t b???i!- M???t kh???u m???i kh??ng gi???ng v???i x??c nh???n m???t kh???u");
				return "redirect:/doi-mat-khau";
			}
		} else {
			redirectAttributes.addFlashAttribute("thatbai", "?????i m???t kh???u th???t b???i!- B???n ???? ??i???n sai m???t kh???u c??");
			return "redirect:/doi-mat-khau";
		}
		redirectAttributes.addFlashAttribute("thanhcong", "?????i m???t kh???u th??nh c??ng!-");
		return "redirect:/";
	}

	@GetMapping(value = "/dang-nhap")
	public String showSignIn(HttpSession session, Model model) {

		model.addAttribute("taiKhoan", new TaiKhoan());
		if (session.getAttribute("username") != null) {
			session.invalidate();
		}
		return "dang-nhap";

	}

	@GetMapping("/thong-tin")
	public String thongTinCaNhan(Principal principal, Model model) {

		BenhNhan bn = new BenhNhan();
		try {
			bn = benhnhanDao.GetOneBenhNhanByUser(principal.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate=df.format(bn.getNgaySinh());
		model.addAttribute("ngaysinh", stringDate);
		model.addAttribute("benhnhan", bn);
		List<LichHen> lichHen = new ArrayList<LichHen>();
		try {
			lichHen = lichhenDao.GetAllLichHenByBenhNhan(bn.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("T??n :"+bn.getTen());
		model.addAttribute("lichHen", lichHen);
		
		List<PhieuKhambenh> dsphieukham = new ArrayList<PhieuKhambenh>();
		try {
			dsphieukham= phieukhamDao.GetAllPhieuKhamByBenhNhanID(bn.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("dsphieukham", dsphieukham);

		List<ChiTietDonThuoc> dschitiet = new ArrayList<ChiTietDonThuoc>();
		try {
			dschitiet = chitietdonthuocDao.GetAllChiTietDonThuocByBenhNhan(bn.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("dschitiet", dschitiet);
		return "thong-tin";

	}

	@GetMapping("/dang-ky")
	public String hienThiDangKy(Model model) {
		BenhNhan benhNhan = new BenhNhan();
		benhNhan.setTaiKhoan(new TaiKhoan());
		model.addAttribute("benhNhan", new BenhNhan());
		return "dang-ky";
	}

	@PostMapping("/dang-ky")
	public String dangKy(@ModelAttribute("benhNhan") BenhNhan benhNhan, @ModelAttribute("taikhoan") TaiKhoan taikhoan,
			@RequestParam("ngay_sinh") String ngaysinh, RedirectAttributes redirectAttributes) throws IOException {
		List<Role> listrole=new ArrayList<Role>();
		TaiKhoan tkExist = null;
		tkExist = taikhoanDao.GetOneTaiKhoan(taikhoan.getUsername());
		if (tkExist.getUsername() != null) {
			redirectAttributes.addFlashAttribute("thatbai", "????ng k?? th???t b???i!- Tr??ng username");
			return "index";
		}
		listrole= taikhoanDao.GetAllRole();
		for(int i=0;i<listrole.size();i++)
			if(listrole.get(i).getName().equals("B???nh Nh??n"))
				taikhoan.setRole(listrole.get(i));
		taikhoan.setPassword("123456");
		int ketquaAddTK = taikhoanDao.POSTTaiKhoan(taikhoan);
		if (ketquaAddTK == 200) {
			benhNhan.setTaiKhoan(taikhoan);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try {
				date = formatter.parse(ngaysinh);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			benhNhan.setNgaySinh(date);
			int ketquaPOST = benhnhanDao.POSTBenhNhan(benhNhan);
			if (ketquaPOST == 200) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				taikhoan.setPassword(encoder.encode(taikhoan.getPassword()));
				redirectAttributes.addFlashAttribute("thanhcong", "");
				return "redirect:/dang-nhap";
			} else {
				redirectAttributes.addFlashAttribute("thatbai", "????ng k?? th???t b???i!");
				int ketqua = taikhoanDao.DeleteTaiKhoan(taikhoan.getUsername());
				return "redirect:/dang-ky";

			}
		}
		redirectAttributes.addFlashAttribute("thatbai", "????ng k?? th???t b???i!");
		return "redirect:/dang-ky";
	}

	@PostMapping("/doLogin")
	public String dangNhap(@ModelAttribute("taiKhoan") TaiKhoan taikhoan, RedirectAttributes redirectAttributes)
			throws IOException {

		System.out.println("User name :" + taikhoan.getUsername());
		TaiKhoan tkExist = taikhoanDao.GetOneTaiKhoan(taikhoan.getUsername());
		if (taikhoan.getUsername() != null) {
			if (tkExist.getPassword().equals(taikhoan.getPassword()))
				return "redirect:/index";
		}

		return "redirect:/dang-nhap";
	}

	@GetMapping("/thong-bao")
	public String thongBaoThanhCong() {
		return "thong-bao";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/dang-nhap";
	}

	@GetMapping("/dat-lich")
	public String hienThiDatLich(Model model, Principal principal) throws IOException {

		TaiKhoan user = taikhoanDao.GetOneTaiKhoan(principal.getName());
		if (user.getUsername() == null) {
			return "redirect:/dang-nhap";
		}
		BenhNhan benhNhan = benhnhanDao.GetOneBenhNhanByUser(principal.getName());
		LichHen lichHen = new LichHen();
		lichHen.setBenhNhan(benhNhan);
		model.addAttribute("lichHen", lichHen);
		NhanVien nhanVien = nhanvienDao.GetOneNhanVien((long) (2));
		System.out.println("User name :" + nhanVien.getTen());
		return "dat-lich";
	}

	@PostMapping("/dat-lich")
	public String datLich(@ModelAttribute("lichHen") LichHen lichHen, Principal principal,
			@RequestParam(value = "thoigiankham", required = false) String thoigiankham,
			RedirectAttributes redirectAttributes) throws IOException {
		TaiKhoan user = taikhoanDao.GetOneTaiKhoan(principal.getName());
		if (user.getUsername() == null) {
			return "redirect:/dang-nhap";
		}
		BenhNhan benhNhan = benhnhanDao.GetOneBenhNhanByUser(principal.getName());


		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = formatter.parse(thoigiankham);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// c???n s???a l???i m?? nh??n vi??n v???i m?? b???nh nh??n
		LichHen lh = null;
		lh = lichhenDao.GetLichHenBenhNhan(lichhenDao.doichuoitungay(date), benhNhan.getId());
		lichHen.setBenhNhan(benhNhan);
		lichHen.setThoiGian(date);
		lichHen.setHinhThuc(true);
		lichHen.setTrangThai("3");
		if (lh == null) {
			int ketqua = lichhenDao.POSTLichHen(lichHen);

			if (ketqua == 200) {

				Thread thread = new Thread(() -> {
					MimeMessage message = javaMailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message);
					try {

						messageHelper.setSubject("X??c nh???n ?????t l???ch t???i ph??ng kh??m");
						messageHelper.setFrom("phongkhamlongvien@gmail.com", "Ph??ng kh??m LV");
						messageHelper.setTo(benhNhan.getEmail());
						String content = "<b>Xin ch??o " + benhNhan.getTen() + "</b> <br>";
						content += "Ch??ng t??i ???? nh???n ???????c l???ch h???n tr?????c c???a b???n t???i ph??ng kh??m v???i c??c th??ng tin nh?? sau : <br>";
						content += "H??? v?? t??n :" + benhNhan.getTen() + "<br>";
						content += "Ng??y sinh : " + benhnhanDao.doichuoitungay(benhNhan.getNgaySinh()) + "<br>";
						String gioitinh = "";
						if (benhNhan.isGioiTinh())
							gioitinh = "Nam";
						else
							gioitinh = "N???";
						content += "Gi???i t??nh : " + gioitinh + "<br>";
						content += "Tri???u ch???ng : " + lichHen.getTrieuChung() + "<br>";
						content += "Ghi ch?? b???nh nh??n : " + lichHen.getGhiChu()+ "<br>";
						content += "??i kh??m v??o l??c : " +lichhenDao.doichuoitungay(lichHen.getThoiGian()) + "<br>";
						content += "Vui l??ng c?? m???t t???i ph??ng kh??m ????? nh???n ???????c d???ch v??? t???t nh???t!" + "<br>";
						content += "<br>";
						content += "C???m ??n b???n ???? ?????t l???ch ??? ph??ng kh??m ch??ng t??i.";
						messageHelper.setText(content, true);
						javaMailSender.send(message);
					} catch (Exception e) {
					}
				});
				thread.start();
				///
				redirectAttributes.addFlashAttribute("message", "????ng k?? l???ch h???n th??nh c??ng");
				return "redirect:/thong-bao";
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "M???i ng??y b???n ch??? ?????t ???????c 1 l???ch h???n ");
			return "redirect:/dat-lich";
		}

		redirectAttributes.addFlashAttribute("message", " B???n ???? ?????t l???ch h???n th???t b???i");
		return "redirect:/dat-lich";
	}
	
	@PostMapping("/capnhat")
	public String update(@ModelAttribute("benhNhan") BenhNhan benhNhan,Principal principal,
			 RedirectAttributes redirectAttributes) throws IOException {
			BenhNhan bn = new BenhNhan();
			bn=benhnhanDao.GetOneBenhNhanByUser(principal.getName());
		
			benhNhan.setId(bn.getId());
			benhNhan.setTaiKhoan(bn.getTaiKhoan());
			benhNhan.setNgaySinh(bn.getNgaySinh());
			int ketquaPUT = benhnhanDao.PUTBenhNhan(benhNhan);
			if (ketquaPUT == 200) {
				
				redirectAttributes.addFlashAttribute("thanhcong", "C???p nh???t th??nh c??ng!");
				return "redirect:/";
			} else {
				redirectAttributes.addFlashAttribute("thatbai", "C???p nh???t th???t b???i!");
				return "redirect:/thong-tin";

			}
	}

}
