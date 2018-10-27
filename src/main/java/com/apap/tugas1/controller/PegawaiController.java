package com.apap.tugas1.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	private BigInteger transformId;

	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired 
	private ProvinsiService provinsiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("title", "SIPEG");
		return "home";
	}
	
	@RequestMapping(value = "/pegawai/view", method = RequestMethod.GET)
	private String lihatPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiModelByNip(nip);
		System.out.println(pegawai.getInstansi().getNama()+ "hehehhehehhehe");
		List<JabatanModel> jabatanPegawai = pegawai.getJabatan();
		int gaji = (int)(jabatanPegawai.get(0).getGaji_pokok() + 
				(jabatanPegawai.get(0).getGaji_pokok()*pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100));
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		model.addAttribute("gajiPegawai", gaji);
		model.addAttribute("datapegawai", pegawai);
		model.addAttribute("title", "SIPEG");
		return "pegawai-view";
		
	}
	
	@RequestMapping(value="/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String viewTertuaTermuda(@RequestParam (value="idInstansi") BigInteger id, Model model) {
		PegawaiModel tua = pegawaiService.getPegawaiTertuaDiInstansi(id);
		PegawaiModel muda = pegawaiService.getPegawaiTermudaDiInstansi(id);
		model.addAttribute("tertua", tua);
		model.addAttribute("termuda", muda);
		model.addAttribute("title", "Old-Young");
		return "pegawai-tuamuda";
	}
	
	@RequestMapping(value = "pegawai/ubah", method = RequestMethod.GET)
	private String updatePegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiModelByNip(nip);
		transformId = pegawai.getId();
		model.addAttribute("datapegawai", pegawai);
		model.addAttribute("pegawaiByProvinsi", pegawai.getInstansi().getProvinsi().getNama());
		model.addAttribute("listjabatan", pegawai.getJabatan());
		model.addAttribute("datainstansi", pegawai.getInstansi().getNama());
		return "pegawai-update";
	}
	
	@RequestMapping(value = "pegawai/ubah", method = RequestMethod.POST)
	private String updatePegawaiSubmit(@RequestParam(value="id") BigInteger id, Model model, @ModelAttribute Optional<PegawaiModel> pegawai) {
		pegawaiService.updatePegawai(id, pegawai.get());
		model.addAttribute("id", id);
		
		return "pegawai-update";
	}
	
}
