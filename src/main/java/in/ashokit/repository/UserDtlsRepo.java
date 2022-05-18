package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.UserAccountEntity;

public interface UserDtlsRepo extends JpaRepository<UserAccountEntity, Integer>{
	
	public UserAccountEntity findByEmail(String emailId);
    public UserAccountEntity findByEmailAndPazzword(String emailId, String pwd);
    
}
