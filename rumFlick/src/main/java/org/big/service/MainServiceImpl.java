package org.big.service;

import org.big.dto.ContentsDTO;
import org.big.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainMapper mainMapper;
	
	
}
