package me.ulguim.tcc.entity.perfil;

import me.ulguim.tcc.bean.SocialNetwork;
import me.ulguim.tcc.entity.Account;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by yulle on 27/01/17.
 */

@Table(name="profile")
public class Perfil {

	@Id
	private Long id;

	@OneToOne
	private Account account;

	private List<SocialNetwork> socialNetworkList;

}
