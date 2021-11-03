package com.example.application.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class Votes {


	    private int idvotes;
	    private String yay;
	    private String nay;
	    private String name;
	    private String committee;
	    
	    public Votes() {
	    	
	    }

	public Votes(int idvotes, String yay, String nay, String name, String committee) {
		this.yay = yay;
		this.nay = nay;
		this.name = name;
		this.committee = committee;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)

	public int getIdvotes() {
		return idvotes;
	}

	public void setIdvotes(int idvotes) {
		this.idvotes = idvotes;
	}

	public String getYay() {
		return yay;
	}

	public void setYay(String yay) {
		this.yay = yay;
	}

	public String getNay() {
		return nay;
	}

	public void setNay(String nay) {
		this.nay = nay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommittee() {
		return committee;
	}

	public void setCommittee(String committee) {
		this.committee = committee;
	}

	@Override
	public String toString() {
		return "Votes{" +
				"idvotes=" + idvotes +
				", yay='" + yay + '\'' +
				", nay='" + nay + '\'' +
				", name='" + name + '\'' +
				", committee='" + committee + '\'' +
				'}';
	}
}

    

