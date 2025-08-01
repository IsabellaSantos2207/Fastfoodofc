/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.isabellasantos.FastFood.controller;
import local.isabellasantos.FastFood.model.Pedido;
import local.isabellasantos.FastFood.model.StatusPedidos;
import local.isabellasantos.FastFood.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/FastFurious/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody Pedido itemPedido) {
        try{
            Pedido pedido = pedidoService.criarPedido(itemPedido); 
            return ResponseEntity.ok(pedido);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletar(id);
    }

    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestParam StatusPedidos status) {
        return pedidoService.atualizarStatus(id, status);
    }

    @GetMapping("/status/{status}")
    public List<Pedido> listarPorStatus(@PathVariable StatusPedidos status) {
        return pedidoService.listarPorStatus(status);
    }
}
