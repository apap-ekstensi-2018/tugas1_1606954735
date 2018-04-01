package com.example.service;

import java.util.List;

import com.example.model.ProdiModel;

public interface ProdiService {
	List<ProdiModel> selectAllProdibyFakultas(int idFakultas);
	
	String selectKodeProdibyId(int idProdi);
	
	String selectNamaProdibyId(int idProdi);
}
