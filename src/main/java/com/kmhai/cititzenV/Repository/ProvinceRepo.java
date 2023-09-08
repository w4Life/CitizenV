package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kmhai.cititzenV.entity.Province;


public interface ProvinceRepo extends JpaRepository<Province, Integer> {
    
    Province findByCode(String code);
}
