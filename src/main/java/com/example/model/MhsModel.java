package com.example.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MhsModel {
	private String id;
	private String npm;
	private String nama;
	private String tempatLahir;
	private Date tanggalLahir;
	private int jenisKelamin; 
	private String agama;
	private String golDarah;
	private String status;
	private String tahunMasuk;
	private String jalurMasuk;
	private int idProdi;
	private String namaProdi;
	private int idFakultas;
	private String namaFakultas;
	private int idUniv;
	private String namaUniv;
}
