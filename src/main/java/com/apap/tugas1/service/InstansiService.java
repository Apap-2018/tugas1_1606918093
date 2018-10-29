package com.apap.tugas1.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;

public interface InstansiService {

	InstansiDb getInstansiDb();

	List<InstansiModel> getListInstansi();

}
