package br.com.fiap.aimpress.repository;

import br.com.fiap.aimpress.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
