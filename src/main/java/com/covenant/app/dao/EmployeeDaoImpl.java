package com.covenant.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.covenant.app.model.Deal;
import com.covenant.app.model.Employee;


@Repository
public class EmployeeDaoImpl  {

	public Employee findById(int id) {
		return em.find(Employee.class, id);
	}

	public void saveEmployee(Employee employee) {
		em.persist(employee);
	}

	
	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Query query = em.createQuery("Select d from Employee d");
		return (List<Employee>) query.getResultList();
	}

	public Employee findEmployeeBySsn(String ssn) {
		
		TypedQuery<Employee> query = em.createQuery("select s from Employee s where s.ssn =:ssn",Employee.class);
		query.setParameter("ssn", ssn);
		if(query.getResultList().size()!=0)
		{
			return query.getResultList().get(0);
		}
		else
		{
			return null;
		}
	}
	@PersistenceContext
	EntityManager em;
}
