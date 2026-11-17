package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Config.EmailJaCadastradoException;
import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.Model.dto.Request.UsuarioCadastroDTO;
import br.edu.ufpel.mapacultural.Model.dto.Response.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository         repository;
    private final PasswordEncoder           passwordEncoder;

    public UsuarioService( UsuarioRepository repository, PasswordEncoder passwordEncoder )
    {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    private UsuarioDTO convertToDTO( UsuarioRegistrado usuario )
    {
        return new UsuarioDTO(
                usuario.getUsuarioId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getSobrenome()
        );
    }

//    ================= Métodos GET =========================
    @Transactional (readOnly = true)
    public List<UsuarioDTO> listarTodos()
    {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Transactional(readOnly=true)
    public Optional<UsuarioDTO> buscarPorId(Integer id)
    {
        return repository.findById(id)
                .map(this::convertToDTO);
    }

//====================== Métodos POST ========================
    @Transactional
    public UsuarioDTO cadastrar (UsuarioCadastroDTO dto)
    {
        if (repository.existsByEmail(dto.email())) {
            throw new EmailJaCadastradoException("Esse email já existe");
        }

        UsuarioRegistrado usuario = new UsuarioRegistrado();
        usuario.setEmail(dto.email());
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());

        usuario.setSenhaHash(passwordEncoder.encode(dto.senha()));

        UsuarioRegistrado usuario_comId = repository.save(usuario);

        return convertToDTO(usuario_comId);
    }

//========================= DELETE ========================
    @Transactional
    public void excluir (Integer id) {
        UsuarioRegistrado usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        repository.delete(usuario);
    }

}
