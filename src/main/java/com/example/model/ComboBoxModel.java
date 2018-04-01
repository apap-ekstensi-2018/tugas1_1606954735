package com.example.model;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboBoxModel {
	public String value;
	public String text;
	
	public static final List<ComboBoxModel> getJalurMasuk(){
		List<ComboBoxModel> listJalurMasuk = Arrays.asList(
                new ComboBoxModel("53", "Undangan Olimpiade"),
                new ComboBoxModel("54", "Undangan Reguler / SNMPTN"),
                new ComboBoxModel("55", "Undangan Paralel / PPKB"),
                new ComboBoxModel("57", "Ujian Tulis Bersama / SBMPTN"),
                new ComboBoxModel("62", "Ujian Tulis Mandiri")
            );
		return listJalurMasuk;
	}
	
	public static final List<ComboBoxModel> getJenisKelamin(){
		List<ComboBoxModel> listJenisKelamin = Arrays.asList(
                new ComboBoxModel("0", "Perempuan"),
                new ComboBoxModel("1", "Laki-laki")
            );
		return listJenisKelamin;
	}
}
