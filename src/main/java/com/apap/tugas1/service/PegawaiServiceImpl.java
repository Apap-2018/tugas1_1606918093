package com.apap.tugas1.service;

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
	public PegawaiModel getPegawaiTertuaDiInstansi(long idInstansi) {
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
	public PegawaiModel getPegawaiTermudaDiInstansi(long idInstansi) {
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
	public PegawaiDb getPegawaiDb() {
		// TODO Auto-generated method stub
		return pegawaiDb;
	}

	@Override
	public PegawaiModel getPegawaiDetailById(long id) {
		// TODO Auto-generated method stub
		return pegawaiDb.findById(id);
	}

	@Override
	public void updatePegawai(PegawaiModel pegawai, long id) {
		// TODO Auto-generated method stub
		PegawaiModel updatePegawai = pegawaiDb.getOne(id);
		updatePegawai.setNama(pegawai.getNama());
		updatePegawai.setInstansi(pegawai.getInstansi());
		updatePegawai.setTahun_masuk(pegawai.getTahun_masuk());
		updatePegawai.setTempat_lahir(pegawai.getTempat_lahir());
		updatePegawai.setTanggal_lahir(pegawai.getTanggal_lahir());
		pegawaiDb.save(updatePegawai);
	}

	@Override
	public PegawaiModel addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		return pegawaiDb.save(pegawai);
	}

	@Override
	public void generateNip(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		String nip = "";
		nip += pegawai.getInstansi().getId();
		String[] tanggal_lahir = pegawai.getTanggal_lahir().toString().split("-");
		String strTanggal = tanggal_lahir[2] + tanggal_lahir[1] + tanggal_lahir[0].substring(2, 4);
				
		nip += strTanggal;
		//System.out.println(nip+=strTanggal);
		nip += pegawai.getTahun_masuk();

		int i = 1;
		for (PegawaiModel pegawaiInstansi:pegawai.getInstansi().getPegawaiInstansi()) {
			if (pegawaiInstansi.getTahun_masuk().equals(pegawai.getTahun_masuk()) && pegawaiInstansi.getTanggal_lahir().equals(pegawai.getTanggal_lahir())) {
				i += 1;
			}	
		}
		nip += "0" + i;
		//System.out.println(nip += "0" + i);
		pegawai.setNip(nip);
	}


	

	



	

	
	

}
