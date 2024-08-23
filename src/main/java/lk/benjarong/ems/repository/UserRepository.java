package lk.benjarong.ems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.benjarong.ems.entity.UserEntity;
import lk.benjarong.ems.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT p FROM UserEntity p WHERE p.role =:role")
    List<UserEntity> findAdminsByRole(@Param("role") UserRole role);

    @Query("SELECT q FROM UserEntity q WHERE q.role =:role")
    List<UserEntity> findSuperAdminsByRole(@Param("role") UserRole role);

}
