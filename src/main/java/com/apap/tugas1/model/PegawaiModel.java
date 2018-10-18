package com.apap.tugas1.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "pegawai")
public class PegawaiModel implements Serializable{

	

	//id bigint20 ac pk notnull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private BigInteger id;
	
	//nip varchar255 notnull unique
	@NotNull
	@Size(max=255)
	@Column(name= "NIP", nullable = false, unique = true)
	private String nip;
	
	//nama varchar255 not null
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable = false)
	private String nama;
	
	//tempatlahir varchar255 notnull
	@NotNull
	@Size(max=255)
	@Column(name="tempat_lahir", nullable = false)
	private String tempat_lahir;
	
	//tanggallahir date notnull
	@NotNull
	@Column(name="tanggal_lahir", nullable = false)
	private Date tanggal_lahir;
	
	
	//tahunmasuk varchar255 notnull
	@NotNull
	@Size(max=255)
	@Column(name="tahun_masuk", nullable = false)
	private String tahun_masuk;
	
	//idInstansi BigInt FK to Instansi.id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_instansi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private InstansiModel instansi;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "jabatan_pegawai",
		   joinColumns = {@JoinColumn(name = "id_pegawai")},
		   inverseJoinColumns = {@JoinColumn(name = "id_jabatan")})
	private List<JabatanModel> jabatan;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempat_lahir() {
		return tempat_lahir;
	}

	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}

	public Date getTanggal_lahir() {
		return tanggal_lahir;
	}

	public void setTanggal_lahir(Date tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}

	public String getTahun_masuk() {
		return tahun_masuk;
	}

	public void setTahun_masuk(String tahun_masuk) {
		this.tahun_masuk = tahun_masuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}

	public List<JabatanModel> getJabatan() {
		return jabatan;
	}

	public void setJabatan(List<JabatanModel> jabatan) {
		this.jabatan = jabatan;
	}
}
