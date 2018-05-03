package per.agreysky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.agreysky.domain.Girl;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl,Integer>{
    //通过年龄查询
    public List<Girl> findByAge(Integer age);
}
