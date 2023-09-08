package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmhai.cititzenV.entity.Declaration;

public interface DeclareRepo extends JpaRepository<Declaration, Integer> {
    
}
