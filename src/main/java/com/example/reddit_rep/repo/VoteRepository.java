package com.example.reddit_rep.repo;

import com.example.reddit_rep.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v where v.user.id = :userId and v.feature.id = :featureId")
    Optional<Vote> findByUserIdAndFeatureId(@Param("featureId") Long featureId, @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v where v.user.id = :userId AND v.feature.id = :featureId")
    void deleteVote(@Param("featureId") Long featureId, @Param("userId") Long userId);
}
