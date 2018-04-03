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
	@NotNull @NotEmpty
	private String npm;
	@NotNull @NotEmpty
	private String nama;
	@NotNull @NotEmpty
	private String tempatLahir;
	@NotNull
	private Date tanggalLahir;
	@NotNull
	private int jenisKelamin;
	@NotNull @NotEmpty
	private String agama;
	@NotNull @NotEmpty
	private String golDarah;
	@NotNull @NotEmpty
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
