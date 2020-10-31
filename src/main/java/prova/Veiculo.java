package prova;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Veiculo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	
    private String modelo;
    private String placa;
    
    @ManyToMany
    private List<Pessoa> pessoas;

	public Veiculo(String modelo, String placa) {
		super();
		this.modelo = modelo;
		this.placa = placa;
	}
	
	public void adicionarPessoa(Pessoa pessoa) {
		this.pessoas.add(pessoa);
		pessoa.adicionarVeiculo(this);
	}
	
	public void removerPessoa(Pessoa pessoa) {
		this.pessoas.remove(pessoa);
		pessoa.removerVeiculo(this);
	}
    
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getPlaca() {
		return placa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
    

}
