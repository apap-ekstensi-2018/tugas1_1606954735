package com.example.service;

import java.util.List;

import com.example.model.MhsModel;

public interface MhsService {
	MhsModel selectMhs(String npm);
	
	List<MhsModel> selectAllMhs();
	
	void addMhs(MhsModel Mhs);
	
	void deleteMhs(MhsModel Mhs);
	
	void updateMhs(MhsModel Mhs);
	
	String selectLatestSeqNPM(String baseNPM);
	
	int selectJmlMhs(String tahunMasuk, String idProdi);
	
	int selectJmlMhsLulus(String tahunMasuk, String idProdi);
	
	int selectUmurTermuda(String tahunMasuk, String idProdi);
	
	int selectUmurTertua(String tahunMasuk, String idProdi);
	
	List<MhsModel> selectAllMhsbyProdi(String idProdi);
}
