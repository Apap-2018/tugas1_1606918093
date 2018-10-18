package com.apap.tugas1.service;
import java.math.BigInteger;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

public interface JabatanService {
	JabatanDb getJabatanDb();
	JabatanModel getJabatanById(BigInteger id);
	void addJabatan(JabatanModel jabatan);
	void updateJabatan(JabatanModel newJabatan);
}
