/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package local.isabellasantos.FastFood.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import local.isabellasantos.FastFood.model.Pedido;
import local.isabellasantos.FastFood.model.StatusPedidos;
/**
 *
 * @author Aluno
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByStatus(StatusPedidos status);
    
}

