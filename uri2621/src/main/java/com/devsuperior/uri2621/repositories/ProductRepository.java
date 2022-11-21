package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjetion;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true, value = "select products.name "
	 		+ "from products "
	 		+ "inner join providers ON providers.id = products.id_providers "
	 		+ "where products.amount between :min and :max and "
	 		+ "providers.name like CONCAT(:beginName,'%')") 
	List<ProductMinProjetion> search1(Integer min, Integer max, String beginName);
	
	@Query("select new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) "
	 		+ "from Product obj "
	 		+ "where obj.amount between :min and :max and "
	 		+ "obj.provider.name like CONCAT(:beginName,'%')") 
	List<ProductMinDTO> search2(Integer min, Integer max, String beginName);
	
}
