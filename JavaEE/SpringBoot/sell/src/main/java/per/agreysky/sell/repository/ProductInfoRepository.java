package per.agreysky.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.agreysky.sell.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
