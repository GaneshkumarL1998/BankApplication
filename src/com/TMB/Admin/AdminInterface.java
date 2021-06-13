package com.TMB.Admin;

import java.io.Serializable;
public interface AdminInterface extends Serializable{
	AdminInterface login();
	void adduser();
	void removeuser();
	void updateuser();
	void searchusers();
	void changePassword();
	void viewAllUsers();
}
