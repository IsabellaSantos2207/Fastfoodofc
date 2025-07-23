/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.isabellasantos.FastFood.service;
import java.util.List;
import local.isabellasantos.FastFood.model.Produto;
import local.isabellasantos.FastFood.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ppjata
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
        
        
    }

    public Produto salvar(Produto produto) {
            return produtoRepository.save(produto);
    
    }
   

    public List<Produto> findByCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

}

