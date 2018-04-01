package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProdiMapper;
import com.example.model.ProdiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdiServiceDatabase implements ProdiService {
	@Autowired
	private ProdiMapper prodiMapper;
	
	@Override
    public List<ProdiModel> selectAllProdibyFakultas(int idFakultas)
    {
        log.info ("select all prodi with idFakultas {}", idFakultas);
        return prodiMapper.selectAllProdibyFakultas(idFakultas);
    }
	
	@Override
    public String selectKodeProdibyId(int idProdi)
    {
        log.info ("select kode prodi with id {}", idProdi);
        return prodiMapper.selectKodeProdibyId(idProdi);
    }
	
	@Override
    public String selectNamaProdibyId(int idProdi)
    {
        log.info ("select nama prodi with id {}", idProdi);
        return prodiMapper.selectNamaProdibyId(idProdi);
    }
}
