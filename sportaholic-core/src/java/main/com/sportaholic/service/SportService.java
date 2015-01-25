package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Sport;

public interface SportService {

	public Sport get(int id) throws Exception;
	
	public List<Sport> getAll() throws Exception;
	
}
