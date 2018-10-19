package com.apap.tugas1.service;

import java.math.BigInteger;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiModelByNip(String nip);
	PegawaiModel getPegawaiTertuaDiInstansi(BigInteger idInstansi);
	PegawaiModel getPegawaiTermudaDiInstansi(BigInteger idInstansi);
	
}
