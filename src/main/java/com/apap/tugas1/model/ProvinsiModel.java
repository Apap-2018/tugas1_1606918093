package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "provinsi")
public class ProvinsiModel implements Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<InstansiModel> getInstansiProvinsi() {
		return instansiProvinsi;
	}

	public void setInstansiProvinsi(List<InstansiModel> instansiProvinsi) {
		this.instansiProvinsi = instansiProvinsi;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getPresentase_tunjangan() {
		return presentase_tunjangan;
	}

	public void setPresentase_tunjangan(double presentase_tunjangan) {
		this.presentase_tunjangan = presentase_tunjangan;
	}

	//provinsi int10 ac pk notnull
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//fk dari InstansiModel
	@OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<InstansiModel> instansiProvinsi;
	
	//nama varchar255 notnull
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable = false)
	private String nama;
	
	//presentase_tunjangan
	@NotNull
	@Column(name="presentase_tunjangan", nullable = false)
	private double presentase_tunjangan;
	
	
	
	
}
