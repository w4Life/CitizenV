package com.kmhai.cititzenV.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kmhai.cititzenV.entity.Hamlet;

public interface HamletRepo extends JpaRepository<Hamlet, Integer> {
    
    Hamlet findByCode(String code);

    boolean existsByCode(String code);

    @Query(value = "select * from hamlets h where left(h.code, 4) = :code", nativeQuery = true)
    List<Hamlet> findByDistrictCode(@Param("code") String code);

    @Query(value = "select * from hamlets h where left(h.code, 2) = :code", nativeQuery = true)
    List<Hamlet> findByProvinceCode(@Param("code") String code);

    @Query(value = "select * from hamlets h where left(h.code, 6) = :code", nativeQuery = true)
    List<Hamlet> findByWardCode(@Param("code") String code);

    
}
