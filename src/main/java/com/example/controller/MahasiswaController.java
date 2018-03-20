package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.MhsModel;
import com.example.service.MhsService;

@Controller
public class MahasiswaController {
	@Autowired
	MhsService mhsDAO;
	
	@RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Home");
        return "index";
    }
	
	@RequestMapping("/mahasiswa")
	public String view(Model model,
			@RequestParam(value = "npm", required = false) String npm) {
		MhsModel mhs = mhsDAO.selectMhs(npm);
		model.addAttribute("pageTitle", "Lihat Data Mahasiswa");
		
		if(mhs != null) {
			model.addAttribute("mhs", mhs);
			return "view";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}
	
	@RequestMapping("/mahasiswa/tambah")
	public String add (Model model) {
		model.addAttribute("title", "Menambahkan Data Mahasiswa");
		return "form-add";
	}
	
	@PostMapping("/mahasiswa/tambah/submit")
    public String updateFormSubmit(Model model, @ModelAttribute MhsModel mhs) {
		mhs.setNpm(generateNPM(mhs.getTahunMasuk(), mhs.getNamaUniv(), mhs.getNamaProdi(), mhs.getJalurMasuk()));
		mhsDAO.addMhs(mhs);
        return "success-add";
    }
	
	public String generateNPM(String tahunMasuk, String kodeUniv, String kodeProdi, String jalurMasuk) {
		String npm = "";
		return npm;
	}
}
