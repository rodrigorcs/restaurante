package br.com.restaurante.faces;import java.util.List;import javax.annotation.PostConstruct;import javax.ejb.EJB;import javax.faces.bean.ManagedBean;import javax.faces.bean.ViewScoped;import br.com.restaurante.business.AtendimentoBS;import br.com.restaurante.business.FuncionarioBS;import br.com.restaurante.model.AtendimentoModel;import br.com.restaurante.model.MenuModel;import br.com.restaurante.util.Constantes;import br.com.restaurante.util.Utilitario;import br.com.topsys.util.TSUtil;import br.com.topsys.web.faces.TSMainFaces;import br.com.topsys.web.util.TSFacesUtil;@ViewScoped@SuppressWarnings("serial")@ManagedBean(name = "listaAgendamentoFaces")public final class ListaAgendamentoFaces extends TSMainFaces {	@EJB	private AtendimentoBS atendimentoBS;	@EJB	private FuncionarioBS funcionarioBS;	private List<AtendimentoModel> grid;	private List<AtendimentoModel> gridFiltrado;	private AtendimentoModel atendimentoModel;	private AtendimentoModel atendimentoSelectionModel;	@Override	@PostConstruct	protected void clearFields() {		this.pesquisarAtendimentos();	}	public void pesquisarAtendimentos() {		this.grid = this.atendimentoBS.pesquisarAgendamentos(new AtendimentoModel(Utilitario.getOrigemAtual()));	}	@Override	protected String detail() {		if (!TSUtil.isEmpty(this.atendimentoSelectionModel)) {			this.atendimentoModel = this.atendimentoSelectionModel;		}		if (TSUtil.isEmpty(this.atendimentoModel)) {			return null;		}		AtendimentoModel pmm = this.atendimentoBS.obterCrudModel(this.atendimentoModel);		Utilitario.atualizarFuncionarioSessao(this.funcionarioBS.obterCrudModel(pmm.getFuncionarioModel()));		Utilitario.atualizarAtendimentoSessao(pmm);		MenuModel menuModel = new MenuModel();		menuModel.setId(Constantes.MENU_ATENDIMENTO);		MenuFaces mf = (MenuFaces) TSFacesUtil.getManagedBeanInSession(Constantes.MENU_FACES);		return (mf.escolherMenu(menuModel));	}	public List<AtendimentoModel> getGrid() {		return grid;	}	public void setGrid(List<AtendimentoModel> grid) {		this.grid = grid;	}	public List<AtendimentoModel> getGridFiltrado() {		return gridFiltrado;	}	public void setGridFiltrado(List<AtendimentoModel> gridFiltrado) {		this.gridFiltrado = gridFiltrado;	}	public AtendimentoModel getAtendimentoModel() {		return atendimentoModel;	}	public void setAtendimentoModel(AtendimentoModel atendimentoModel) {		this.atendimentoModel = atendimentoModel;	}	public AtendimentoModel getAtendimentoSelectionModel() {		return atendimentoSelectionModel;	}	public void setAtendimentoSelectionModel(AtendimentoModel atendimentoSelectionModel) {		this.atendimentoSelectionModel = atendimentoSelectionModel;	}}