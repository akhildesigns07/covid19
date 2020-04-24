package com.akhil.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.akhil.project.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	List<User> findAll();
}
