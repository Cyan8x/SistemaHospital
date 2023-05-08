package com.Sistema.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGENERICRepository<T, ID> extends JpaRepository<T, ID>{

}
