package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
