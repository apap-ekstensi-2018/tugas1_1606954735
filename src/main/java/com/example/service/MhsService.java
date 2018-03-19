package com.example.service;

import java.util.List;

import com.example.model.MhsModel;

public interface MhsService {
	MhsModel selectMhs(String npm);
	
	List<MhsModel> selectAllMhs();
	
	void addMhs(MhsModel Mhs);
	
	void deleteMhs(MhsModel Mhs);
	
	void updateMhs(MhsModel Mhs);
}
