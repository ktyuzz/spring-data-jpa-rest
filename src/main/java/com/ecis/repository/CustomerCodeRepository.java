package com.ecis.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.ecis.domain.CustomerCode;

@RepositoryRestResource
public interface CustomerCodeRepository extends JpaRepository<CustomerCode, Integer> {
    
    @RestResource(path = "startswith")
    List<CustomerCode> findByLastnameAndFirstname(@Param("Lastname") String Lastname,@Param("Firstname") String Firstname);
    /**
     * /api/customerCode/search/startswith?corpNm=A
     * 
     * */
     @RestResource(path="startswith")
     List<CustomerCode> findByCorpNmStartsWith(@Param("corpNm") String corpNm);
    // Page<CustomerCode> findByContentsTitleStartsWith(@Param("title") String title, Pageable pageable);
    //
    // @Query("SELECT * FROM CustomerCode WHERE :cond like:place")
    // List<CustomerCode> findBySearchText(@Param("_like") String searchCond, @Param("_like") String searchText);
    //
    // List<CustomerCode> findAll(Specification spec);
}
