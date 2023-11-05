package com.chandan.app.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chandan.app.dto.CompanyDTO;
import com.chandan.app.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperImpl implements CompanyMapper {

	@Override
	public CompanyDTO getCompanyDTO(Company entity) {
		CompanyDTO dto = new CompanyDTO();

		dto.setId(entity.getId());
		dto.setCompanyName(entity.getCompanyName());
		dto.setEmail(entity.getEmail());
		dto.setStrength(entity.getStrength());
		dto.setWebSiteURL(entity.getWebSiteURL());
		dto.setCompanyCode(entity.getCompanyCode());

		return dto;
	}

	@Override
	public List<CompanyDTO> getCompanyDTOList(List<Company> entityList) {
		List<CompanyDTO> dtoList = new ArrayList<CompanyDTO>();

		for (Company entity : entityList) {
			dtoList.add(getCompanyDTO(entity));
		}

		return dtoList;
	}

	@Override
	public Set<CompanyDTO> getCompanyDTOSet(Set<Company> entitySet) {
		Set<CompanyDTO> dtoSet = new HashSet<CompanyDTO>();

		for (Company entity : entitySet) {
			dtoSet.add(getCompanyDTO(entity));
		}

		return dtoSet;
	}

	@Override
	public Company getCompany(CompanyDTO dto) {
		Company entity = new Company();

		entity.setId(dto.getId());
		entity.setCompanyName(dto.getCompanyName());
		entity.setEmail(dto.getEmail());
		entity.setStrength(dto.getStrength());
		entity.setWebSiteURL(dto.getWebSiteURL());
		entity.setCompanyCode(dto.getCompanyCode());

		return entity;
	}

}
