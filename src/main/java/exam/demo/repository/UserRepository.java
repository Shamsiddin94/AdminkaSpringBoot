package exam.demo.repository;

import exam.demo.entity.User;
import exam.demo.repository.specs.UsersSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

Optional<User> findByUserName(String userName);
Optional<User> findById(Long id);
Page<User>  findAll( Pageable pageable);


 }
