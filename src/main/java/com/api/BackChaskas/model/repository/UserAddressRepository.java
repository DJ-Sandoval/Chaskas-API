package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends BaseRepository<UserAddress, Long> {

    List<UserAddress> findByUserId(Long userId);
    List<UserAddress> findByUserIdAndIsDefaultTrue(Long userId);
}
