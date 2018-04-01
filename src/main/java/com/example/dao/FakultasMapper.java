package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;

@Mapper
public interface FakultasMapper {
	@Select("select id, kode_fakultas as kodeFakultas, nama_fakultas as namaFakultas from fakultas where id_univ = #{idUniv}")
    List<FakultasModel> selectAllFakultasbyUniv(@Param("idUniv") int idUniv);
	
	@Select("select nama_fakultas as namaFakultas from fakultas where id = #{idFakultas}")
    String selectNamaFakultasbyId(@Param("idFakultas") int idFakultas);
}
