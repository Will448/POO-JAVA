package TrabalhosAvaliativos.Trab02.Questão06.Model;

public class Produto {

    private int id;
    private String nome;
    private String dt_validade;
    private String preco;
    private String fornecedor_id;
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(String dt_validade) {
        this.dt_validade = dt_validade;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(String fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public Produto() {

    }

    public Produto(String nome, String dt_validade, String preco, String fornecedor_id, String categoria) {
        this.nome = nome;
        this.dt_validade = dt_validade;
        this.preco = preco;
        this.fornecedor_id = fornecedor_id;
        this.categoria = categoria;
    }

    public Produto(int id, String nome, String dt_validade, String preco, String fornecedor_id, String categoria) {
        this.id = id;
        this.nome = nome;
        this.dt_validade = dt_validade;
        this.preco = preco;
        this.fornecedor_id = fornecedor_id;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + id +
                ", nome='" + nome + '\'' +
                ", Dta_validade='" + dt_validade + '\'' +
                ", preco='" + preco + '\'' +
                ", Id_fornc='" + fornecedor_id + '\'' +
                ", Categoria='" + categoria + '\'' +
                '}';
    }
}
