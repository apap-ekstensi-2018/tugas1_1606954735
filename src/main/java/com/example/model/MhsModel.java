package com.example.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull @NotEmpty
	private String tahunMasuk;
	@NotNull @NotEmpty
	private String jalurMasuk;
	@NotNull
	private int idProdi;
	private String namaProdi;
	@NotNull
	private int idFakultas;
	private String namaFakultas;
	@NotNull
	private int idUniv;
	private String namaUniv;
}
