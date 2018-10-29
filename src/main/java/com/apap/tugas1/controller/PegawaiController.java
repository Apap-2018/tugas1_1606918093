package com.apap.tugas1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	private long transformId;

	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired 
	private ProvinsiService provinsiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired 
	private JabatanService jabatanService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("title", "SIPEG");
		return "home";
	}
	
	@RequestMapping(value = "/pegawai/view", method = RequestMethod.GET)
	private String lihatPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiModelByNip(nip);
		//System.out.println(pegawai.getInstansi().getNama()+ "hehehhehehhehe");
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
	private String viewTertuaTermuda(@RequestParam (value="idInstansi") long id, Model model) {
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
		//transformId = pegawai.getId();
		model.addAttribute("datapegawai", pegawai);
		model.addAttribute("pegawaiByProvinsi", ((ProvinsiService) provinsiService).getProvinsiDb().findAll());
		model.addAttribute("listjabatan", ((JabatanService) jabatanService).getJabatanDb().findAll());
		model.addAttribute("datainstansi", ((InstansiService) instansiService).getInstansiDb().findAll());
		return "pegawai-update";
	}
	
	@RequestMapping(value = "pegawai/ubah", params = {"submit"}, method = RequestMethod.POST)
	private String updatePegawaiSubmit(Model model, @ModelAttribute PegawaiModel pegawai) {
		//pegawai.setId(transformId);
		pegawaiService.updatePegawai(pegawai, pegawai.getId());
		pegawai.setNip(pegawaiService.getPegawaiDb().getOne(transformId).getNip());
		model.addAttribute("pegawai", pegawai);
		return "infosuccess";
	}
	
	@RequestMapping("/pegawai/cari")
	private String cari(Model model) {
		model.addAttribute("pegawaiByProvinsi", ((ProvinsiService) provinsiService).getProvinsiDb().findAll());
		model.addAttribute("listjabatan", ((JabatanService) jabatanService).getJabatanDb().findAll());
		model.addAttribute("datainstansi", ((InstansiService) instansiService).getInstansiDb().findAll());
		model.addAttribute("title", "Home");
		return "pegawai-cari";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		model.addAttribute("pegawaiByProvinsi", ((ProvinsiService) provinsiService).getProvinsiDb().findAll());
		model.addAttribute("listjabatan", ((JabatanService) jabatanService).getJabatanDb().findAll());
		model.addAttribute("datainstansi", ((InstansiService) instansiService).getInstansiDb().findAll());
		
		PegawaiModel newPegawai = new PegawaiModel();
		model.addAttribute("pegawai", newPegawai);
		model.addAttribute("title", "Tambah Pegawai");
		
		return "pegawai-add";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.generateNip(pegawai);
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("nip", pegawai.getNip());
		return "infosuccess-add";
	} 
	
	
	
}
