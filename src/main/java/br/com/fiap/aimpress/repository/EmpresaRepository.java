package br.com.fiap.aimpress.repository;

import br.com.fiap.aimpress.Domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
