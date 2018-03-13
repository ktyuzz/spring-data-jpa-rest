package com.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.domain.CustomerCode;

@RepositoryRestResource
public interface CustomerCodeRepository extends JpaRepository<CustomerCode, Integer>, JpaSpecificationExecutor {
    /**
     * https://docs.spring.io/spring-data/jpa/docs/1.7.0.RELEASE/reference/html/#jpa.query-methods
     * - query method의 키워드와 관련하여 위의 링크를 참조하여 사용할것
     * 
     */
    /**
     * /api/customerCodes/search/code?code=E
     * 
     * @param code
     * @return
     */
    @RestResource(path = "code")
    List<CustomerCode> findByCodeContaining(@Param("code") String code);

    /**
     * /api/customerCodes/search/ceo?ceo=E
     * 
     * @param ceo
     * @return
     */
    @RestResource(path = "ceo")
    List<CustomerCode> findByCeoContaining(@Param("ceo") String ceo);

    /**
     * /api/customerCodes?corpNm=E&corpCode=1234&cond=and
     * 
     * @param corpNm
     * @return
     */
    @RestResource(path = "corpNm")
    List<CustomerCode> findByCorpNmContaining(@Param("corpNm") String corpNm);

    // Page<CustomerCode> findByContentsTitleStartsWith(@Param("title") String title, Pageable pageable);
    //
    // @Query("SELECT * FROM CustomerCode WHERE :cond like:place")
    // List<CustomerCode> findBySearchText(@Param("_like") String searchCond, @Param("_like") String searchText);
    //
    // List<CustomerCode> findAll(Specification spec);
    // @RestResource(path = "corpNm2")
    // public Page findByNameStartsWith(@Param("corpNm") String corpNm, Pageable p);
}
