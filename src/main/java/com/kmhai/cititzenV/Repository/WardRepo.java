package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kmhai.cititzenV.entity.Ward;
import java.util.List;


public interface WardRepo extends JpaRepository<Ward, Integer> {
    
    Ward findByCode(String code);

    @Query(value = "select * from wards w where left(w.code, 4) = :code", nativeQuery = true)
    List<Ward> findByDistrictCode(@Param("code") String code);

    @Query(value = "select * from wards w where left(w.code, 2) = :code", nativeQuery = true)
    List<Ward> findByProvinceCode(@Param("code") String code);
}
