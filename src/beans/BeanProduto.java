package beans;

public class BeanProduto {
	
	private int id_produto;
	private int usuario_id;
	private String nome;
	private int quantidade;
	private double valor;
	
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "BeanProduto [id_produto=" + id_produto + ", usuario_id=" + usuario_id + ", nome=" + nome
				+ ", quantidade=" + quantidade + ", valor=" + valor + "]";
	}
	
	
	
}