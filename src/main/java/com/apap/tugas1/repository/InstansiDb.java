package com.apap.tugas1.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import com.apap.tugas1.model.InstansiModel;
import org.springframework.stereotype.Repository;


public interface InstansiDb extends JpaRepository<InstansiModel, BigInteger>{
	//InstansiModel findByNama(String nama);

}
