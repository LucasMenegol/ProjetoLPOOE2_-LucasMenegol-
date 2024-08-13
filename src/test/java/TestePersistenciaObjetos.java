import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.dao.PersistenciaJPA;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.*;
import java.util.Calendar;
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
    public void testPersistenciaClienteFuncionarioVenda() throws Exception {
        // Criação de Cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente A");
        cliente.setEndereco("Rua 123");
        cliente.setTelefone("123456789");
        cliente.setEmail("cliente@exemplo.com");
        
        // Persistência do Cliente
        jpa.persist(cliente);

        // Criação de Funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario A");
        funcionario.setCpf("123.456.789-00");
        funcionario.setCargo("Vendedor");
        funcionario.setSalario(3000.00);

        // Persistência do Funcionário
        jpa.persist(funcionario);

        // Criação de Venda
        Venda venda = new Venda();
        Calendar dataVenda = Calendar.getInstance();
        venda.setData(dataVenda);
        venda.setValor(150.00);
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);

        // Persistência da Venda
        jpa.persist(venda);

        // Validação das Persistências
        Cliente clientePersistido = (Cliente) jpa.find(Cliente.class, cliente.getId());
        assertNotNull(clientePersistido);
        assertEquals("Cliente A", clientePersistido.getNome());

        Funcionario funcionarioPersistido = (Funcionario) jpa.find(Funcionario.class, funcionario.getId());
        assertNotNull(funcionarioPersistido);
        assertEquals("Funcionario A", funcionarioPersistido.getNome());

        Venda vendaPersistida = (Venda) jpa.find(Venda.class, venda.getId());
        assertNotNull(vendaPersistida);
        assertEquals(150.00, vendaPersistida.getValor(), 0.01);
        assertEquals(dataVenda, vendaPersistida.getData());
        
        System.out.println("Lista vendas do cliente"+cliente.getVendas());
        
    }
}
