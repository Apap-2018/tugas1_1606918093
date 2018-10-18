package com.apap.tugas1.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "instansi")
public class InstansiModel implements Serializable {

	//instansi bigint20 ac pk notnull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private BigInteger id;
	
	//fk dari PegawaiModel
	@OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<PegawaiModel> pegawaiInstansi;
	
	//nama varchar255 notnull
	@NotNull
	@Size(max=255)
	@Column(name= "nama", nullable= false)
	private String nama;
	
	//deskripsi varchar 255 notnull
	@NotNull
	@Size(max=255)
	@Column(name= "deskripsi", nullable= false)
	private String deskripsi;
	
	//fk ke ProvinsiModel
	//id_provinsi bigint20 fk provinsi.id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_provinsi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProvinsiModel provinsi;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public List<PegawaiModel> getPegawaiInstansi() {
		return pegawaiInstansi;
	}

	public void setPegawaiInstansi(List<PegawaiModel> pegawaiInstansi) {
		this.pegawaiInstansi = pegawaiInstansi;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public ProvinsiModel getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(ProvinsiModel provinsi) {
		this.provinsi = provinsi;
	}

	
}
