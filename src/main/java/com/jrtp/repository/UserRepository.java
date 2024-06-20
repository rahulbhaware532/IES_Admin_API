package com.jrtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jrtp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("update UserEntity set accStatus=: where userId=:userId")
	public Integer updateAccStatus(Long userId, String status);

	public UserEntity findByEmail(String email);

	public UserEntity findByEmailAndPwd(String email, String pwd);
}
