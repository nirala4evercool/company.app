package com.chandan.app.service;

import java.util.List;

import com.chandan.app.entity.Company;
import com.chandan.app.exception.ValidationException;

public interface CompanyService {

	List<Company> getAllCompanies();
	Company getCompanyById( Long id);
	Company getCompanyByCode(String companyCode);

	Company addCompany(Company company) throws ValidationException;

	Company updateCompany(Long id, Company company) throws ValidationException;
	
	void deleteCompany(Long id);
}