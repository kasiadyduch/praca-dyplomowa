package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
