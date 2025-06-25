package utez.edu.mx.unidad3.modules.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();
    Client save(Client client); //este metodo nos ayuda a guardar y actualizar
    Optional<Client> findById(long id);
    @Modifying
    @Query(value = "DELETE FROM client WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") Long id);

    Optional<Client>findByEmail(String email);
}
