package com.koreaIT.develog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String name;
}
