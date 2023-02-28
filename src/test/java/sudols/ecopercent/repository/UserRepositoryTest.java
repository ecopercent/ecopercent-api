package sudols.ecopercent.repository;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sudols.ecopercent.domain.User;
import sudols.ecopercent.dto.UserPostDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    MemoryUserRepository memoryUserRepository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        memoryUserRepository.clearStore();
    }

    @Test
    public void save() {
        // given
        UserPostDto postUser = UserPostDto.builder()
                .nickname("dason")
                .email("dason@student.42.fr")
                .build();

        // when
        User user = postUser.toEntity();
        Long userId = memoryUserRepository.save(user);

        // then
        User result = memoryUserRepository.findById(userId).get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    public void findById() {
        // given
        UserPostDto postUser = UserPostDto.builder()
                .nickname("dason")
                .email("dason@student.42.fr")
                .build();
        User user = postUser.toEntity();
        memoryUserRepository.save(user);

        // when
        User result = memoryUserRepository.findById(user.getUserId()).get();

        // then
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    public void findByEmail() {
        // given
        UserPostDto postUser = UserPostDto.builder()
                .nickname("dason")
                .email("dason@student.42.fr")
                .build();
        User user = postUser.toEntity();
        memoryUserRepository.save(user);

        // when
        User result = memoryUserRepository.findByEmail(user.getEmail()).get();

        // then
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
        // given
        UserPostDto postUser = UserPostDto.builder()
                .nickname("dason")
                .email("dason@student.42.fr")
                .build();
        User user = postUser.toEntity();
        memoryUserRepository.save(user);

        // when
        memoryUserRepository.delete(user.getUserId());

        // then
        Optional<User> result = memoryUserRepository.findById(user.getUserId());
        Assertions.assertThat(result).isEqualTo(Optional.empty());
    }
}