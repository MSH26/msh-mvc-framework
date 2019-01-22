package mvc.model;

import mvc.systemfiles.systemclasses.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "user")
public class UserModel extends Model{
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "dateofbirth")
	private String dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "questionone")
	private String questionOne;
	@Column(name = "questiontwo")
	private String questionTwo;
	@Column(name = "questionthree")
	private String questionThree;
	@Column(name = "answerone")
	private String answerOne;
	@Column(name = "answertwo")
	private String answerTwo;
	@Column(name = "answerthree")
	private String answerThree;
	
	
	
	public UserModel(){}
	
	public UserModel(int id, String userName){
		this.id = id;		// Can be set once when created
		this.userName = userName;		// Can be set once when created. For this version it's modification has been restricted
	}
	
	public UserModel(int id, String userName, String password, String firstName, String lastName, String dateOfBirth, String gender, String email, String phone, String questionOne, String questionTwo, String questionThree, String answerOne, String answerTwo, String answerThree){
		this.id = id;		// Can be set once when created
		this.userName = userName;		// Can be set once when created. For this version it's modification has been restricted
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.questionOne = questionOne;
		this.questionTwo = questionTwo;
		this.questionThree = questionThree;
		this.answerOne = answerOne;
		this.answerTwo = answerTwo;
		this.answerThree = answerThree;
	}
	
	public int getId(){
		return id;
	}
	
	/** Restrict the access of modifiying userName */
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return userName;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getFirstName(){
		return firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getLastName(){
		return lastName;
	}
	
	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfBirth(){
		return dateOfBirth;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	public String getGender(){
		return gender;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return phone;
	}
	
	public void setQuestionOne(String questionOne){
		this.questionOne = questionOne;
	}
	public String getQuestionOne(){
		return questionOne;
	}
	
	public void setQuestionTwo(String questionTwo){
		this.questionTwo = questionTwo;
	}
	public String getQuestionTwo(){
		return questionTwo;
	}
	
	public void setQuestionThree(String questionThree){
		this.questionThree = questionThree;
	}
	public String getQuestionThree(){
		return questionThree;
	}
	
	public void setAnswerOne(String answerOne){
		this.answerOne = answerOne;
	}
	public String getAnswerOne(){
		return answerOne;
	}
	
	public void setAnswerTwo(String answerTwo){
		this.answerTwo = answerTwo;
	}
	public String getAnswerTwo(){
		return answerTwo;
	}
	
	public void setAnswerThree(String answerThree){
		this.answerThree = answerThree;
	}
	public String getAnswerThree(){
		return answerThree;
	}
}