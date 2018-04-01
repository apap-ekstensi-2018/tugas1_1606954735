package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.ProdiModel;

@Mapper
public interface ProdiMapper {
	@Select("select id, kode_prodi as kodeProdi, nama_prodi as namaProdi from program_studi where id_fakultas = #{idFakultas}")
    List<ProdiModel> selectAllProdibyFakultas(@Param("idFakultas") int idFakultas);
	
	@Select("select kode_prodi as kodeProdi from program_studi where id = #{idProdi}")
    String selectKodeProdibyId(@Param("idProdi") int idProdi);
	
	@Select("select nama_prodi as namaProdi from program_studi where id = #{idProdi}")
    String selectNamaProdibyId(@Param("idProdi") int idProdi);
}
