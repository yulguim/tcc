package me.ulguim.tcc.entity;

import me.ulguim.tcc.bean.SocialNetwork;
import me.ulguim.tcc.entity.Account;

import javax.persistence.*;
import java.util.List;

/**
 * Created by yulle on 27/01/17.
 */

@Entity
@Table(name="profile")
public class Perfil {

	@Id
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Account account;

//	private List<SocialNetwork> socialNetworkList;


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
