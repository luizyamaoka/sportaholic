package com.sportaholic.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ReadingDao;
import com.sportaholic.model.Reading;
import com.sportaholic.service.ReadingService;

@Component
public class ReadingServiceImpl implements ReadingService {

	private ReadingDao readingDao;
	
	@Autowired
	public ReadingServiceImpl(ReadingDao readingDao) {
		this.readingDao = readingDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(Reading reading) {
		try {
			reading.setCreatedAt(Calendar.getInstance().getTime());
			this.readingDao.create(reading);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
