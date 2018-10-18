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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="jabatan")
public class JabatanModel implements Serializable{

	//idjabatan bigint20 ac pk notnull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private BigInteger id;
	
	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name="deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name="gaji_pokok", nullable = false)
	private double gaji_pokok;
	
	//@ManyToMany(fetch = FetchType.LAZY,
     //       cascade = {
     //           CascadeType.PERSIST,
      //          CascadeType.MERGE
      //      },
      //      mappedBy = "jabatan")
    //private List<PegawaiModel> post;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "jabatan_pegawai",
	        joinColumns = @JoinColumn(name = "id_jabatan", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "id_pegawai", referencedColumnName = "id"))
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<PegawaiModel> listPegawai;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public double getGaji_pokok() {
		return gaji_pokok;
	}

	public void setGaji_pokok(double gaji_pokok) {
		this.gaji_pokok = gaji_pokok;
	}

	public List<PegawaiModel> getListPegawai() {
		return listPegawai;
	}

	public void setPost(List<PegawaiModel> post) {
		this.listPegawai = post;
	}
	
}
