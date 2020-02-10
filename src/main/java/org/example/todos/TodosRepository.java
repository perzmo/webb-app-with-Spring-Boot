package org.example.todos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TodosRepository extends JpaRepository<Todos, Integer> {

}
