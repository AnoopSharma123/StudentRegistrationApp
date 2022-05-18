package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.CityMasterEntity;

public interface CityMasterRepo extends JpaRepository<CityMasterEntity, Integer> {

	public List<CityMasterEntity>findByStateId(Integer Id);
	
}
