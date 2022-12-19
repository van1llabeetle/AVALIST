package br.edu.ifgoiano.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifgoiano.entidade.Tarefa;
import br.edu.ifgoiano.repository.TarefaRepository;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	public List<Tarefa> listarTarefas() {
		return tarefaRepository.findAll(Sort.by(Sort.Direction.ASC, "feito", "data"));
	}

	@Override
	public void inserir(Tarefa tarefa) {
		this.tarefaRepository.save(tarefa);

	}

	@Override
	public Tarefa obterTarefa(Long id) {
		return this.tarefaRepository.getReferenceById(id);
	}

	@Override
	public void alterarTarefa(Tarefa tarefa) {
		this.tarefaRepository.save(tarefa);
	}

	@Override
	public void excluirTarefa(Long id) {
		this.tarefaRepository.deleteById(id);

	}
	
	//Carregar os valores 
	
	@Override
	public void AtualizarParaFeito(Long id) {
		Tarefa tarefa = this.tarefaRepository.getReferenceById(id);
		tarefa.setFeito(true);
		this.tarefaRepository.save(tarefa);
		
	}

	@Override
	public Long obterQtdeTarefasPendentes() {
		return this.tarefaRepository.countByFeitoFalse();
		
	}

}
