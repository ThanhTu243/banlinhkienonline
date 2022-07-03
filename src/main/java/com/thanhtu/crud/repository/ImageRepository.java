package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
}
