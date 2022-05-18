package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.CountryMasterEntity;

public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity, Integer>{

}
