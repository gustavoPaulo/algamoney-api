package com.algamoneyapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteSenha {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("maria"));
	}
}
