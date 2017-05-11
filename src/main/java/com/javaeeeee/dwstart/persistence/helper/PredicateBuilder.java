package com.javaeeeee.dwstart.persistence.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateBuilder<T> {

	Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> root);
}