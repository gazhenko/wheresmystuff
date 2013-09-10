package edu.gatech.cs2340.thenullterminators.wheresmystuff;


/**
 * This class is used to create a User object
 * 
 * @author acranford6
 * @version 1.2 1MAR2013 acranford6
 * 
 */
public class User{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 5L;
	private String userName;
	private String passWord;
	private String phoneNumber;
	private int fails;
	private boolean isAdmin;
	private boolean isLocked;

	/**
	 * This constructor takes in a user's username, password and phone number
	 * 
	 * @param userName
	 *            user's login name
	 * @param passWord
	 *            user's login password
	 */
	public User(String userName, String passWord, String phoneNumber) {
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.fails = 0;
		this.isAdmin = false;
		this.isLocked = false;

	}// end constructor

	/**
	 * This constructor takes a user name and password with no phone number.
	 * 
	 * @param userName
	 *            user's login name
	 * @param passWord
	 *            user's login password
	 */
	public User(String userName, String passWord) {
		this(userName, passWord, null);
	}// end constructor

	/**
	 * This constructor takes a user name and password with a boolean for admin
	 * 
	 * @param userName
	 *            user's login name
	 * @param passWord
	 *            user's login password
	 * @param bool
	 *            true if admin false if user
	 */
	public User(String userName, String passWord, boolean bool) {
		this(userName, passWord, null);
		this.isAdmin = bool;
	}

	/**
	 * This method is a setter for the username instance variable
	 * 
	 * @param userName
	 *            user's login name
	 */
	public void setUserName(String userName) {
		// TODO set up security for this
		this.userName = userName;
	}// end setUserName

	/**
	 * This method is a setter for the password instance variable
	 * 
	 * @param passWord
	 *            user's login password
	 */
	public void setPassWord(String passWord) {
		// TODO set up security for this
		this.passWord = passWord;
	}// end setUserPassWord

	/**
	 * This method is a setter for the phoneNumber instance variable
	 */
	public void setPhoneNumber(String phoneNumber) {
		// TODO set up security for this
		this.phoneNumber = phoneNumber;
	}// end setPhoneNumber

	/**
	 * This method is a getter for the username instance variable
	 * 
	 * @return userName user's login name
	 */
	public String getUserName() {
		return this.userName;
	}// end getUserName

	/**
	 * This method is a getter for the password instance variable
	 * 
	 * @return
	 */
	public String getPassWord() {
		return this.passWord;
	}// end getUserPassWord

	/**
	 * This method is a getter for the phone number instance variable
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}// end getPhoneNumber

	/**
	 * This method is a getter for the amount of password fails this user
	 * currently holds
	 */
	public int getFails() {
		return this.fails;
	}// end getFails

	/**
	 * This method is used to increment the amount of fails this user has made
	 */
	public void incFails() {
		this.fails++;
	}// end incFails

	/**
	 * This method is used to allow the admin user to reset the fails for a user
	 */
	public void resetFails() {
		fails = 0;
	}// end resetFails

	/**
	 * This method is used to set the state of the user to locked or unlocked.
	 * 
	 * @param bool
	 *            set to locked or unlocked
	 */
	public void setIsLocked(boolean bool) {
		isLocked = bool;
	}

	/**
	 * This method returns the current value of isLocked
	 * 
	 * @return isLocked boolean
	 */
	public boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * This method takes in a boolean value and sets the variable equal to it to
	 * verify if a user has admin access
	 * 
	 * @param bool
	 */
	public void setAdmin(boolean bool) {
		isAdmin = bool;
	}

	/**
	 * This method returns the s current value of isAdmin to the calling code.
	 * 
	 * @return
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

}// end User