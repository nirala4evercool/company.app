package com.chandan.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.chandan.app.entity.Company;
import com.chandan.app.error.ApplicationErrorCodes;
import com.chandan.app.repository.CompanyRepository;
import com.chandan.app.service.CompanyService;
import com.chandan.app.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandan.app.exception.ResourceNotFoundException;
import com.chandan.app.exception.ValidationException;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ServiceHelper serviceHelper;

	@Override
	public List<Company> getAllCompanies() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		companyRepository.findAll().forEach(companyList::add);
		return companyList;
	}

	@Override
	public Company getCompanyById(Long id) {
		Optional<Company> optionalCompany=companyRepository.findById(id);
		if (optionalCompany.isPresent()){
			return optionalCompany.get();
		}else {
			return null;
		}

	}

	@Override
	public Company getCompanyByCode(String companyCode) {
	Optional<Company>	optionalCompany=companyRepository.findByCompanyCode(companyCode);
	if (optionalCompany.isPresent()){
		return optionalCompany.get();
	}
		return null;
	}

	@Override
	public Company addCompany(Company company) throws ValidationException {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) throws ValidationException {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		company.setId(existingCompanyRecord.getId());
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		companyRepository.deleteById(existingCompanyRecord.getId());
	}
}
