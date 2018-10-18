package com.apap.tugas1.service;

import java.math.BigInteger;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional

public class JabatanServiceImpl implements JabatanService {
	@Autowired
	JabatanDb jabatanDb;

	@Override
	public JabatanDb getJabatanDb() {
		return jabatanDb;
	}

	@Override
	public JabatanModel getJabatanById(BigInteger id) {
		// TODO Auto-generated method stub
		return jabatanDb.getOne(id);
	}

	@Override
	public void addJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.save(jabatan);
	}

	@Override
	public void updateJabatan(JabatanModel newJabatan) {
		// TODO Auto-generated method stub
		JabatanModel updateJabatan = jabatanDb.getOne(newJabatan.getId());
		updateJabatan.setNama(newJabatan.getNama());
		updateJabatan.setDeskripsi(newJabatan.getDeskripsi());
		updateJabatan.setGaji_pokok(newJabatan.getGaji_pokok());
		jabatanDb.save(newJabatan);
	}

	

}
