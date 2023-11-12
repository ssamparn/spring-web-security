package com.spring.astabanksecurity.repository;

import com.spring.astabanksecurity.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    @Query(value = "from Notice n where CURDATE() BETWEEN noticeBeginDate and noticeEndDate")
    List<Notice> findAllActiveNotices();
}
