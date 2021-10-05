package com.springboot.security.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dto.Message;
import com.springboot.security.dto.JwtDto;
import com.springboot.security.dto.LoginUser;
import com.springboot.security.dto.NewUser;
import com.springboot.security.entity.Role;
import com.springboot.security.entity.User;
import com.springboot.security.enums.RoleName;
import com.springboot.security.jwt.JwtProvider;
import com.springboot.security.service.ClientService;
import com.springboot.security.service.RoleService;
import com.springboot.security.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	ClientService clientService;

	@PostMapping("/new")	
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos erróneos o email inválido"), HttpStatus.BAD_REQUEST);
		if(userService.existsByUserName(newUser.getUserName()))
			return new ResponseEntity<>(new Message("Nombre de Usuario ya existe en la Base de datos"), HttpStatus.BAD_REQUEST);
		if(userService.existsByEmail(newUser.getEmail()))
			return new ResponseEntity<>(new Message("Correo electróncio ya existe en la Base de datos"), HttpStatus.BAD_REQUEST);
		
		User user = new User(newUser.getName(),newUser.getUserName(),newUser.getEmail(),passwordEncoder.encode(newUser.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		if (newUser.getRoles().contains("admin"))
			roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		user.setRoles(roles);
		userService.save(user);
		
		return new ResponseEntity<>(new Message("Usuario creado con éxito"), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Message("Campos erróneos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	@ResponseBody
	public ResponseEntity<List<User>> findAll() {
		List<User> lista = clientService.findAll();
		return ResponseEntity.ok(lista);
	}
	
//	@GetMapping("/users/{id}")
//	public User show(@PathVariable Integer id) {
//		return clientService.findById(id)
//	}
	
	
	@PostMapping("/users")
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.noContent().build();
		} else {
			user.setIdUser(0);
			User userCreated = clientService.save(user);
			return ResponseEntity.ok(userCreated);
		}
	}
	
	@PutMapping("/updateUser")
	@ResponseBody
	public ResponseEntity<User> update(@RequestBody User obj) {
		if (obj == null) {
			return ResponseEntity.badRequest().build();
		} else {
			Optional<User> optUser = clientService.findById(obj.getIdUser());
			if (optUser.isPresent()) {
				User updateUser = clientService.save(obj);
				return ResponseEntity.ok(updateUser);
			} else {
				return ResponseEntity.badRequest().build();
			}
		}
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseBody
	public ResponseEntity<User> delete(@PathVariable("id") int id) {
		Optional<User> optUser = clientService.findById(id);
		if (optUser.isPresent()) {
			clientService.delete(id);
			Optional<User> optDeleteUser = clientService.findById(id);
			if (optDeleteUser.isPresent()) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.ok(optUser.get());
			}
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/users/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		
		User user = clientService.findByIdUser(id);
		
		if (!file.isEmpty()) {
			String nameFile = file.getOriginalFilename();
			Path rutaFile = Paths.get("uploads//clients").resolve(nameFile).toAbsolutePath();
			
			try {
				Files.copy(file.getInputStream(), rutaFile);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente " + nameFile);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			user.setPicture(nameFile);
			clientService.save(user);
			
			response.put("cliente", user);
			response.put("mensaje", "Has subido la imagen con éxito: " + nameFile);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	

}
