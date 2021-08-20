package ec.com.vaccination.backend.services;

import ec.com.vaccination.backend.config.JwtTokenUtil;
import ec.com.vaccination.backend.constants.SicknessType;
import ec.com.vaccination.backend.dtos.UserLoginRequestDTO;
import ec.com.vaccination.backend.dtos.UserResponseDTO;
import ec.com.vaccination.backend.dtos.UserSignUpRequestDTO;
import ec.com.vaccination.backend.models.*;
import ec.com.vaccination.backend.repositories.HttpMappingRoleRepository;
import ec.com.vaccination.backend.repositories.RoleRepository;
import ec.com.vaccination.backend.repositories.UserRepository;
import ec.com.vaccination.backend.repositories.UserRoleRepository;
import ec.com.vaccination.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    public static final String USER_FETCH_ERROR_TEXT = "Ocurrio un error al obtener el usuario";
    public static final String INVALID_ID_ERROR_TEXT = "Debe ingresar un nro. cedula valido";
    public static final String BAD_CREDENTIALS_ERROR_TEXT = "Debe proporcionar credenciales validas";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HttpMappingRoleRepository httpMappingRoleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    public ResponseEntity<UserResponseDTO> signUp(UserSignUpRequestDTO userRequest, Errors errors) {

        if (errors.hasErrors()) {

            List<String> errorList = errors.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            return new ResponseEntity<>(UserResponseDTO.builder()
                    .errorList(errorList).build(), HttpStatus.BAD_REQUEST);

        }

        if (!StringUtils.isValidEcuadorianId(userRequest.getIdentification())) {
            return new ResponseEntity<>(UserResponseDTO.builder()
                    .errorList(Arrays.asList(INVALID_ID_ERROR_TEXT)).build(), HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .active(true)
                .birthDate(userRequest.getBirthDate())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .identification(userRequest.getIdentification())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .userName(userRequest.getUserName())
                .sicknessType(SicknessType.valueOf(userRequest.getSicknessType()))
                .surName(userRequest.getSurName())
                .build();

        List<Role> roleList = roleRepository.findAll();

        if (!roleList.isEmpty()) {

            user.setRoleList(roleList.stream().map(
                    role -> UserRole.builder()
                            .id(UserRoleId.builder()
                                    .user(user)
                                    .role(role).build())
                            .build()).collect(Collectors.toList()));

        }

        userRepository.saveAndFlush(user);

        return new ResponseEntity<>(UserResponseDTO.builder().build(), HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDTO> login(UserLoginRequestDTO userRequest, Errors errors) {

        if (errors.hasErrors()) {

            List<String> errorList = errors.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            return new ResponseEntity<>(UserResponseDTO.builder()
                    .errorList(errorList).build(), HttpStatus.BAD_REQUEST);

        }

        Optional<User> user = userRepository.findByUserName(userRequest.getUserName());

        if (!user.isPresent()) {
            return new ResponseEntity<>(UserResponseDTO.builder()
                    .errorList(Arrays.asList(USER_FETCH_ERROR_TEXT)).build(),
                    HttpStatus.BAD_REQUEST);

        }

        User userData = user.get();

        boolean isValidPassword = passwordEncoder.matches(userRequest.getPassword(), userData.getPassword());

        if (!isValidPassword) {
            return new ResponseEntity<>(UserResponseDTO.builder()
                    .errorList(Arrays.asList(BAD_CREDENTIALS_ERROR_TEXT)).build(),
                    HttpStatus.BAD_REQUEST);

        }

        String authorizationToken = jwtTokenUtil.generateToken(userData);

        return new ResponseEntity<>(UserResponseDTO.builder()
                .authorizationToken(authorizationToken)
                .build(), HttpStatus.OK);
    }

    public boolean hasUserRolePermission(String userName, String uri) {

        Optional<User> user = userRepository.findByUserName(userName);

        if (!user.isPresent()) {
            return false;
        }

        Optional<List<UserRole>> userRoleList = userRoleRepository.findByIdUserId(user.get().getId());

        if (!userRoleList.isPresent()) {
            return false;
        }

        List<Integer> roleList = userRoleList.get().stream()
                .map(role -> role.getId().getRole().getId()).collect(Collectors.toList());

        Optional<HttpMappingRole> mappingUriList = httpMappingRoleRepository.findByIdHttpMappingUriAndIdRoleIdIn(
                uri, roleList);

        if (!mappingUriList.isPresent()) {
            return false;
        }

        return true;
    }
}