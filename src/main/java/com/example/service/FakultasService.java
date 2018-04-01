package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;

public interface FakultasService {
	List<FakultasModel> selectAllFakultasbyUniv(int idUniv);
	
	String selectNamaFakultasbyId(int idFakultas);
}
