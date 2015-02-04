package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.SportDto;

public interface AdmSportService {

	public List<String> create(SportDto sportDto) throws Exception;
	
	public List<String> update(SportDto sportDto) throws Exception;
	
}
