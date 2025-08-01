package local.isabellasantos.FastFood.service;
import jakarta.transaction.Transactional;
import local.isabellasantos.FastFood.model.Pedido;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import local.isabellasantos.FastFood.model.ItemPedido;
import local.isabellasantos.FastFood.model.Produto;
import local.isabellasantos.FastFood.model.StatusPedidos;
import local.isabellasantos.FastFood.repositories.PedidoRepository;
import local.isabellasantos.FastFood.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PedidoService {

    @Autowired 
    private ProdutoRepository produtoRepository;
    
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // ============================== POST ================================== //
    
    @Transactional
    public Pedido criarPedido (Pedido pedido) {
        pedido.setStatus(StatusPedidos.ABERTO);
        
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow(() -> new RuntimeException ("Produto não encontrado"));
                    
            item.setStatus(StatusPedidos.ABERTO);
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.setTotal(produto.getPreco() * item.getQuantidade());
            
        }
        pedido.getItens().forEach(item -> {
        item.calcularTudo();
        });
        pedido.calcularValorTotal();
        
        return pedidoRepository.save(pedido);
    }
    
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        pedido.calcularValorTotal(); // Atualiza o valor total antes de salvar
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    // Atualizar status do pedido
    public Pedido atualizarStatus(Long id, StatusPedidos novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
    
    // Listar pedidos por status (exemplo: ABERTO, PRONTO, ENTREGUE)
    public List<Pedido> listarPorStatus(StatusPedidos status) {
        // Para isso, crie um método personalizado no PedidoRepository
        return pedidoRepository.findByStatus(status);
    }
}