import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.dao.PersistenciaJPA;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistenciaObjetos {
    PersistenciaJPA jpa = new PersistenciaJPA();

    public TestePersistenciaObjetos() {
    }

    @Before
    public void setUp() {
        jpa.conexaoAberta();
    }

    @After
    public void tearDown() {
        jpa.fecharConexao();
    }

    @Test
    public void testPersistenciaClienteProdutoVenda() throws Exception {
        // Criação de Cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente A");
        cliente.setEndereco("Rua 123");
        cliente.setTelefone("123456789");
        cliente.setEmail("cliente@exemplo.com");
        
        // Persistência do Cliente
        jpa.persist(cliente);

        // Criação de Produtos
        Produto produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setPreco(50.00);
        produto1.setQuantidade(10);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setPreco(100.00);
        produto2.setQuantidade(5);

        // Persistência dos Produtos
        jpa.persist(produto1);
        jpa.persist(produto2);

        // Criação de Venda
        Venda venda = new Venda();
        Calendar dataVenda = Calendar.getInstance();
        venda.setData(dataVenda);
        venda.setValor(150.00);
        venda.setCliente(cliente);

        // Adiciona os produtos à venda
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        venda.setProdutos(produtos);

        // Persistência da Venda
        jpa.persist(venda);

        // Validação das Persistências
        Cliente clientePersistido = (Cliente) jpa.find(Cliente.class, cliente.getId());
        assertNotNull(clientePersistido);
        assertEquals("Cliente A", clientePersistido.getNome());

        Produto produto1Persistido = (Produto) jpa.find(Produto.class, produto1.getId());
        assertNotNull(produto1Persistido);
        assertEquals("Produto 1", produto1Persistido.getNome());

        Produto produto2Persistido = (Produto) jpa.find(Produto.class, produto2.getId());
        assertNotNull(produto2Persistido);
        assertEquals("Produto 2", produto2Persistido.getNome());

        Venda vendaPersistida = (Venda) jpa.find(Venda.class, venda.getId());
        assertNotNull(vendaPersistida);
        assertEquals(150.00, vendaPersistida.getValor(), 0.01);
        assertEquals(dataVenda, vendaPersistida.getData());
        assertEquals(2, vendaPersistida.getProdutos().size());  // Verifica se há dois produtos na venda

        System.out.println("Lista de vendas do cliente: " + clientePersistido.getVendas());
    }
}
