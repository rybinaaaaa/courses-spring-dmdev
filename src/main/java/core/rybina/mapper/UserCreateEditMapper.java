package core.rybina.mapper;

import core.rybina.database.entity.Company;
import core.rybina.database.entity.User;
import core.rybina.database.repository.CompanyRepository;
import core.rybina.dto.UserCreateEditDto;
import core.rybina.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto from, User toObject) {
        copy(from, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto from) {
        User user = new User();
        copy(from, user);

        return user;
    }

    private void copy(UserCreateEditDto from, User user) {
        user.setUsername(from.getUsername());
        user.setFirstname(from.getFirstname());
        user.setLastname(from.getLastname());
        user.setBirthdate(from.getBirthdate());
        user.setRole(from.getRole());
        user.setCompany(getCompany(from.getCompanyId()));

        Optional.ofNullable(from.getRowPassword())
                        .filter(StringUtils::hasText)
                                .map(passwordEncoder::encode)
                                        .ifPresent(user::setPassword);

        Optional.ofNullable(from.getImage())
                .filter(image -> !image.isEmpty())
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));

    }

    public Company getCompany(Integer id) {
        return Optional.ofNullable(id).flatMap(companyRepository::findById).orElse(null);
    }
}
