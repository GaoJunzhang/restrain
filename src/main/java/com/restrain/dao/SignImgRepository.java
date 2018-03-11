package com.restrain.dao;

import com.restrain.model.SignImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SignImgRepository extends JpaRepository<SignImg, Long> {

    public List<SignImg> findBySignId(@Param("signid") Long signId);

    @Query(value = "select max(id) from sign_img",nativeQuery = true)
    long maxId();
}
