import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.dao.PersistenciaJPA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteConexao {
    
    public TesteConexao() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
     PersistenciaJPA jpa = new PersistenciaJPA();
        if(jpa.conexaoAberta()){
            System.out.println("Conectado ao BD");
        } else {
            System.err.println("Erro ao conectar ao banco");
        }
     }
}

