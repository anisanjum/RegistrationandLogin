package com.learn.fundtransfer1.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	protected Long id;

	protected String username;
	protected String password;
	protected boolean loggedIn;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public Customer(Long id, String username, String password, boolean loggedIn) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.loggedIn = loggedIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,username,password,loggedIn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if(!(obj instanceof Customer)) return false;
		Customer customer=(Customer) obj;
		return Objects.equals(username, customer.username) &&
				Objects.equals(password, customer.password);

	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", loggedIn=" + loggedIn
				+ "]";
	}



}