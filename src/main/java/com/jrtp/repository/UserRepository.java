package com.jrtp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jrtp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Transactional
	@Modifying
	@Query("update UserEntity set accStatus=:status where userId=:userId")
	public Integer updateAccStatus(@Param("userId") Long userId, @Param("status") String status);

	public UserEntity findByEmail(String email);

	public UserEntity findByEmailAndPwd(String email, String pwd);
}
