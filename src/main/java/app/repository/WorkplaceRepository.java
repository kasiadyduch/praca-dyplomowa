package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Workplace;

public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {
}
