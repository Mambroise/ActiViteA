package com.example.activitea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

//	@Query(value="SELECT * FROM address WHERE user_id=?1", nativeQuery=true)
//	List<Address> findAddressByUserId(int userId);
	List<Address> findByUserId(int userId);
	Optional<Address> findByUserIdAndActiveIsTrue(int userId);
}
