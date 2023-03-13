package com.datamining.servide.impl;

import com.datamining.dao.RoleDAO;
import com.datamining.entity.Role;
import com.datamining.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleIServiceImpl implements RoleService {
@Autowired
RoleDAO dd;
    @Override
    public List<Role> findAll() {
        return dd.findAll();
    }
}
