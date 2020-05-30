package exam.demo.repository.kanselyariya;

import exam.demo.entity.User;
import exam.demo.entity.hujjat.HujjatBajarish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HujjatBajarishRepository extends JpaRepository<HujjatBajarish,Long> {
    List<HujjatBajarish> findByKimga (User user);

    List<HujjatBajarish> findByKimdan(User user);
}
