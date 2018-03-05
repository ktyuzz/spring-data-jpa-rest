//package com.ecis.specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//import com.ecis.criteria.SearchCriteria;
//import com.ecis.domain.CustomerCode;
//
//public class CustomCodeSpecification implements Specification<CustomerCode> {
//    private SearchCriteria criteria;
//
//    public CustomCodeSpecification(SearchCriteria searchCriteria) {
//        // TODO Auto-generated constructor stub
//    }
//
//    @Override
//    public Predicate toPredicate(Root<CustomerCode> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
//        if (criteria.getOperation().equalsIgnoreCase(">")) {
//            return builder.greaterThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
//            return builder.lessThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                return builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue() + "%");
//            } else {
//                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
//        }
//        return null;
//    }
//}