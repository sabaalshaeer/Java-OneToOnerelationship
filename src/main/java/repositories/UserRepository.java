package repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.AppUser;



//Repositories define the operations that can done to data sources
//insert ,remove, edit, delete, search, etc...
@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
	 public Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);
	 
	public Optional<AppUser> findAppUserByUsernameAndPassword(String username);



}
