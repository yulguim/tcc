package me.ulguim.tcc.entity;

import me.ulguim.tcc.bean.SocialNetwork;
import me.ulguim.tcc.entity.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by yulle on 27/01/17.
 */

@Entity
@Table(name="profile")
public class Perfil {

	@Id
	private Long id;

	private List<SocialNetwork> socialNetworkList;

}
