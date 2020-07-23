package com.interview.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.interview.entity.Employee;


@Repository
@Transactional
public class EmployeeRepository {
	
		@PersistenceContext
		EntityManager entityManager;

		public List<Employee> findAll() {
			TypedQuery<Employee> namedQuery = entityManager.createNamedQuery("find_all_employees", Employee.class);
			return namedQuery.getResultList();
		}

		public Employee findById(int id) {
			return entityManager.find(Employee.class, id);
		}

		public Employee update(Employee Employee) {
			return entityManager.merge(Employee);
		}

		public Employee insert(Employee Employee) {
			return entityManager.merge(Employee);
		}

		public void deleteById(int id) {
			Employee Employee = findById(id);
			entityManager.remove(Employee);
		}
}
