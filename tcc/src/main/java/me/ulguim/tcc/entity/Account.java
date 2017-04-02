package me.ulguim.tcc.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.bean.ExtraParamsBean;
import me.ulguim.tcc.entity.converter.ExtraParamsConverter;
import sun.misc.Perf;

@Entity
@Table(name="account")
public class Account extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String avatar;
	
	private String username;
	
	private String name;

	private String lastname;

	private String email;
	
	private String password;

	@Column(name = "extra_params")
	@Convert(converter = ExtraParamsConverter.class)
	private ExtraParamsBean extraParams;

	@OneToOne(mappedBy = "account")
	private Perfil profile;

	@Column(name = "contacts_ids")
	@ElementCollection(targetClass=Long.class)
	private List<Long> contactsIdList = new ArrayList<>();

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<Post> postList;

	private String chave;
	@Column(name = "insert_time")
	private Timestamp insertTime;
	@Column(name = "update_time")
	private Timestamp updateTime;
	@Column(name = "insert_by")
	private Long insertBy;
	@Column(name = "update_by")
	private Long updateBy;

	public Account() {
		
	}

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(Long insertBy) {
		this.insertBy = insertBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public ExtraParamsBean getExtraParams() {
		return extraParams;
	}

	public void setExtraParams(ExtraParamsBean extraParams) {
		this.extraParams = extraParams;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public Perfil getProfile() {
		return profile;
	}

	public void setProfile(Perfil profile) {
		this.profile = profile;
	}

	public String getLabel() {
		return this.name + " " + this.lastname;
	}

	public List<Long> getContactsIdList() {
		return contactsIdList;
	}

	public void setContactsIdList(List<Long> contactsIdList) {
		this.contactsIdList = contactsIdList;
	}

	public void addContact(Long id) {
		this.contactsIdList.add(id);
	}

	public boolean contactExists(Long id) {
		return this.contactsIdList.stream()
				.filter(l -> l.equals(id))
				.findAny().isPresent();
	}

	public boolean removeContact(Long id) {
		return this.contactsIdList.removeIf(l -> l.equals(id));
	}
}
