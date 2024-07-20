package org.big.dto;

import java.util.List;

import lombok.Data;

@Data
public class ContentsDTO {
	private String contentsCode;
	private String contentsCategory;
	private String contentsTitle;
	private String contentsDirector;
	private String contentsGenre;
	private String contentsNation;
	private String contentsRate;
	private String contentsSynopsis;
	private List<ContentsFileDTO> fileList;
}
