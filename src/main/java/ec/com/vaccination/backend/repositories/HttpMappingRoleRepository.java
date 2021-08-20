package ec.com.vaccination.backend.repositories;

import ec.com.vaccination.backend.models.HttpMappingRole;
import ec.com.vaccination.backend.models.HttpMappingRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HttpMappingRoleRepository extends JpaRepository<HttpMappingRole, HttpMappingRoleId> {

    Optional<HttpMappingRole> findByIdHttpMappingUriAndIdRoleIdIn(String uri, List<Integer> roleId);
}
