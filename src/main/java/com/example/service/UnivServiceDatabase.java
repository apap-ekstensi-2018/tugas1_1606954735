package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UnivMapper;
import com.example.model.UnivModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UnivServiceDatabase implements UnivService {
	@Autowired
	private UnivMapper univMapper;
	
	@Override
    public List<UnivModel> selectAllUniv()
    {
        log.info ("select all universitas");
        return univMapper.selectAllUniv();
    }
	
	@Override
    public String selectKodeUnivbyId(int idUniv)
    {
        log.info ("select kode univ with id {}", idUniv);
        return univMapper.selectKodeUnivbyId(idUniv);
    }
	
	@Override
    public String selectNamaUnivbyId(int idUniv)
    {
        log.info ("select nama univ with id {}", idUniv);
        return univMapper.selectNamaUnivbyId(idUniv);
    }
}
