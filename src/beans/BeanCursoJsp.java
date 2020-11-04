package beans;

public class BeanCursoJsp {

	@Override
	public String toString() {
		return "BeanCursoJsp [id=" + id + ", Login=" + Login + ", Senha=" + Senha + ", nome=" + nome + ", telefone="
				+ telefone + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", estado="
				+ estado + ", ibge=" + ibge + "]";
	}

	private long id;
	private String Login;
	private String Senha;
	private String nome;
	private String telefone;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private int ibge;
	private String fotoBase64;
	private String contentType;
	private String curriculoBase64;
	private String contenttypecurriculo;
	
	private String tempFotoUser;
	
	public String getTempFotoUser() {
		
		tempFotoUser = "data:" + contentType + ";base64," + fotoBase64;
		
		return tempFotoUser;
	}

	
	
	public String getContenttypecurriculo() {
		return contenttypecurriculo;
	}



	public void setContenttypecurriculo(String contenttypecurriculo) {
		this.contenttypecurriculo = contenttypecurriculo;
	}



	public String getCurriculoBase64() {
		return curriculoBase64;
	}



	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}



	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIbge() {
		return ibge;
	}

	public void setIbge(int ibge) {
		this.ibge = ibge;
	}

}