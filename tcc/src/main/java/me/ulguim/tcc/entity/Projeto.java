package me.ulguim.tcc.entity;

import in.k2s.sdk.jpa.entity.BaseEntity;
import me.ulguim.tcc.bean.*;
import me.ulguim.tcc.entity.converter.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="projeto")
public class Projeto extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private StatusProjeto status;

	private String titulo;

	@Column(columnDefinition="TEXT")
	private String descricao;

	@Column(name = "habilidades", columnDefinition="TEXT")
	@Convert(converter = HabilidadesConverter.class)
	private List<HabilidadeBean> habilidadeList;

	@Column(name = "links_externos", columnDefinition="TEXT")
	@Convert(converter = SocialNetworkConverter.class)
	private List<SocialNetworkBean> linksExternos = new ArrayList<>();

	private Boolean permiteRequest = true;

	@Column(name = "tags", columnDefinition="TEXT")
	@Convert(converter = TagsConverter.class)
	private List<TagBean> tags = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="fk_account")
	private Account owner;

	@OneToMany(mappedBy="projeto")
	private List<AccountProjeto> accountProjetoList;

	@OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY)
	private List<Post> postList;

	//private List<Account> participantes;

	@Column(name = "mensagens", columnDefinition="TEXT")
	@Convert(converter = MensagemConverter.class)
	private List<MensagemBean> mensagens = new ArrayList<>();

	private String chave;
	@Column(name = "insert_time")
	private Timestamp insertTime;
	@Column(name = "update_time")
	private Timestamp updateTime;
	@Column(name = "insert_by")
	private Long insertBy;
	@Column(name = "update_by")
	private Long updateBy;

	public Projeto() {

	}

	public enum StatusProjeto {
		ATIVO, INATIVO, REMOVIDO
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public List<MensagemBean> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MensagemBean> mensagens) {
		this.mensagens = mensagens;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<HabilidadeBean> getHabilidadeList() {
		return habilidadeList;
	}

	public void setHabilidadeList(List<HabilidadeBean> habilidadeList) {
		this.habilidadeList = habilidadeList;
	}

	public List<SocialNetworkBean> getLinksExternos() {
		return linksExternos;
	}

	public void setLinksExternos(List<SocialNetworkBean> linksExternos) {
		this.linksExternos = linksExternos;
	}

	public Boolean getPermiteRequest() {
		return permiteRequest;
	}

	public void setPermiteRequest(Boolean permiteRequest) {
		this.permiteRequest = permiteRequest;
	}

	public List<TagBean> getTags() {
		return tags;
	}

	public void setTags(List<TagBean> tags) {
		this.tags = tags;
	}

	public StatusProjeto getStatus() {
		return status;
	}

	public void setStatus(StatusProjeto status) {
		this.status = status;
	}

	public List<AccountProjeto> getAccountProjetoList() {
		return accountProjetoList;
	}

	public void setAccountProjetoList(List<AccountProjeto> accountProjetoList) {
		this.accountProjetoList = accountProjetoList;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public void addMensagem(MensagemBean bean) {this.mensagens.add(bean); }

	public MensagemBean getMensagemById(Long id) {
		return this.mensagens.stream()
				.filter(m -> m.getId().equals(id))
				.findAny()
				.orElseGet(null);
	}

	public boolean deleteMensagemById(Long id) {
		return this.mensagens.removeIf(l -> l.getId().equals(id));
	}
}
