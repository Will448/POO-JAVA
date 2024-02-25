package TrabalhosAvaliativos.Trab02.Questão06.Controller;

import TrabalhosAvaliativos.Trab02.Questão06.BancoDados.Conexao;
import TrabalhosAvaliativos.Trab02.Questão06.Model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoController {

    private Conexao bd;

    public ProdutoController() {
        this.bd = new Conexao();
    }

    public void testarConexao() {
        this.bd.getConexao();
        System.out.println("Conectou");
    }

    public void salvar(Produto produto) throws SQLException {
        String sql;
        try {
            sql = "INSERT INTO produto (nome, dt_validade, preco, fornecedor_id, categoria) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDt_validade());
            stmt.setString(3, produto.getPreco());
            stmt.setString(4, produto.getFornecedor_id());
            stmt.setString(5, produto.getCategoria());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }


    public ResultSet listar() {
        String sql;
        try {
            sql = "SELECT * FROM produto";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            throw new RuntimeException();

        }
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql;
        try {
            sql = "UPDATE produto  SET preco = ?, SET categoria = ?,  SET fornecedor_id = ?,   SET nome = ?, SET dt_validade = ? WHERE id = ?";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);


            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDt_validade());
            stmt.setString(3, produto.getPreco());
            stmt.setString(4, produto.getFornecedor_id());
            stmt.setString(5, produto.getCategoria());
            stmt.setInt(0, produto.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql;
        try {
            sql = "DELETE FROM produto WHERE id = ?";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public ResultSet buscar(Produto produto) throws SQLException {
        String sql = "";
        try {
            if (!produto.getNome().isEmpty()) {
                sql = "SELECT * FROM produto WHERE nome like'%" + produto.getNome() + "%'";
            } else if (!produto.getDt_validade().isEmpty()) {
                sql = "SELECT * FROM produto WHERE dt_validade like'%" + produto.getDt_validade() + "%'";
            } else if (!produto.getFornecedor_id().isEmpty()) {
                sql = "SELECT * FROM produto WHERE fornecedor_id like'%" + produto.getFornecedor_id() + "%'";
            } else if (!produto.getPreco().isEmpty()) {
                sql = "SELECT * FROM produto WHERE preco like'%" + produto.getPreco() + "%'";
            } else if (!produto.getCategoria().isEmpty()) {
                sql = "SELECT * FROM produto WHERE categoria like'%" + produto.getCategoria() + "%'";
            }
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            return rs;

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}



