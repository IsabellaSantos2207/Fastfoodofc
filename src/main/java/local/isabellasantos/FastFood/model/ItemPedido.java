package local.isabellasantos.FastFood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private int quantidade;

    private double precoUnitario;
    
    private double total;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;  

    @Enumerated(EnumType.STRING)
    private StatusPedidos status;
    /**
     * Isso é responsável por calcular o valor total
     */
    
    public void calcularTudo(){
        this.total = this.quantidade * this.precoUnitario;
    }
    public ItemPedido() {
    }

    public StatusPedidos getStatus() {
        return status;
    }

    public void setStatus(StatusPedidos status) {
        this.status = status;
    }
   
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getSubtotal() {
        return this.quantidade * this.precoUnitario;
    }
}
