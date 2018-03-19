package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultasModel {
	public int id;
	public String kodeFakultas;
	public String namaFakultas;
	public int idUniv;
}
