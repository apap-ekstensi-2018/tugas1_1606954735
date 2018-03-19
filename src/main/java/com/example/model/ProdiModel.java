package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdiModel {
	public int id;
	public String kodeProdi;
	public String namaProdi;
	public int idFakultas;
}
