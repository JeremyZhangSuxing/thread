package com.spring.cloud.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgLog {
    private Long id;

    private String fromUserId;

    private String toUserId;

    private String clientType;

    private String msgType;

    private String content;

    private Byte sendFrom;

    private Byte isDeleted;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Integer recordVersion;

}