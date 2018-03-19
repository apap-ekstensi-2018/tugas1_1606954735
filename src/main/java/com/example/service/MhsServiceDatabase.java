package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MhsMapper;
import com.example.model.MhsModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MhsServiceDatabase implements MhsService {
	@Autowired
	private MhsMapper mhsMapper;
	
	@Override
	public MhsModel selectMhs(String npm) {
		log.info("select mahasiswa with npm {}", npm);
		return mhsMapper.selectMhs(npm);
	}
	
	@Override
    public List<MhsModel> selectAllMhs()
    {
        log.info ("select all students");
        return mhsMapper.selectAllMhs();
    }

    @Override
    public void addMhs(MhsModel student)
    {
    	log.info("student " + student.getNpm() + " added");
    	mhsMapper.addMhs (student);
    }

    @Override
    public void deleteMhs(MhsModel student)
    {
    	log.info("student " + student.getNpm() + " deleted");
    	mhsMapper.deleteMhs(student);
    }

    @Override
    public void updateMhs(MhsModel student) {
    	log.info("student " + student.getNpm() + " updated");
    	mhsMapper.updateMhs(student);
    }
}
