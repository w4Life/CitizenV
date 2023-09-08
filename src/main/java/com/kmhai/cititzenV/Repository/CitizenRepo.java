package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kmhai.cititzenV.entity.Citizen;
import java.util.List;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {

    Citizen findByIdentityCode(String identityCode);

    Boolean existsByIdentityCode(String code);

    @Query(value = "select c.*, ad.id as address_id, h.code as hamlet_code " +
        "from citizens c " +
        "join addresses ad on c.permanent_id = ad.id " +
        "join hamlets h on ad.hamlet_code = h.code " +
        "where left(h.code, 2) = :provinceCode", nativeQuery = true)
    List<Citizen> findByProvince(@Param("provinceCode") String provinceCode);

    @Query(value = "select c.*, ad.id as address_id, h.code as hamlet_code " +
        "from citizens c " +
        "join addresses ad on c.permanent_id = ad.id " +
        "join hamlets h on ad.hamlet_code = h.code " +
        "where left(h.code, 4) = :districtCode", nativeQuery = true)
    List<Citizen> findByDistrict(@Param("districtCode") String districtCode);

    @Query(value = "select c.*, ad.id as address_id, h.code as hamlet_code " +
        "from citizens c " +
        "join addresses ad on c.permanent_id = ad.id " +
        "join hamlets h on ad.hamlet_code = h.code " +
        "where left(h.code, 6) = :wardCode", nativeQuery = true)
    List<Citizen> findByWard(@Param("wardCode") String wardCode);

    @Query(value = """
        select c.*, ad.id as address_id, h.code as hamlet_code
        from citizens c
        join addresses ad on c.permanent_id = ad.id
        join hamlets h on ad.hamlet_code = h.code
        where h.code = :hamletCode
            """, nativeQuery = true)
    List<Citizen> findByHamlet(@Param("hamletCode") String hamletCode);

}
