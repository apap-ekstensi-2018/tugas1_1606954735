package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDatabase implements FakultasService {
	@Autowired
	private FakultasMapper fakultasMapper;
	
	@Override
    public List<FakultasModel> selectAllFakultasbyUniv(int idUniv)
    {
        log.info ("select all fakultas with idUniv {}", idUniv);
        return fakultasMapper.selectAllFakultasbyUniv(idUniv);
    }
	
	@Override
    public String selectNamaFakultasbyId(int idFakultas)
    {
        log.info ("select nama fakultas with id {}", idFakultas);
        return fakultasMapper.selectNamaFakultasbyId(idFakultas);
    }
}
