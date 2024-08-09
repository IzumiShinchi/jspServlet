package org.big.dto;

import java.util.List;

import lombok.Data;

@Data
public class ContentsDTO {
	private String cCode;
	private String cCategory;
	private String cTitle;
	private String cDirector;
	private String cGenre;
	private String cNation;
	private String cRate;
	private String cSynopsis;
	private ContentsFileDTO fileInfo;
}
