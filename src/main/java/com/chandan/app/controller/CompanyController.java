package com.chandan.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.chandan.app.dto.CompanyDTO;
import com.chandan.app.entity.Company;
import com.chandan.app.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chandan.app.exception.ValidationException;
import com.chandan.app.mapper.CompanyMapper;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/companies")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<Company> companyList = companyService.getAllCompanies();
		
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();
		
		for (Company entity : companyList) {
			companyDTOList.add(companyMapper.getCompanyDTO(entity));
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(companyDTOList);
	}
	@GetMapping("/companies/{id}")
public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id){
		Company company=companyService.getCompanyById(id);
		if (company==null){
			return ResponseEntity.notFound().build();
		}
		CompanyDTO companyDTO = companyMapper.getCompanyDTO(company);
		return  ResponseEntity.status(HttpStatus.OK).body(companyDTO);

	}
	@GetMapping("/companies/by-code/{companyCode}")
	public ResponseEntity<CompanyDTO> getCompanyByCode(@PathVariable String companyCode){
		Company company=companyService.getCompanyByCode(companyCode);
		if (company==null){
			return  ResponseEntity.notFound().build();
		}
		CompanyDTO companyDTO=companyMapper.getCompanyDTO(company);
		return ResponseEntity.status(HttpStatus.OK).body(companyDTO);

	}
	@PostMapping("/companies")
	public ResponseEntity<CompanyDTO> addCompany(@Valid @RequestBody CompanyDTO companyDTO)
			throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company newCompany = companyService.addCompany(company);
		CompanyDTO newCompanyDTO = companyMapper.getCompanyDTO(newCompany);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId())
				.toUri();
		return ResponseEntity.created(location).body(newCompanyDTO);
	}

	@PutMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable(value = "id") Long id,
			@Valid @RequestBody CompanyDTO companyDTO) throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company updatedCompany = companyService.updateCompany(id, company);
		CompanyDTO updatedCompanyDTO = companyMapper.getCompanyDTO(updatedCompany);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(updatedCompanyDTO);
	}

	@DeleteMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable(value = "id") Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.noContent().build();
	}

	public String getMessage(String exceptionCode) {
		return messageSource.getMessage(exceptionCode, null, LocaleContextHolder.getLocale());
	}

}
