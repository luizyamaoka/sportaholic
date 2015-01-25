package com.sportaholic.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.sportaholic.dto.ClientDto;

@Entity
@Table(name=DomainConstants.TB_CLIENT)
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="gender")
	private String gender;
	
	@OneToMany(mappedBy="client")
	private List<Reading> readings;
	
	@OneToMany(mappedBy="client")
	private List<Interest> interests;
	
	@OneToMany(mappedBy="client")
	private List<ArticleComment> articleComments;

	@OneToMany(mappedBy="client")
	private List<ProductView> productViews;
	
	@OneToMany(mappedBy="client")
	private List<Address> addresses;
	
	@OneToMany(mappedBy="client")
	private List<Purchase> purchases;
	
	@OneToMany(mappedBy="client")
	private List<ProductComment> productComments;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean isPasswordCorrect(String password) {
		return BCrypt.checkpw(password, this.password);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Transient
	public String getCompleteName() {
		return this.firstName + " " + this.lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public List<ArticleComment> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(List<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public ClientDto toClientDto() {
		ClientDto clientDto = new ClientDto();
		
		clientDto.setId(this.id);
		clientDto.setFirstName(this.firstName);
		clientDto.setLastName(this.lastName);
		clientDto.setCpf(this.cpf);
		clientDto.setEmail(this.email);
		if (this.birthDate != null) {
			Calendar cal = Calendar.getInstance();
		    cal.setTime(this.birthDate);
		    clientDto.setBirthDay(cal.get(Calendar.DAY_OF_MONTH));
		    clientDto.setBirthMonth(cal.get(Calendar.MONTH) + 1);
		    clientDto.setBirthYear(cal.get(Calendar.YEAR));
		}
		clientDto.setGender(this.gender);
		
		return clientDto;
	}
	
	@Transient
	public String getUri() {
		return UrlConstants.URL_CLIENT + "/" + this.id;
	}
	
	@Transient
	public String getEditUri() {
		return UrlConstants.URL_CLIENT + "/" + this.id + "/edit";
	}

	public List<ProductView> getProductViews() {
		return productViews;
	}

	public void setProductViews(List<ProductView> productViews) {
		this.productViews = productViews;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}
}
