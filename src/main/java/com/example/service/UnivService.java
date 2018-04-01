package com.example.service;

import java.util.List;

import com.example.model.UnivModel;

public interface UnivService {
	List<UnivModel> selectAllUniv();
	
	String selectKodeUnivbyId(int idUniv);
	
	String selectNamaUnivbyId(int idUniv);
}
