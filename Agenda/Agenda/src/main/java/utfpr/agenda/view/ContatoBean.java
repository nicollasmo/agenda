/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utfpr.agenda.view;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utfpr.agenda.dao.ContatoDAO;
import utfpr.agenda.entity.Contato;
import utfpr.agenda.support.PageBean;

/**
 *
 * @author Samsung
 */
@ManagedBean
@SessionScoped
public class ContatoBean extends PageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Contato contato = new Contato();
    
    private final ContatoDAO contatoDAO = new ContatoDAO();
    
    private List<Contato> contatos = contatoDAO.listar();
    
    private boolean editar = false;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    public String listar(){
        this.contatos = this.contatoDAO.listar();
        return "";
    }
    public String cadastrar(){
        this.contato = new Contato();
        this.editar = false;
        return "cadastro?faces-redirect=true";
    }
    public String excluir(long codContato){
        this.contatoDAO.remover(Contato.class, codContato);
        this.contatos = this.contatoDAO.listar();
        return "";
    }
    public String alterar(long codContato){
        this.editar = true;
        this.contato = (Contato) this.contatoDAO.pesquisar(Contato.class, codContato);
        return "cadastro?faces-redirect=true";
    }
    public String cadastrarContato(){
        this.contatoDAO.inserir(this.contato);
        this.contatos = this.contatoDAO.listar();
        this.contato = new Contato();
        return "contatos?faces-redirect=true";
    }
    public String editarContato(){
        this.editar = false;
        this.contatoDAO.alterar(contato);
        this.contatos = this.contatoDAO.listar();
        this.contato = new Contato();
        return "contatos?faces-redirect=true";
    }
    
    
    
}
