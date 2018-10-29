package com.apap.tugas1.service;

import java.util.Optional;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

public interface PegawaiService {
	PegawaiModel getPegawaiModelByNip(String nip);
	PegawaiModel getPegawaiTertuaDiInstansi(long id);
	PegawaiModel getPegawaiTermudaDiInstansi(long idInstansi);
	void updatePegawai(PegawaiModel pegawai, long id);
	PegawaiDb getPegawaiDb();
	PegawaiModel getPegawaiDetailById(long id);
	PegawaiModel addPegawai(PegawaiModel pegawai);
	void generateNip(PegawaiModel pegawai);
	
}
