package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.kmhai.cititzenV.entity.District;
import java.util.List;



public interface DistrictRepo extends JpaRepository<District, Integer> {
    
    District findByCode(String code);
    
    @Query(value = "select * from districts d where left(d.code, 2) = :code", nativeQuery = true)
    List<District> findByProvinceCode(@Param("code") String code);
}
