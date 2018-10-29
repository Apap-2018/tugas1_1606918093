package com.apap.tugas1.service;
import java.math.BigInteger;
import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

public interface JabatanService {
	JabatanDb getJabatanDb();
	JabatanModel getJabatanById(long id);
	void addJabatan(JabatanModel jabatan);
	void updateJabatan(JabatanModel newJabatan);
	void deleteJabatan(long id);
	List<JabatanModel> getListJabatan();
	List<JabatanModel> findAllJabatan();
}
