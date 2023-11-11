package com.spring.astabanksecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="notice_details")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id")
    private int noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Column(name = "notice_begin_date")
    private Date noticeBeginDate;

    @Column(name = "notice_end_date")
    private Date noticeEndDate;

    @Column(name = "create_dt")
    private Date createDt;

    @Column(name = "update_dt")
    private Date updateDt;
}
