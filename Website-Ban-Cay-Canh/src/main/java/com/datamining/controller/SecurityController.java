package com.datamining.controller;

import com.datamining.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
public class SecurityController {
	@Autowired
	BCryptPasswordEncoder pe;
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
//		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "user/security/loginQR";
	}

	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "redirect:/product/list";
	}

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "user/security/loginQR";
	}

	@RequestMapping("/login/unauthoried")
	public String loginauthority(Model model) {
		model.addAttribute("message", "Bạn không đủ quyền!");
		return "redirect:/product/list";
	}
	@Autowired
	AccountService accountService;
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2 ){
		accountService.loginFromOAuth2(oauth2);
		return "redirect:/product/list";
	}
	@GetMapping("/change-password")
	public String changePassword(){

		return "user/security/changePassword";
}

}
