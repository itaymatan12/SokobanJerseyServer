package com.matanitay.JerseyServer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import db.LevelSolution;
import db.LevelSolutionManager;

@Path("employees")
public class SolutionService 
{

	private LevelSolutionManager manager;
	
	public SolutionService() {
		manager =  new LevelSolutionManager();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LevelSolution> getAllSolutions()
	{
		return manager.getAllSolutions();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEmployee(LevelSolution emp) 
	{
		if(!manager.SolutionisExist(emp.getLevelName()))
		{
			manager.addSolution(emp);
		}
	}
}
