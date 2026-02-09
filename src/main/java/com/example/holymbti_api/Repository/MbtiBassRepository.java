package com.example.holymbti_api.Repository;

import com.example.holymbti_api.Domain.MBTI_BASS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiBassRepository extends JpaRepository<MBTI_BASS, Long> {

    MBTI_BASS findByTypeName(String typeName);

    MBTI_BASS findByTypeDtlName(String imageName);
}
