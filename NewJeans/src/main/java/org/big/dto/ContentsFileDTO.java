package org.big.dto;

import lombok.Data;

@Data
public class ContentsFileDTO {
	private int fileIdx;
	private String cCode;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
}
