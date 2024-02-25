package TrabalhosAvaliativos.Trab02.Questão06.View;
import TrabalhosAvaliativos.Trab02.Questão06.Controller.ProdutoController;
import TrabalhosAvaliativos.Trab02.Questão06.Model.Produto;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormProdutos {
    private JButton deletarbtn;
    private JTextField dtavalidadetxt;
    private JButton salvarbtn;
    private JLabel Nome;
    private JTextField precotxt;
    private JTextField categoriatxt;
    private JTextField idforntxt;
    private JPanel Panel;
    private JLabel nome;
    private JTextField nometxt;
    private JTable listarTable;
    private JButton listarbtn;
    private JButton cancelarbtn;
    private JButton button2;
    private JTextField idtxt;
    private JButton buscarbtn;

    public FormProdutos() {
        listar();

        salvarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ProdutoController produtoController = new ProdutoController();
                    Produto produto = new Produto();

                    produto.setNome(nometxt.getText());
                    produto.setDt_validade(dtavalidadetxt.getText());
                    produto.setPreco(precotxt.getText());
                    produto.setFornecedor_id(idforntxt.getText());
                    produto.setCategoria(categoriatxt.getText());

                    if (idtxt.getText().isEmpty()) {
                        produtoController.salvar(produto);
                    } else {
                        produto.setId(Integer.parseInt(idtxt.getText()));
                        produtoController.atualizar(produto);
                    }

                    System.out.println("Registro inserido com sucesso! :)");

                    listar();
                    limpar();
                    salvarbtn.setText("Salvar");

                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
            }
        });
        cancelarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoController produtoController = new ProdutoController();
                produtoController.testarConexao();
                limpar();
                listar();
                salvarbtn.setText("Salvar");
            }
        });

        listarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listar();
            }
        });
        listarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idtxt.setText(listarTable.getValueAt(listarTable.getSelectedRow(), 0).toString());
                nometxt.setText(listarTable.getValueAt(listarTable.getSelectedRow(), 4).toString());
                dtavalidadetxt.setText(listarTable.getValueAt(listarTable.getSelectedRow(), 5).toString());
                precotxt.setText(listarTable.getValueAt(listarTable.getSelectedRow(), 1).toString());
                idforntxt.setText(listarTable.getValueAt(listarTable.getSelectedRow(), 3).toString());
                categoriatxt.setText(listarTable.getValueAt(listarTable.getSelectedRow(), 2 ).toString());

                salvarbtn.setText("Editar");

            }
        });

        deletarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Deseja confirmar a remoção do registro");

                if (i == JOptionPane.YES_OPTION && !idtxt.getText().isEmpty()) {
                    try {
                        ProdutoController produtoController = new ProdutoController();
                        int id = Integer.parseInt(listarTable.getValueAt(listarTable.getSelectedRow(), 0).toString());
                        System.out.println(id);
                        produtoController.deletar(id);

                        listar();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    //JOptionPane.showMessageDialog(null, "Você deletou com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para ser deletado");
                }
            }
        });
        buscarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               try {
                   if (!nometxt.getText().isEmpty() || !dtavalidadetxt.getText().isEmpty() ||
                   !precotxt.getText().isEmpty() || !categoriatxt.getText().isEmpty() || !idforntxt.getText().isEmpty()){

                       Produto produtos = new Produto();
                       ProdutoController produtoController = new ProdutoController();

                       produtos.setNome(nometxt.getText());
                       produtos.setDt_validade(dtavalidadetxt.getText());
                       produtos.setPreco(precotxt.getText());
                       produtos.setFornecedor_id(idforntxt.getText());
                       produtos.setCategoria(categoriatxt.getText());


                       ResultSet rs = produtoController.buscar(produtos);

                       listarTable.setModel(DbUtils.resultSetToTableModel(rs));
                       //listarTable.setTableHeader(null);
                       setNomeColuna(new String[]{"Id do produto", "Preço","Categoria" , "Id_Fornecedor", "Nome","Categoria"});
                   }else{
                       JOptionPane.showMessageDialog(null, "Informe algum registro para ser buscado");
                   }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });
    }
    public void limpar() {
        idtxt.setText("");
        nometxt.setText("");
        dtavalidadetxt.setText("");
        categoriatxt.setText("");
        precotxt.setText("");
        idforntxt.setText("");
    }

    public void listar() {
        ProdutoController produtoController = new ProdutoController();
        ResultSet rs = produtoController.listar();

        listarTable.setModel(DbUtils.resultSetToTableModel(rs));
        //listarTable.setTableHeader(null);
        setNomeColuna(new String[]{"Id do produto", "Preço","Categoria" , "Id_Fornecedor", "Nome","Categoria"});
    }

    public void setNomeColuna(String[] colunas) {
        for (int i = 0; i < colunas.length; i++) {
            listarTable.getColumnModel().getColumn(i).setHeaderValue(colunas[i]);
        }
    }

    public static void main (String[]args){
            JFrame frame = new JFrame("TelaFormProdutos");
            frame.setContentPane(new FormProdutos().Panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    private void createUIComponents() {
    }
}

