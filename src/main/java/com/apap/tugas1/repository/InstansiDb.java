package com.apap.tugas1.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

import org.springframework.stereotype.Repository;


public interface InstansiDb extends JpaRepository<InstansiModel, Long>{
	//InstansiModel findByNama(String nama);
	List<InstansiModel> findByProvinsi(ProvinsiModel provinsi);
}
