package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserInfo;


/**
 * ユーザ情報テーブルDAO
 */

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
