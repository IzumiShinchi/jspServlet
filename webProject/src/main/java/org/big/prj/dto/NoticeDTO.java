package org.big.prj.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDTO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date createdAt;
}
