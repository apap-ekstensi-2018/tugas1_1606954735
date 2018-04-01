package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.UnivModel;

@Mapper
public interface UnivMapper {
	@Select("select id, kode_univ as kodeUniv, nama_univ as namaUniv from universitas")
    List<UnivModel> selectAllUniv();
	
	@Select("select kode_univ as kodeUniv from universitas where id = #{idUniv}")
    String selectKodeUnivbyId(@Param("idUniv") int idUniv);
	
	@Select("select nama_univ as namaUniv from universitas where id = #{idUniv}")
    String selectNamaUnivbyId(@Param("idUniv") int idUniv);
}
