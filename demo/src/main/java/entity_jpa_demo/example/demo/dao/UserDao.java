package entity_jpa_demo.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity_jpa_demo.example.demo.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long> {
UserEntity findByUsername(String username);
@SuppressWarnings("unchecked")
UserEntity save(UserEntity userentity);
List<UserEntity> findByAddressState(String state);
}
