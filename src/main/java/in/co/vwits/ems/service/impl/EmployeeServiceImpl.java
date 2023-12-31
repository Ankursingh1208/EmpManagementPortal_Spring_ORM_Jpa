package in.co.vwits.ems.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.vwits.ems.dao.EmployeeDao;
import in.co.vwits.ems.model.Employee;
import in.co.vwits.ems.service.EmployeeService;
import in.co.vwits.model.exception.EmployeeNotFoundException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao dao;

	
	@Override
	public List<Employee> findAll(){
		return dao.findAll();
	}



	@Override
	public Optional<Employee> findByEmpId(int empId) throws EmployeeNotFoundException 
	{
		Optional <Employee> p = dao.findByEmpId(empId);
		if(p.isPresent()) {
		return p; 
		}
		else {
			//throw user defined exception "EmployeeNotFound"
			throw new EmployeeNotFoundException();
		}
	}

	@Override
	public void deleteByEmpId(int empID) 
	{
		dao.deleteByEmpId(empID);
	}

	@Override
	public void updateByEmpId(Employee e) 
	{
		this.dao.updateByEmpId(e);
	}
	@Override
	public List<Employee> findAllEmployeeSortedBySalary(){
		return dao.findAll().stream().sorted().collect(Collectors.toList()); 
	}
	@Override
	public List<Employee> findAllEmployeeSortedByName() {
	    return dao.findAll()
	              .stream()
	              .sorted(Comparator.comparing(Employee::getName))
	              .collect(Collectors.toList());
	}
	

	@Override
	public void save(Employee emp) {
		dao.save(emp);
	}
}
