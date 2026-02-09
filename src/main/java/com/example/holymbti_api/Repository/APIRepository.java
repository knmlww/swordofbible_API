package com.example.holymbti_api.Repository;


import com.example.holymbti_api.Domain.MbtiIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface APIRepository extends JpaRepository<MbtiIssue, Long> {

    @Query("SELECT count(1) FROM MbtiIssue")
    Long countBy();

    @Query("SELECT count(1) FROM MbtiIssue WHERE mbtiResult = :param")
    Long countByMbtiResult(@Param("param") String param);

    @Query("SELECT MAX(issueNum) FROM MbtiIssue")
    Long selectMaxIssueNum();
}
