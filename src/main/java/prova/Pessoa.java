package prova;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nome;
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JoinTable(name = "pessoa_veiculo")
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();

    @OneToMany
    private List<Telefone> telefones;

	public Pessoa(String nome) {
		this.nome = nome;
	}
	
	public void adicionarVeiculo(Veiculo veiculo){
		this.veiculos.add(veiculo);
		veiculo.adicionarPessoa(this);
	}
	public void removerVeiculo(Veiculo veiculo) {
		this.veiculos.remove(veiculo);
	}
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
