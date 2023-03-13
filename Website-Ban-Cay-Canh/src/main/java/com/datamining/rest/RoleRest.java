package com.datamining.rest;



import com.datamining.DTO.RoleDTO;
import com.datamining.entity.Role;
import com.datamining.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")

public class RoleRest {
	@Autowired
	RoleService roleService;

	@GetMapping()
	public ObjectResponse getAll() {
		var roles = roleService.findAll();
		var roleDTO = roles.stream().map(RoleDTO::convert).collect(Collectors.toList());
		return new ObjectResponse("ok", roleDTO, HttpStatus.OK.value());
	}
	@GetMapping("/all")
	public  List<Role> getAlll(){
		List<Role> list = roleService.findAll();
		return  list;
	}

}
