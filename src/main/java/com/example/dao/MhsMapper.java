package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.MhsModel;

@Mapper
public interface MhsMapper {
	@Select("select mhs.npm"
			+ ", mhs.nama"
			+ ", mhs.tempat_lahir as tempatLahir"
			+ ", mhs.tanggal_lahir as tanggalLahir"
			+ ", mhs.jenis_kelamin as jenisKelamin"
			+ ", mhs.agama"
			+ ", mhs.golongan_darah as golDarah"
			+ ", mhs.status"
			+ ", mhs.tahun_masuk as tahunMasuk"
			+ ", mhs.jalur_masuk as jalurMasuk"
			+ ", prodi.nama_prodi as namaProdi"
			+ ", fakul.nama_fakultas as namaFakultas"
			+ ", univ.nama_univ as namaUniv "
			+ "from mahasiswa mhs inner join program_studi prodi on prodi.id = mhs.id_prodi "
			+ "inner join fakultas fakul on fakul.id = prodi.id_fakultas "
			+ "inner join universitas univ on univ.id = fakul.id_univ "
			+ "where mhs.npm = #{npm}")
	MhsModel selectMhs(@Param("npm") String npm);
	
	@Select("select npm, nama from mahasiswa")
    List<MhsModel> selectAllMhs();

    @Insert("INSERT INTO mahasiswa (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addMhs(MhsModel mhs);
    
    @Delete("DELETE FROM mahasiswa WHERE npm = #{npm}")
    void deleteMhs(MhsModel mhs);
    
    @Update("UPDATE mahasiswa SET name = #{name} WHERE npm = #{npm}")
    void updateMhs(MhsModel mhs);
}
