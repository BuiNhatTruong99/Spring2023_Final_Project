package com.datamining.dao;

import com.datamining.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProfileDAO extends JpaRepository<Profile, Integer> {


}
