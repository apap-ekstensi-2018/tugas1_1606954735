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
	@Select("select mhs.id"
			+ ", mhs.npm"
			+ ", mhs.nama"
			+ ", mhs.tempat_lahir as tempatLahir"
			+ ", mhs.tanggal_lahir as tanggalLahir"
			+ ", mhs.jenis_kelamin as jenisKelamin"
			+ ", mhs.agama"
			+ ", mhs.golongan_darah as golDarah"
			+ ", mhs.status"
			+ ", mhs.tahun_masuk as tahunMasuk"
			+ ", mhs.jalur_masuk as jalurMasuk"
			+ ", prodi.id as idProdi"
			+ ", prodi.nama_prodi as namaProdi"
			+ ", fakul.id as idFakultas"
			+ ", fakul.nama_fakultas as namaFakultas"
			+ ", univ.id as idUniv"
			+ ", univ.nama_univ as namaUniv "
			+ "from mahasiswa mhs inner join program_studi prodi on prodi.id = mhs.id_prodi "
			+ "inner join fakultas fakul on fakul.id = prodi.id_fakultas "
			+ "inner join universitas univ on univ.id = fakul.id_univ "
			+ "where mhs.npm = #{npm}")
	MhsModel selectMhs(@Param("npm") String npm);
	
	@Select("select mhs.npm, mhs.nama, mhs.id_prodi as idProdi, prodi.nama_prodi as namaProdi"
			+ ", mhs.tahun_masuk as tahunMasuk, mhs.jalur_masuk as jalurMasuk"
			+ " from mahasiswa mhs inner join program_studi prodi on prodi.id = mhs.id_prodi")
    List<MhsModel> selectAllMhs();
	
	@Select("select mhs.npm, mhs.nama, mhs.id_prodi as idProdi, prodi.nama_prodi as namaProdi"
			+ ", mhs.tahun_masuk as tahunMasuk, mhs.jalur_masuk as jalurMasuk"
			+ " from mahasiswa mhs inner join program_studi prodi on prodi.id = mhs.id_prodi"
			+ " where mhs.id_prodi = #{idProdi}")
    List<MhsModel> selectAllMhsbyProdi(@Param("idProdi") String idProdi);

    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin"
    		+ ", agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) "
    		+ "VALUES (#{npm}, #{nama}, #{tempatLahir}, #{tanggalLahir}, #{jenisKelamin}, #{agama}"
    		+ ", #{golDarah}, #{status}, #{tahunMasuk}, #{jalurMasuk}, #{idProdi})")
    void addMhs(MhsModel mhs);
    
    @Delete("DELETE FROM mahasiswa WHERE id = #{id} and npm = #{npm}")
    void deleteMhs(MhsModel mhs);
    
    @Update("UPDATE mahasiswa SET npm = #{npm}, nama = #{nama}, tempat_lahir = #{tempatLahir}"
    		+ ", tanggal_lahir = #{tanggalLahir}, jenis_kelamin = #{jenisKelamin}, agama = #{agama}"
    		+ ", golongan_darah = #{golDarah}, status = #{status}, tahun_masuk = #{tahunMasuk}, jalur_masuk = #{jalurMasuk}"
    		+ ", id_prodi = #{idProdi} WHERE id = #{id}")
    void updateMhs(MhsModel mhs);
    
    @Select("select LPAD(COALESCE(max(convert(right(npm,3), unsigned integer)), 0) + 1, 3, '0') from mahasiswa where npm like #{baseNPM}")
    String selectNextSeqNPM(@Param("baseNPM") String baseNPM);
    
    @Select("select count(1) as 'jmlMahasiswa' from mahasiswa where tahun_masuk = #{tahunMasuk} AND id_prodi = #{idProdi}")
    int selectJmlMhs(@Param("tahunMasuk") String tahunMasuk, @Param("idProdi") String idProdi);
    
    @Select("select count(case when status = 'lulus' then 1 end) as 'jmlMahasiswaLulus' from mahasiswa where tahun_masuk = #{tahunMasuk} AND id_prodi = #{idProdi}")
    int selectJmlMhsLulus(@Param("tahunMasuk") String tahunMasuk, @Param("idProdi") String idProdi);
    
    @Select("select coalesce(year(curdate()) - year(max(tanggal_lahir)), 0) as 'umurTermuda' from mahasiswa where tahun_masuk = #{tahunMasuk} AND id_prodi = #{idProdi}")
    int selectUmurTermuda(@Param("tahunMasuk") String tahunMasuk, @Param("idProdi") String idProdi);
    
    @Select("select coalesce(year(curdate()) - year(min(tanggal_lahir)), 0) as 'umurTermuda' from mahasiswa where tahun_masuk = #{tahunMasuk} AND id_prodi = #{idProdi}")
    int selectUmurTertua(@Param("tahunMasuk") String tahunMasuk, @Param("idProdi") String idProdi);
}
