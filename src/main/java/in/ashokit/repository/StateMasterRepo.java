package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.StateMasterEntity;

public interface StateMasterRepo extends JpaRepository<StateMasterEntity,Integer>{
	
	public List<StateMasterEntity> findByCountryId(Integer countryId);

}
