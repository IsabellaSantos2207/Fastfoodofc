package local.isabellasantos.FastFood.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nomeCliente;
    private double valorTotal;
    

    
    @Enumerated(EnumType.STRING)
    private StatusPedidos status = StatusPedidos.ABERTO;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    // Getters e Setters 
    public long getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public StatusPedidos getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusPedidos status) {
        this.status = status;
    }

    // Setando a lista de itens garantindo que cada item conheça seu pedido
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
        for (ItemPedido item : itens) {
            item.setPedido(this);
        }
    }

    // Método para adicionar um item individualmente garantindo relacionamento
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
    }
    
    // Calcula o valor total somando subtotais dos itens
    public double calcularValorTotal() {
        double total = itens.stream()
                            .mapToDouble(ItemPedido::getSubtotal)
                            .sum();
        this.valorTotal = total;
        return total;
    }
}
