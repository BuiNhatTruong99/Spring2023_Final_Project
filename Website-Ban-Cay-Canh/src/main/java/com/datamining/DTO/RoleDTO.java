package com.datamining.DTO;

import com.datamining.entity.Authority;
import com.datamining.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
@Data
public class RoleDTO {
    private int id;
    private String name;
    @JsonIgnore
    List<Authority> authorities;

    public static RoleDTO convert(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setAuthorities(role.getAuthorities());
        return roleDTO;
    }
}