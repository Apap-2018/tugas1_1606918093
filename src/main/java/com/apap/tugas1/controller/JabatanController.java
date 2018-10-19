package com.apap.tugas1.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;


@Controller
public class JabatanController {
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	private String jabatanViewAll(Model model){
		List<JabatanModel> datajabatan = jabatanService.getJabatanDb().findAll();
		model.addAttribute("datajabatan", datajabatan);
		model.addAttribute("title", "Lihat Daftar Jabatan");
		return "jabatan-viewall";
	}
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewDataByJabatan(@RequestParam(value="idJabatan") BigInteger id, Model model) {
		JabatanModel datajabatan = jabatanService.getJabatanById(id);
		model.addAttribute("datajabatan", datajabatan);
		model.addAttribute("title", "Lihat Data terkait Jabatan");
		return "jabatan-view";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		model.addAttribute("title", "Tambah Jabatan/Posisi");
		return "jabatan-add";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatan(JabatanModel jabatan,Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("title", "Jabatan");
		return "infosuccess";
	}
	
	@RequestMapping(value="/jabatan/ubah", method = RequestMethod.GET)
	private String update(@RequestParam(value="idJabatan") BigInteger id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(id);
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("title", "Update Jabatan");
		return "jabatan-update";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateJabatan(@RequestParam(value="idJabatan") BigInteger id, JabatanModel jabatan, Model model) {
		jabatan.setId(id);
		jabatanService.updateJabatan(jabatan);
		model.addAttribute("updatedJabatan", jabatan);
		model.addAttribute("title", "Update Jabatan");
		return "infosuccess";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	private String deleteJabatan(@RequestParam(value="idJabatan") BigInteger id, Model model) {
		jabatanService.deleteJabatan(id);
		model.addAttribute("title", "Delete Jabatan");
		model.addAttribute("title", "Hapus Jabatan");
		return "infosuccess";
	}
	
	

}
