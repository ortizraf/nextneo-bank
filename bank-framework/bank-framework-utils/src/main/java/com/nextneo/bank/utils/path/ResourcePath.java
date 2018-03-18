package com.nextneo.bank.utils.path;

public class ResourcePath {

	public final static String ACCOUNT = "/account";
	public final static String ACCOUNT_MOVEMENT = "/account-movement";
	public final static String CUSTOMER = "/customer";
	public final static String USER = "/user";
	public final static String LOGIN = "/login";

	public class Account {

		public final static String FIND_ALL = "findAll";
		public final static String FIND_BY_ID = "findById/{id}/{customerId}";
		public final static String FIND_BY_CUSTOMER = "findByCustomer/{customerId}";
		public final static String ADD_ACCOUNT = "addAccount";

	}

	public class AccountMovement {

		public final static String ADD_ACCOUNT_MOVEMENT = "addAccountMovement";
		public final static String FIND_MOVEMENTS_BY_ACCOUNT = "findMovementsByAccount/{accountId}";
		public final static String FIND_MOVEMENTS_BY_ACCOUNT_FILTER = "findMovementsByAccount/{accountId}/{page}/{maxResult}";

	}
	
	public class Customer {

		public final static String FIND_BY_ID = "findById/{id}";
		public final static String FIND_BY_ACCOUNT = "findByAccount/{accountId}";

	}

	public class User {

		public final static String ADD_CLIENT = "addClient";
		public final static String FIND_BY_ID = "findById/{id}";
		public final static String FIND_BY_ACCOUNT = "findByAccount/{accountId}";

	}
	
	public class Login {
		
		public final static String DO_LOGIN = "doLogin";

	}

}
