package com.koreaIT.develog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int id;
	private int memberId;
	private int articleId;
	private String regDate;
	private String updateDate;
	private String body;
	
//	member join
	private String writerName;
	private String profillImg;
}
