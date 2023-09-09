package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kmhai.cititzenV.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    
    @Query(value = """
            select case 
            when count(*) > 0 then true  
            else false  
            end 
            from addresses ad 
            where ad.location = :location and ad.hamlet_code = :hamlet_code
            """, nativeQuery = true)
    int existsByLocationAndHamlet(@Param("location") String location, @Param("hamlet_code") String hamletCode);

    @Query(value = """
            select * from addresses ad
            where ad.location = :location and ad.hamlet_code = :hamlet_code
            """, nativeQuery = true)
    Address findByLocationAndHamlet(@Param("location") String location, @Param("hamlet_code") String hamletCode);
}
