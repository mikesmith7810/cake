package com.xdesign.cake.db.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xdesign.cake.domain.Cake;

@Transactional
public interface CakeRepository extends JpaRepository<Cake, Integer> {

	Cake getById( Integer id );
}
