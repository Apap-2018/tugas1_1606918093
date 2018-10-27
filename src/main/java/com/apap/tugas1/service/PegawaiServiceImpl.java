package com.apap.tugas1.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.InstansiDb;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDb pegawaiDb;
	@Autowired
	private InstansiDb instansiDb;
	
	@Override
	public PegawaiModel getPegawaiModelByNip(String nip) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public PegawaiModel getPegawaiTertuaDiInstansi(BigInteger idInstansi) {
		// TODO Auto-generated method stub
		List<PegawaiModel> pegawaiByInstansi = instansiDb.getOne(idInstansi).getPegawaiInstansi();
		PegawaiModel tua = pegawaiByInstansi.get(0);
		for (int i=1; i<pegawaiByInstansi.size(); i++) {
			if (pegawaiByInstansi.get(i).getTanggal_lahir().before(tua.getTanggal_lahir())) {
				tua = pegawaiByInstansi.get(i);
			}
		}
		return tua;
	}

	@Override
	public PegawaiModel getPegawaiTermudaDiInstansi(BigInteger idInstansi) {
		// TODO Auto-generated method stub
		List<PegawaiModel> pegawaiByInstansi = instansiDb.getOne(idInstansi).getPegawaiInstansi();
		PegawaiModel muda = pegawaiByInstansi.get(0);		
		for (int i=1; i<pegawaiByInstansi.size(); i++) {
			if (pegawaiByInstansi.get(i).getTanggal_lahir().after(muda.getTanggal_lahir())) {
				muda = pegawaiByInstansi.get(i);
			}
		}
		return muda;
	}

	

	@Override
	public void updatePegawai(BigInteger id, PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		PegawaiModel updatePegawai = pegawaiDb.getOne(id);
		updatePegawai.setNama(pegawai.getNama());
		updatePegawai.setInstansi(pegawai.getInstansi());
		updatePegawai.setTahun_masuk(pegawai.getTahun_masuk());
		updatePegawai.setTempat_lahir(pegawai.getTempat_lahir());
		updatePegawai.setTanggal_lahir(pegawai.getTanggal_lahir());
		pegawaiDb.save(updatePegawai);
		
	}

	



	

	
	

}
