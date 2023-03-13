
package com.example.test2.repository;


import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.io.Serializable;

/**
 * Extension of {@link JpaRepository} and {@link QueryDslPredicateExecutor}
 * <p>Ορισμός μεθόδων που θα χρησιμοποιούνται από όλα τα repositories σε ένα κοινό σημείο.</p>
 * @param <T>  Entity class
 * @param <ID> Id class
 */
@NoRepositoryBean
public interface ExtendedQueryDslJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    /**
     * Ανάκτηση {@link Page} αντικειμένων {@code T1} για συγκεκριμένο ερώτημα {@link JPQLQuery}
     * @param jpqlQuery Ερώτημα προς τη βάση
     * @param pageable  Ζητούμενη σελίδα αποτελεσμάτων
     * @param <T1>
     * @return Σελίδα αποτελεσμάτων
     */
    <T1> Page<T1> findAll(JPQLQuery jpqlQuery, Pageable pageable);
}
