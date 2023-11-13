package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.domain.repositories.ICompanyRepository;
import com.quyvx.main_server.infrastructure.entities.CompanyEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.CompanyEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.CompanyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CompanyRepository implements ICompanyRepository {
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyEntityMapper mapper;
    @Override
    public Company save(Company model) {
        CompanyEntity entity = mapper.modelToEntity(model);
        entity = companyJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        companyJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
