package karuna.karuna_backend.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PostRepository extends JpaRepository<Post, Long> { }
