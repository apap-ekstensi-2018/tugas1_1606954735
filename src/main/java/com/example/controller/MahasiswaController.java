package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.ComboBoxModel;
import com.example.model.FakultasModel;
import com.example.model.MhsModel;
import com.example.model.ProdiModel;
import com.example.model.UnivModel;
import com.example.service.FakultasService;
import com.example.service.MhsService;
import com.example.service.ProdiService;
import com.example.service.UnivService;

@Controller
public class MahasiswaController {
	@Autowired
	MhsService mhsDAO;
	
	@Autowired
	UnivService univDAO;
	
	@Autowired
	FakultasService fakultasDAO;
	
	@Autowired
	ProdiService prodiDAO;
	
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
		model.addAttribute("pageTitle", "Lihat Mahasiswa");
		
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
		List<UnivModel> listUniv = univDAO.selectAllUniv();
		List<ComboBoxModel> listJenisKelamin = ComboBoxModel.getJenisKelamin();
		List<ComboBoxModel> listJalurMasuk = ComboBoxModel.getJalurMasuk();
		model.addAttribute("pageTitle", "Tambah Mahasiswa");
		model.addAttribute("listUniv", listUniv);
		model.addAttribute("listJenisKelamin", listJenisKelamin);
		model.addAttribute("listJalurMasuk", listJalurMasuk);
		return "form-add";
	}
	
	@RequestMapping(value = "/mahasiswa/tambah", method = RequestMethod.POST)
	public String addSubmit (Model model, @Valid @ModelAttribute MhsModel mhs) {
		mhs.setNpm(generateNPM(mhs.getTahunMasuk(), mhs.getIdUniv(), mhs.getIdProdi(), mhs.getJalurMasuk().split(":")[0]));
		mhs.setJalurMasuk(mhs.getJalurMasuk().split(":")[1]);
		mhs.setStatus("Aktif");
		mhsDAO.addMhs(mhs);
		model.addAttribute("npm", mhs.getNpm());
		return "success-add";
	}
	
	@RequestMapping(value = "/getFakultasbyUniv", method = RequestMethod.GET)
	public @ResponseBody
	List<FakultasModel> findAllFakultasbyUniv(
	        @RequestParam(value = "idUniv", required = true) int idUniv) {
	    return fakultasDAO.selectAllFakultasbyUniv(idUniv);
	}
	
	@RequestMapping(value = "/getProdibyFakultas", method = RequestMethod.GET)
	public @ResponseBody
	List<ProdiModel> findAllProdibyFakultas(
	        @RequestParam(value = "idFakultas", required = true) int idFakultas) {
		return prodiDAO.selectAllProdibyFakultas(idFakultas);
	}
	
	public String generateNPM(String tahunMasuk, int idUniv, int idProdi, String jalurMasuk) {
		String kodeUniv = univDAO.selectKodeUnivbyId(idUniv);
		String kodeProdi = prodiDAO.selectKodeProdibyId(idProdi);
		String baseNPM = String.format("%s%s%s%s", tahunMasuk.substring(2, 4), kodeUniv, kodeProdi, jalurMasuk);
		String lastSeq = mhsDAO.selectLatestSeqNPM(baseNPM + "%");
		String npm = String.format("%s%s", baseNPM, lastSeq);
		return npm;
	}
	
	@RequestMapping("/mahasiswa/ubah/{npm}")
    public String update (Model model,
            @PathVariable(value = "npm") String npm)
    {
        MhsModel mhs = mhsDAO.selectMhs(npm);
        List<UnivModel> listUniv = univDAO.selectAllUniv();
		List<ComboBoxModel> listJenisKelamin = ComboBoxModel.getJenisKelamin();
		List<ComboBoxModel> listJalurMasuk = ComboBoxModel.getJalurMasuk();
		
        if (mhs != null) {
        	List<FakultasModel> listFakultas = fakultasDAO.selectAllFakultasbyUniv(mhs.getIdUniv());
            List<ProdiModel> listProdi = prodiDAO.selectAllProdibyFakultas(mhs.getIdFakultas());
        	model.addAttribute("title", "Ubah Mahasiswa");
            model.addAttribute ("mhs", mhs);
            model.addAttribute("listUniv", listUniv);
            model.addAttribute("listProdi", listProdi);
            model.addAttribute("listFakultas", listFakultas);
    		model.addAttribute("listJenisKelamin", listJenisKelamin);
    		model.addAttribute("listJalurMasuk", listJalurMasuk);
            return "form-update";
        } else {
        	model.addAttribute("title", "Data Mahasiswa Tidak Ada");
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
	
	@RequestMapping(value="/mahasiswa/ubah/{npm}", method = RequestMethod.POST)
    public String updateSubmit (Model model
    		, @ModelAttribute MhsModel mhs)
    {
		mhs.setNpm(generateNPM(mhs.getTahunMasuk(), mhs.getIdUniv(), mhs.getIdProdi(), mhs.getJalurMasuk().split(":")[0]));
		mhs.setJalurMasuk(mhs.getJalurMasuk().split(":")[1]);
		mhs.setStatus("Aktif");
		mhsDAO.updateMhs(mhs);
		model.addAttribute("npm", mhs.getNpm());
		return "success-update";
    }
	
	
	@RequestMapping(value = "/mahasiswa/kelulusan", method = RequestMethod.GET)
	public String kelulusanSubmit (Model model,  
			@RequestParam(value = "thn", required = false) String thn,
            @RequestParam(value = "univ", required = false) String univ,
            @RequestParam(value = "fakultas", required = false) String fakultas,
            @RequestParam(value = "prodi", required = false) String prodi)
	{
		model.addAttribute("pageTitle", "Kelulusan Mahasiswa");
		if(thn != null && univ != null && fakultas != null && prodi != null) {
			String namaUniv = univDAO.selectNamaUnivbyId(Integer.parseInt(univ));
			String namaFakultas = fakultasDAO.selectNamaFakultasbyId(Integer.parseInt(fakultas));
			String namaProdi = prodiDAO.selectNamaProdibyId(Integer.parseInt(prodi));
			int jmlMhs = mhsDAO.selectJmlMhs(thn, prodi);
			int jmlMhsLulus = mhsDAO.selectJmlMhsLulus(thn, prodi);
			String presentaseLulus = "0";
			if (jmlMhs > 0) {
				presentaseLulus = String.format("%.2f", ((float)jmlMhsLulus/(float)jmlMhs) * 100);
			}
			
			model.addAttribute("jmlMhs", jmlMhs);
			model.addAttribute("jmlMhsLulus", jmlMhsLulus);
			model.addAttribute("presentaseLulus", presentaseLulus);
			model.addAttribute("tahunKelulusan", thn);
			model.addAttribute("univKelulusan", namaUniv);
			model.addAttribute("fakultasKelulusan", namaFakultas);
			model.addAttribute("prodiKelulusan", namaProdi);
			
			return "kelulusan";
		}
		else {
			List<UnivModel> listUniv = univDAO.selectAllUniv();
			model.addAttribute("listUniv", listUniv);
			return "form-kelulusan";
		}
	}
	
	@RequestMapping(value = "/mahasiswa/cari", method = RequestMethod.GET)
	public String cariMhs (Model model,
            @RequestParam(value = "univ", required = false) String univ,
            @RequestParam(value = "fakultas", required = false) String fakultas,
            @RequestParam(value = "prodi", required = false) String prodi) {
		model.addAttribute("pageTitle", "Cari Mahasiswa");
		if(univ != null && fakultas != null && prodi != null) {
			String namaUniv = univDAO.selectNamaUnivbyId(Integer.parseInt(univ));
			String namaFakultas = fakultasDAO.selectNamaFakultasbyId(Integer.parseInt(fakultas));
			String namaProdi = prodiDAO.selectNamaProdibyId(Integer.parseInt(prodi));
			List<MhsModel> listMhs = mhsDAO.selectAllMhsbyProdi(prodi);
			
			model.addAttribute("namaUniv", namaUniv);
			model.addAttribute("namaFakultas", namaFakultas);
			model.addAttribute("namaProdi", namaProdi);
			model.addAttribute("listMhs", listMhs);
			
			return "viewbyprodi";
		}
		else {
			List<UnivModel> listUniv = univDAO.selectAllUniv();
			model.addAttribute("listUniv", listUniv);
			return "form-search";
		}
	}
	
	@RequestMapping(value = "/mahasiswa/cariusia", method = RequestMethod.GET)
	public String cariMhsbyUsia (Model model,  
			@RequestParam(value = "thn", required = false) String thn,
            @RequestParam(value = "univ", required = false) String univ,
            @RequestParam(value = "fakultas", required = false) String fakultas,
            @RequestParam(value = "prodi", required = false) String prodi) {
		model.addAttribute("pageTitle", "Cari Usia Mahasiswa");
		if(thn != null && univ != null && fakultas != null && prodi != null) {
			String namaUniv = univDAO.selectNamaUnivbyId(Integer.parseInt(univ));
			String namaFakultas = fakultasDAO.selectNamaFakultasbyId(Integer.parseInt(fakultas));
			String namaProdi = prodiDAO.selectNamaProdibyId(Integer.parseInt(prodi));
			int umurTermuda = mhsDAO.selectUmurTermuda(thn, prodi);
			int umurTertua = mhsDAO.selectUmurTertua(thn, prodi);
			
			model.addAttribute("umurTermuda", umurTermuda);
			model.addAttribute("umurTertua", umurTertua);
			model.addAttribute("tahunKelulusan", thn);
			model.addAttribute("univKelulusan", namaUniv);
			model.addAttribute("fakultasKelulusan", namaFakultas);
			model.addAttribute("prodiKelulusan", namaProdi);
			
			return "viewbyusia";
		}
		else {
			List<UnivModel> listUniv = univDAO.selectAllUniv();
			model.addAttribute("listUniv", listUniv);
			return "form-searchUsia";
		}
	}
	
	@RequestMapping("/mahasiswa/hapus/{npm}")
    public String hapusMhs (Model model, @PathVariable(value = "npm") String npm)
    {
		MhsModel mhs = mhsDAO.selectMhs(npm);
    	if (mhs != null) {
    		model.addAttribute("pageTitle", "Sukses Menghapus Mahasiswa");
    		mhsDAO.deleteMhs (mhs);
    		return "delete";
    	} else {
    		model.addAttribute("pageTitle", "Gagal Menghapus Mahasiswa");
    		model.addAttribute ("npm", npm);
            return "not-found";
    	}        
    }
}
